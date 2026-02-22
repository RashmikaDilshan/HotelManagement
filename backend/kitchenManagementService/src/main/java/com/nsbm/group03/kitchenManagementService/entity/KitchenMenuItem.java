package com.nsbm.group03.kitchenManagementService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.nsbm.group03.kitchenManagementService.enums.MealType;
import com.nsbm.group03.kitchenManagementService.enums.ServiceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitchen_menu_items")
public class KitchenMenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Column(nullable = false)
    private LocalDate menuDate;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(length = 500)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ── Lifecycle callbacks ──
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ── Constructors ──
    public KitchenMenuItem() {
    }

    public KitchenMenuItem(String itemName, String category, Double price, boolean available,
                           MealType mealType, ServiceType serviceType, LocalDate menuDate,
                           Long restaurantId, String description) {
        this.itemName = itemName;
        this.category = category;
        this.price = price;
        this.available = available;
        this.mealType = mealType;
        this.serviceType = serviceType;
        this.menuDate = menuDate;
        this.restaurantId = restaurantId;
        this.description = description;
    }

    // ── Getters and Setters ──
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(LocalDate menuDate) {
        this.menuDate = menuDate;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "KitchenMenuItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", mealType=" + mealType +
                ", serviceType=" + serviceType +
                ", menuDate=" + menuDate +
                ", restaurantId=" + restaurantId +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
