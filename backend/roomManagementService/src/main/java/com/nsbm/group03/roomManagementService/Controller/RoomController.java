package com.nsbm.group03.roomManagementService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsbm.group03.roomManagementService.Entity.Room;
import com.nsbm.group03.roomManagementService.Mapper.RoomMapper;
import com.nsbm.group03.roomManagementService.Service.RoomService;
import com.nsbm.group03.roomManagementService.Dto.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // GET all rooms
    @GetMapping
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return RoomMapper.toRoomDTOList(rooms);
    }

    // GET a room by room number if not return invalid room number
    @GetMapping("/{roomNumber}")
    public RoomDTO getRoomByNumber(@PathVariable String roomNumber) {
        Room room = roomService.getRoomByNumber(roomNumber);
        if (room == null) {
            throw new RuntimeException("Invalid room number: " + roomNumber);
        }
        return RoomMapper.toRoomDTO(room);
        
    }

    // POST create room
    @PostMapping
    public RoomDTO createRoom(@RequestBody RoomCreateDTO createDTO) {
        Room room = RoomMapper.toEntity(createDTO);
        Room savedRoom = roomService.insertRoom(room);
        return RoomMapper.toRoomDTO(savedRoom);
    }

    // DELETE a room by room number
    @GetMapping("/delete/{roomNumber}")
    public String deleteRoom(@PathVariable String roomNumber) {
        roomService.deleteRoom(roomNumber);
        return "Room with number " + roomNumber + " has been deleted.";
    }

    // PATCH update room status
    @PatchMapping("/updateStatus")
    public RoomDTO updateRoomStatus(@RequestBody RoomStatusUpdateDTO statusDTO) {
        Room room = RoomMapper.toEntity(statusDTO);
        Room updatedRoom = roomService.updateRoomStatus(room);
        return RoomMapper.toRoomDTO(updatedRoom);
    }

    // GET available rooms
    @GetMapping("/available") 
    public List<RoomAvailabilityDTO> getAvailableRooms() {
        List<Room> availableRooms = roomService.getAvailableRooms();
        return RoomMapper.toRoomAvailabilityDTOList(availableRooms);
    }
}