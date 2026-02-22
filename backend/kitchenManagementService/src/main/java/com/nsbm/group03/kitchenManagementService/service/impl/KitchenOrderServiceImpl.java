package com.nsbm.group03.kitchenManagementService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsbm.group03.kitchenManagementService.client.InventoryClient;
import com.nsbm.group03.kitchenManagementService.dto.InventoryItemDTO;
import com.nsbm.group03.kitchenManagementService.dto.InventoryRequestDTO;
import com.nsbm.group03.kitchenManagementService.dto.KitchenOrderDTO;
import com.nsbm.group03.kitchenManagementService.dto.KitchenOrderItemDTO;
import com.nsbm.group03.kitchenManagementService.entity.KitchenMenuItem;
import com.nsbm.group03.kitchenManagementService.entity.KitchenOrder;
import com.nsbm.group03.kitchenManagementService.entity.KitchenOrderItem;
import com.nsbm.group03.kitchenManagementService.enums.OrderStatus;
import com.nsbm.group03.kitchenManagementService.exception.MenuItemNotAvailableException;
import com.nsbm.group03.kitchenManagementService.exception.ResourceNotFoundException;
import com.nsbm.group03.kitchenManagementService.repository.KitchenMenuItemRepository;
import com.nsbm.group03.kitchenManagementService.repository.KitchenOrderRepository;
import com.nsbm.group03.kitchenManagementService.service.KitchenOrderService;

@Service
public class KitchenOrderServiceImpl implements KitchenOrderService {

    private static final Logger logger = LoggerFactory.getLogger(KitchenOrderServiceImpl.class);

    private final KitchenOrderRepository orderRepository;
    private final KitchenMenuItemRepository menuItemRepository;
    private final InventoryClient inventoryClient;

    public KitchenOrderServiceImpl(KitchenOrderRepository orderRepository,
                                   KitchenMenuItemRepository menuItemRepository,
                                   InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.inventoryClient = inventoryClient;
    }

    // ══════════════════════════════════════
    //  Create Order (with validation + inventory)
    // ══════════════════════════════════════

    @Override
    @Transactional
    public KitchenOrderDTO createOrder(KitchenOrderDTO dto) {
        KitchenOrder order = new KitchenOrder();
        order.setRestaurantId(dto.getRestaurantId());
        order.setTableNumber(dto.getTableNumber());
        order.setStaffId(dto.getStaffId());
        order.setSpecialInstructions(dto.getSpecialInstructions());
        order.setOrderStatus(OrderStatus.PENDING);

        double totalAmount = 0.0;
        List<InventoryRequestDTO> inventoryRequests = new ArrayList<>();

        // Validate each item against the menu and build order items
        for (KitchenOrderItemDTO itemDTO : dto.getOrderItems()) {
            KitchenMenuItem menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu Item", itemDTO.getMenuItemId()));

            // Check availability
            if (!menuItem.isAvailable()) {
                throw new MenuItemNotAvailableException(menuItem.getId());
            }

            KitchenOrderItem orderItem = new KitchenOrderItem();
            orderItem.setMenuItemId(menuItem.getId());
            orderItem.setItemName(menuItem.getItemName());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setNotes(itemDTO.getNotes());

            order.addOrderItem(orderItem);

            totalAmount += menuItem.getPrice() * itemDTO.getQuantity();

            // Prepare inventory deduction request
            inventoryRequests.add(new InventoryRequestDTO(menuItem.getItemName(), itemDTO.getQuantity()));
        }

        order.setTotalAmount(totalAmount);

        KitchenOrder saved = orderRepository.save(order);
        logger.info("Created kitchen order ID: {} with {} items, total: {}",
                saved.getId(), saved.getOrderItems().size(), saved.getTotalAmount());

        // Send inventory deduction request (non-blocking — order proceeds even if inventory service is down)
        if (!inventoryRequests.isEmpty()) {
            inventoryClient.deductInventory(inventoryRequests);
        }

        return mapToDTO(saved);
    }

    // ══════════════════════════════════════
    //  Get / List Orders
    // ══════════════════════════════════════

    @Override
    public KitchenOrderDTO getOrderById(Long id) {
        KitchenOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen Order", id));
        return mapToDTO(order);
    }

    @Override
    public List<KitchenOrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ══════════════════════════════════════
    //  Update Order
    // ══════════════════════════════════════

