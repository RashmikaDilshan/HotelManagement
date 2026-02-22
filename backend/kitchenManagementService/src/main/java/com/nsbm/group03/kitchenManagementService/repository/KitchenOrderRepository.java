package com.nsbm.group03.kitchenManagementService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsbm.group03.kitchenManagementService.entity.KitchenOrder;
import com.nsbm.group03.kitchenManagementService.enums.OrderStatus;

@Repository
public interface KitchenOrderRepository extends JpaRepository<KitchenOrder, Long> {

    // ── Filter by order status ──
    List<KitchenOrder> findByOrderStatus(OrderStatus orderStatus);

    // ── Filter by assigned staff ──
    List<KitchenOrder> findByStaffId(Long staffId);

    // ── Filter by restaurant ──
    List<KitchenOrder> findByRestaurantId(Long restaurantId);

    // ── Count orders by status (dashboard) ──
    long countByOrderStatus(OrderStatus orderStatus);
}
