package com.nsbm.group03.kitchenManagementService.service;

import java.time.LocalDate;
import java.util.List;

import com.nsbm.group03.kitchenManagementService.dto.KitchenMenuItemDTO;

public interface KitchenMenuItemService {

    // ── CRUD Operations ──
    KitchenMenuItemDTO createMenuItem(KitchenMenuItemDTO menuItemDTO);

    KitchenMenuItemDTO getMenuItemById(Long id);

    List<KitchenMenuItemDTO> getAllMenuItems();

    KitchenMenuItemDTO updateMenuItem(Long id, KitchenMenuItemDTO menuItemDTO);

    void deleteMenuItem(Long id);

    // ── Filtering & Searching ──
    List<KitchenMenuItemDTO> getMenuItemsByRestaurant(Long restaurantId);

    List<KitchenMenuItemDTO> getMenuItemsByMealType(String mealType);

    List<KitchenMenuItemDTO> getMenuItemsByServiceType(String serviceType);

    List<KitchenMenuItemDTO> getMenuItemsByDate(LocalDate menuDate);

    List<KitchenMenuItemDTO> searchMenuItemsByName(String name);

    List<KitchenMenuItemDTO> getMenuItemsByCategory(String category);

    List<KitchenMenuItemDTO> getMenuItemsByPriceRange(Double minPrice, Double maxPrice);

    List<KitchenMenuItemDTO> getAvailableMenuItems();

    long countAvailableMenuItems();

    // ── Service-specific menu views ──
    List<KitchenMenuItemDTO> getAvailableMenuByServiceType(String serviceType);

    List<KitchenMenuItemDTO> getRestaurantMenuItems(Long restaurantId);

    List<KitchenMenuItemDTO> getEventMenuItems();

    // ── Combined filters ──
    List<KitchenMenuItemDTO> getMenuItemsByRestaurantAndDate(Long restaurantId, LocalDate menuDate);

    List<KitchenMenuItemDTO> getMenuItemsByRestaurantAndMealType(Long restaurantId, String mealType);

    List<KitchenMenuItemDTO> getMenuItemsByFilters(String mealType, String serviceType, LocalDate menuDate);

    // ── Availability toggle ──
    KitchenMenuItemDTO toggleAvailability(Long id);
}