    @Override
    @Transactional
    public KitchenOrderDTO updateOrder(Long id, KitchenOrderDTO dto) {
        KitchenOrder existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen Order", id));

        existing.setRestaurantId(dto.getRestaurantId());
        existing.setTableNumber(dto.getTableNumber());
        existing.setSpecialInstructions(dto.getSpecialInstructions());

        if (dto.getStaffId() != null) {
            existing.setStaffId(dto.getStaffId());
        }

        // Update order items if provided
        if (dto.getOrderItems() != null && !dto.getOrderItems().isEmpty()) {
            existing.getOrderItems().clear();
            double totalAmount = 0.0;

            for (KitchenOrderItemDTO itemDTO : dto.getOrderItems()) {
                KitchenMenuItem menuItem = menuItemRepository.findById(itemDTO.getMenuItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Menu Item", itemDTO.getMenuItemId()));

                if (!menuItem.isAvailable()) {
                    throw new MenuItemNotAvailableException(menuItem.getId());
                }

                KitchenOrderItem orderItem = new KitchenOrderItem();
                orderItem.setMenuItemId(menuItem.getId());
                orderItem.setItemName(menuItem.getItemName());
                orderItem.setQuantity(itemDTO.getQuantity());
                orderItem.setPrice(menuItem.getPrice());
                orderItem.setNotes(itemDTO.getNotes());

                existing.addOrderItem(orderItem);
                totalAmount += menuItem.getPrice() * itemDTO.getQuantity();
            }
            existing.setTotalAmount(totalAmount);
        }

        KitchenOrder updated = orderRepository.save(existing);
        logger.info("Updated kitchen order ID: {}", updated.getId());
        return mapToDTO(updated);
    }

    // ══════════════════════════════════════
    //  Delete Order
    // ══════════════════════════════════════

    @Override
    public void deleteOrder(Long id) {
        KitchenOrder existing = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen Order", id));
        orderRepository.delete(existing);
        logger.info("Deleted kitchen order ID: {}", id);
    }

    // ══════════════════════════════════════
    //  Status Management (PENDING → COOKING → READY → SERVED)
    // ══════════════════════════════════════

    @Override
    public KitchenOrderDTO updateOrderStatus(Long id, String status) {
        KitchenOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen Order", id));

        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
        OrderStatus currentStatus = order.getOrderStatus();

        // Validate status transitions
        validateStatusTransition(currentStatus, newStatus);

        order.setOrderStatus(newStatus);
        KitchenOrder updated = orderRepository.save(order);
        logger.info("Order ID {} status changed: {} → {}", id, currentStatus, newStatus);
        return mapToDTO(updated);
    }

    private void validateStatusTransition(OrderStatus current, OrderStatus next) {
        boolean valid = switch (current) {
            case PENDING -> next == OrderStatus.COOKING;
            case COOKING -> next == OrderStatus.READY;
            case READY -> next == OrderStatus.SERVED;
            case SERVED -> false; // Terminal state
        };

        if (!valid) {
            throw new IllegalArgumentException(
                    "Invalid status transition: " + current + " → " + next +
                    ". Allowed flow: PENDING → COOKING → READY → SERVED");
        }
    }

    // ══════════════════════════════════════
    //  Filtering
    // ══════════════════════════════════════

    @Override
    public List<KitchenOrderDTO> getOrdersByStatus(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return orderRepository.findByOrderStatus(orderStatus)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenOrderDTO> getOrdersByStaff(Long staffId) {
        return orderRepository.findByStaffId(staffId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenOrderDTO> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ══════════════════════════════════════
    //  Staff Assignment
    // ══════════════════════════════════════

    @Override
    public KitchenOrderDTO assignStaff(Long orderId, Long staffId) {
        KitchenOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen Order", orderId));
        order.setStaffId(staffId);
        KitchenOrder updated = orderRepository.save(order);
        logger.info("Assigned staff {} to order {}", staffId, orderId);
        return mapToDTO(updated);
    }

    // ══════════════════════════════════════
    //  Dashboard Count
    // ══════════════════════════════════════

    @Override
    public long countOrdersByStatus(String status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        return orderRepository.countByOrderStatus(orderStatus);
    }

    @Override
    public java.util.Map<String, Long> getDashboardCounts() {
        java.util.Map<String, Long> counts = new java.util.LinkedHashMap<>();
        for (OrderStatus s : OrderStatus.values()) {
            counts.put(s.name(), orderRepository.countByOrderStatus(s));
        }
        return counts;
    }

    // ══════════════════════════════════════
    //  Inventory Integration (read from Inventory Service)
    // ══════════════════════════════════════

    @Override
    public List<InventoryItemDTO> getInventoryItems() {
        return inventoryClient.getAllInventoryItems();
    }

    @Override
    public InventoryItemDTO getInventoryItemById(Long itemId) {
        InventoryItemDTO item = inventoryClient.getInventoryItemById(itemId);
        if (item == null) {
            throw new ResourceNotFoundException("Inventory Item", itemId);
        }
        return item;
    }

    // ══════════════════════════════════════
    //  DTO ↔ Entity Mapping
    // ══════════════════════════════════════

    private KitchenOrderDTO mapToDTO(KitchenOrder entity) {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        dto.setId(entity.getId());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setTableNumber(entity.getTableNumber());
        dto.setStaffId(entity.getStaffId());
        dto.setOrderStatus(entity.getOrderStatus().name());
        dto.setSpecialInstructions(entity.getSpecialInstructions());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getOrderItems() != null) {
            List<KitchenOrderItemDTO> itemDTOs = entity.getOrderItems()
                    .stream()
                    .map(this::mapOrderItemToDTO)
                    .collect(Collectors.toList());
            dto.setOrderItems(itemDTOs);
        }

        return dto;
    }

    private KitchenOrderItemDTO mapOrderItemToDTO(KitchenOrderItem entity) {
        KitchenOrderItemDTO dto = new KitchenOrderItemDTO();
        dto.setId(entity.getId());
        dto.setMenuItemId(entity.getMenuItemId());
        dto.setItemName(entity.getItemName());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setNotes(entity.getNotes());
        return dto;
    }
}
