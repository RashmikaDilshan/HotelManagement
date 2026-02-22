package com.nsbm.group03.kitchenManagementService.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nsbm.group03.kitchenManagementService.dto.KitchenMenuItemDTO;
import com.nsbm.group03.kitchenManagementService.entity.KitchenMenuItem;
import com.nsbm.group03.kitchenManagementService.enums.MealType;
import com.nsbm.group03.kitchenManagementService.enums.ServiceType;
import com.nsbm.group03.kitchenManagementService.exception.ResourceNotFoundException;
import com.nsbm.group03.kitchenManagementService.repository.KitchenMenuItemRepository;
import com.nsbm.group03.kitchenManagementService.service.KitchenMenuItemService;

@Service
public class KitchenMenuItemServiceImpl implements KitchenMenuItemService {

    private static final Logger logger = LoggerFactory.getLogger(KitchenMenuItemServiceImpl.class);

    private final KitchenMenuItemRepository menuItemRepository;

    public KitchenMenuItemServiceImpl(KitchenMenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // ══════════════════════════════════════
    //  CRUD Operations
    // ══════════════════════════════════════

    @Override
    public KitchenMenuItemDTO createMenuItem(KitchenMenuItemDTO dto) {
        KitchenMenuItem entity = mapToEntity(dto);
        KitchenMenuItem saved = menuItemRepository.save(entity);
        logger.info("Created menu item: {} (ID: {})", saved.getItemName(), saved.getId());
        return mapToDTO(saved);
    }

    @Override
    public KitchenMenuItemDTO getMenuItemById(Long id) {
        KitchenMenuItem entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item", id));
        return mapToDTO(entity);
    }

    @Override
    public List<KitchenMenuItemDTO> getAllMenuItems() {
        return menuItemRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KitchenMenuItemDTO updateMenuItem(Long id, KitchenMenuItemDTO dto) {
        KitchenMenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item", id));

        existing.setItemName(dto.getItemName());
        existing.setCategory(dto.getCategory());
        existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.isAvailable());
        existing.setMealType(MealType.valueOf(dto.getMealType().toUpperCase()));
        existing.setServiceType(ServiceType.valueOf(dto.getServiceType().toUpperCase()));
        existing.setMenuDate(dto.getMenuDate());
        existing.setRestaurantId(dto.getRestaurantId());
        existing.setDescription(dto.getDescription());

        KitchenMenuItem updated = menuItemRepository.save(existing);
        logger.info("Updated menu item: {} (ID: {})", updated.getItemName(), updated.getId());
        return mapToDTO(updated);
    }

    @Override
    public void deleteMenuItem(Long id) {
        KitchenMenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item", id));
        menuItemRepository.delete(existing);
        logger.info("Deleted menu item with ID: {}", id);
    }

    // ══════════════════════════════════════
    //  Filtering & Searching
    // ══════════════════════════════════════

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByMealType(String mealType) {
        MealType type = MealType.valueOf(mealType.toUpperCase());
        return menuItemRepository.findByMealType(type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByServiceType(String serviceType) {
        ServiceType type = ServiceType.valueOf(serviceType.toUpperCase());
        return menuItemRepository.findByServiceType(type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByDate(LocalDate menuDate) {
        return menuItemRepository.findByMenuDate(menuDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> searchMenuItemsByName(String name) {
        return menuItemRepository.findByItemNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByCategory(String category) {
        return menuItemRepository.findByCategory(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByPriceRange(Double minPrice, Double maxPrice) {
        return menuItemRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getAvailableMenuItems() {
        return menuItemRepository.findByAvailableTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long countAvailableMenuItems() {
        return menuItemRepository.countByAvailableTrue();
    }

    // ══════════════════════════════════════
    //  Service-Specific Menu Views
    // ══════════════════════════════════════

    @Override
    public List<KitchenMenuItemDTO> getAvailableMenuByServiceType(String serviceType) {
        ServiceType type = ServiceType.valueOf(serviceType.toUpperCase());
        return menuItemRepository.findByServiceTypeAndAvailableTrue(type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getRestaurantMenuItems(Long restaurantId) {
        return menuItemRepository.findByRestaurantIdAndAvailableTrue(restaurantId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getEventMenuItems() {
        return menuItemRepository.findByServiceTypeAndAvailableTrue(ServiceType.EVENT)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ══════════════════════════════════════
    //  Combined Filters
    // ══════════════════════════════════════

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByRestaurantAndDate(Long restaurantId, LocalDate menuDate) {
        return menuItemRepository.findByRestaurantIdAndMenuDate(restaurantId, menuDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByRestaurantAndMealType(Long restaurantId, String mealType) {
        MealType type = MealType.valueOf(mealType.toUpperCase());
        return menuItemRepository.findByRestaurantIdAndMealType(restaurantId, type)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<KitchenMenuItemDTO> getMenuItemsByFilters(String mealType, String serviceType, LocalDate menuDate) {
        MealType meal = MealType.valueOf(mealType.toUpperCase());
        ServiceType service = ServiceType.valueOf(serviceType.toUpperCase());
        return menuItemRepository.findByMealTypeAndServiceTypeAndMenuDateAndAvailableTrue(meal, service, menuDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ══════════════════════════════════════
    //  Availability Toggle
    // ══════════════════════════════════════

    @Override
    public KitchenMenuItemDTO toggleAvailability(Long id) {
        KitchenMenuItem entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item", id));
        entity.setAvailable(!entity.isAvailable());
        KitchenMenuItem updated = menuItemRepository.save(entity);
        logger.info("Toggled availability for menu item ID {}: now {}", id, updated.isAvailable());
        return mapToDTO(updated);
    }

    // ══════════════════════════════════════
    //  DTO ↔ Entity Mapping
    // ══════════════════════════════════════

    private KitchenMenuItemDTO mapToDTO(KitchenMenuItem entity) {
        KitchenMenuItemDTO dto = new KitchenMenuItemDTO();
        dto.setId(entity.getId());
        dto.setItemName(entity.getItemName());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.isAvailable());
        dto.setMealType(entity.getMealType().name());
        dto.setServiceType(entity.getServiceType().name());
        dto.setMenuDate(entity.getMenuDate());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    private KitchenMenuItem mapToEntity(KitchenMenuItemDTO dto) {
        KitchenMenuItem entity = new KitchenMenuItem();
        entity.setItemName(dto.getItemName());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.isAvailable());
        entity.setMealType(MealType.valueOf(dto.getMealType().toUpperCase()));
        entity.setServiceType(ServiceType.valueOf(dto.getServiceType().toUpperCase()));
        entity.setMenuDate(dto.getMenuDate());
        entity.setRestaurantId(dto.getRestaurantId());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
