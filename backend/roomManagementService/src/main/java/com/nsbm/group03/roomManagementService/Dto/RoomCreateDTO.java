package com.nsbm.group03.roomManagementService.Dto;

import com.nsbm.group03.roomManagementService.Enum.RoomType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO for creating a new room (without status, which defaults to AVAILABLE)
public class RoomCreateDTO {

    @NotBlank 
    private String roomNumber;

    @NotNull 
    private RoomType roomType;

    @Min(0)
    private double pricePerNight;

    @Min(1)
    private int capacity;

    public RoomCreateDTO() {}

    public RoomCreateDTO( String roomNumber, RoomType roomType, double pricePerNight, int capacity) {
        
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
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
}