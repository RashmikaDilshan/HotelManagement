package com.nsbm.group03.kitchenManagementService.service;

import java.util.List;

import com.nsbm.group03.kitchenManagementService.dto.InventoryItemDTO;
import com.nsbm.group03.kitchenManagementService.dto.KitchenOrderDTO;

public interface KitchenOrderService {

    // ── CRUD Operations ──
    KitchenOrderDTO createOrder(KitchenOrderDTO orderDTO);

    KitchenOrderDTO getOrderById(Long id);

    List<KitchenOrderDTO> getAllOrders();

    KitchenOrderDTO updateOrder(Long id, KitchenOrderDTO orderDTO);

    void deleteOrder(Long id);

    // ── Status Management ──
    KitchenOrderDTO updateOrderStatus(Long id, String status);

    // ── Filtering ──
    List<KitchenOrderDTO> getOrdersByStatus(String status);

    List<KitchenOrderDTO> getOrdersByStaff(Long staffId);

    List<KitchenOrderDTO> getOrdersByRestaurant(Long restaurantId);

    // ── Staff Assignment ──
    KitchenOrderDTO assignStaff(Long orderId, Long staffId);

    // ── Dashboard counts ──
    long countOrdersByStatus(String status);
    java.util.Map<String, Long> getDashboardCounts();

    // ── Inventory Integration ──
    List<InventoryItemDTO> getInventoryItems();

    InventoryItemDTO getInventoryItemById(Long itemId);
}
