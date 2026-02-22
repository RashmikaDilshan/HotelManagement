package com.nsbm.group03.kitchenManagementService.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsbm.group03.kitchenManagementService.entity.KitchenMenuItem;
import com.nsbm.group03.kitchenManagementService.enums.MealType;
import com.nsbm.group03.kitchenManagementService.enums.ServiceType;

@Repository
public interface KitchenMenuItemRepository extends JpaRepository<KitchenMenuItem, Long> {

    // ── Filter by restaurant ──
    List<KitchenMenuItem> findByRestaurantId(Long restaurantId);

    // ── Filter by meal type ──
    List<KitchenMenuItem> findByMealType(MealType mealType);

    // ── Filter by service type ──
    List<KitchenMenuItem> findByServiceType(ServiceType serviceType);

    // ── Filter by date ──
    List<KitchenMenuItem> findByMenuDate(LocalDate menuDate);

    // ── Search by item name (case-insensitive) ──
    List<KitchenMenuItem> findByItemNameContainingIgnoreCase(String itemName);

    // ── Filter by category ──
    List<KitchenMenuItem> findByCategory(String category);

    // ── Filter by price range ──
    List<KitchenMenuItem> findByPriceBetween(Double minPrice, Double maxPrice);

    // ── Get only available menus ──
    List<KitchenMenuItem> findByAvailableTrue();

    // ── Count available menus (dashboard) ──
    long countByAvailableTrue();

    // ── Service-specific: available menus by service type ──
    List<KitchenMenuItem> findByServiceTypeAndAvailableTrue(ServiceType serviceType);

    // ── Combined filters ──
    List<KitchenMenuItem> findByRestaurantIdAndMenuDate(Long restaurantId, LocalDate menuDate);

    List<KitchenMenuItem> findByRestaurantIdAndMealType(Long restaurantId, MealType mealType);

    List<KitchenMenuItem> findByRestaurantIdAndServiceType(Long restaurantId, ServiceType serviceType);

    List<KitchenMenuItem> findByRestaurantIdAndAvailableTrue(Long restaurantId);

    List<KitchenMenuItem> findByMealTypeAndServiceTypeAndMenuDateAndAvailableTrue(
            MealType mealType, ServiceType serviceType, LocalDate menuDate);
}
