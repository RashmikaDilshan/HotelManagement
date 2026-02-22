package com.nsbm.group03.kitchenManagementService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nsbm.group03.kitchenManagementService.entity.KitchenOrderItem;

@Repository
public interface KitchenOrderItemRepository extends JpaRepository<KitchenOrderItem, Long> {

    // ── Find items by order ID ──
    List<KitchenOrderItem> findByKitchenOrderId(Long kitchenOrderId);
}
