package com.nsbm.group03.roomManagementService.Dto;

import com.nsbm.group03.roomManagementService.Enum.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomStatusUpdateDTO {

    @NotNull
    private String roomId;

    @NotBlank
    private String roomNumber;

    @NotNull
    private RoomStatus status;

    public RoomStatusUpdateDTO() {}

    public RoomStatusUpdateDTO(String roomId,String roomNumber, RoomStatus status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.status = status;
    }

    // Getters & Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
}