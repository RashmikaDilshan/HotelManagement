package com.nsbm.group03.roomManagementService.Mapper;

import com.nsbm.group03.roomManagementService.Entity.Room;
import com.nsbm.group03.roomManagementService.Enum.RoomStatus;
import com.nsbm.group03.roomManagementService.Dto.*;

import java.util.stream.Collectors;
import java.util.List;

// Mapper class to convert between Room entity and various DTOs
public class RoomMapper {

    // Entity → RoomDTO
    public static RoomDTO toRoomDTO(Room room) {
        if (room == null) return null;
        return new RoomDTO(
            room.getRoomNumber(),
            room.getRoomType(),
            room.getPricePerNight(),
            room.getCapacity(),
            room.getStatus()
        );
    }

    // Entity → RoomAvailabilityDTO
    public static RoomAvailabilityDTO toRoomAvailabilityDTO(Room room) {
        if (room == null) return null;
        return new RoomAvailabilityDTO(
            room.getRoomNumber(),
            room.getRoomType(),
            room.getPricePerNight()
        );
    }

    // DTO → Entity
    public static Room toEntity(RoomDTO dto) {
        if (dto == null) return null;
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setStatus(dto.getStatus());
        return room;
    }

    // RoomCreateDTO → Entity
    public static Room toEntity(RoomCreateDTO dto) {
        if (dto == null) return null;
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setRoomType(dto.getRoomType());
        room.setPricePerNight(dto.getPricePerNight());
        room.setCapacity(dto.getCapacity());
        room.setStatus(RoomStatus.AVAILABLE);
        return room;
    }

    // RoomStatusUpdateDTO → Entity (for update)
    public static Room toEntity(RoomStatusUpdateDTO dto) {
        if (dto == null) return null ;
        Room room = new Room();        
        room.setRoomNumber(dto.getRoomNumber());
        room.setStatus(dto.getStatus());
        return room;
    }

    // Batch mapping 
    public static List<RoomDTO> toRoomDTOList(List<Room> rooms) {
        return rooms.stream().map(RoomMapper::toRoomDTO).collect(Collectors.toList());
    }

    public static List<RoomAvailabilityDTO> toRoomAvailabilityDTOList(List<Room> rooms) {
        return rooms.stream().map(RoomMapper::toRoomAvailabilityDTO).collect(Collectors.toList());
    }
}