package com.nsbm.group03.roomManagementService.Entity;

import java.util.UUID;

import com.nsbm.group03.roomManagementService.Enum.RoomStatus;
import com.nsbm.group03.roomManagementService.Enum.RoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;


@Entity
public class Room {

    @Id    
    private String roomId;
    
    @Column(unique = true, nullable = false)
    private String roomNumber;
    private double pricePerNight;
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @PrePersist
    public void generateId() {
        this.roomId = UUID.randomUUID().toString();
    }
    
    public Room() {
    }

    public Room(String roomId, String roomNumber, RoomType roomType, double pricePerNight, int capacity, RoomStatus status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;
    }

    

    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public RoomStatus getStatus() {
        return status;
    }
    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomType=" + roomType + ", pricePerNight="
                + pricePerNight + ", capacity=" + capacity + ", status=" + status + "]";
    }   

}
