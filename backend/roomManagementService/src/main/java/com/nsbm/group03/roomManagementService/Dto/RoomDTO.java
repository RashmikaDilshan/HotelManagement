package com.nsbm.group03.roomManagementService.Dto;

import com.nsbm.group03.roomManagementService.Enum.RoomType;
import com.nsbm.group03.roomManagementService.Enum.RoomStatus;

// DTO for transferring room data (includes status)
public class RoomDTO {

    private String roomNumber;
    private RoomType roomType;
    private double pricePerNight;
    private int capacity;
    private RoomStatus status;

    public RoomDTO() { }

    public RoomDTO(String roomNumber, RoomType roomType, double pricePerNight, int capacity, RoomStatus status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.status = status;
    }

    // Getters & Setters
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
}