package com.nsbm.group03.kitchenManagementService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "kitchen_order_items")
public class KitchenOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_order_id", nullable = false)
    private KitchenOrder kitchenOrder;

    @Column(nullable = false)
    private Long menuItemId;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double price;

    @Column(length = 300)
    private String notes;

    // ── Constructors ──
    public KitchenOrderItem() {
    }

    public KitchenOrderItem(Long menuItemId, String itemName, int quantity,
                            Double price, String notes) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.notes = notes;
    }

    // ── Getters and Setters ──
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KitchenOrder getKitchenOrder() {
        return kitchenOrder;
    }

    public void setKitchenOrder(KitchenOrder kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // ── toString ──
    @Override
    public String toString() {
        return "KitchenOrderItem{" +
                "id=" + id +
                ", menuItemId=" + menuItemId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                '}';
    }
}
