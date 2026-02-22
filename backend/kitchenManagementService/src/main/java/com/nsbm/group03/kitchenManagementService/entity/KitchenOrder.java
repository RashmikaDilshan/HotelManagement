package com.nsbm.group03.kitchenManagementService.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nsbm.group03.kitchenManagementService.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitchen_orders")
public class KitchenOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    private String tableNumber;

    private Long staffId; // assigned kitchen staff (from Employee Management Service)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(length = 500)
    private String specialInstructions;

    @Column(nullable = false)
    private Double totalAmount;

    @OneToMany(mappedBy = "kitchenOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KitchenOrderItem> orderItems = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ── Lifecycle callbacks ──
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (orderStatus == null) {
            orderStatus = OrderStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ── Constructors ──
    public KitchenOrder() {
    }

    public KitchenOrder(Long restaurantId, String tableNumber, Long staffId,
                        OrderStatus orderStatus, String specialInstructions,
                        Double totalAmount) {
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.staffId = staffId;
        this.orderStatus = orderStatus;
        this.specialInstructions = specialInstructions;
        this.totalAmount = totalAmount;
    }

    // ── Helper methods ──
    public void addOrderItem(KitchenOrderItem item) {
        orderItems.add(item);
        item.setKitchenOrder(this);
    }

    public void removeOrderItem(KitchenOrderItem item) {
        orderItems.remove(item);
        item.setKitchenOrder(null);
    }

    // ── Getters and Setters ──
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<KitchenOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<KitchenOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ── toString ──
    @Override
    public String toString() {
        return "KitchenOrder{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", tableNumber='" + tableNumber + '\'' +
                ", staffId=" + staffId +
                ", orderStatus=" + orderStatus +
                ", specialInstructions='" + specialInstructions + '\'' +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
