package com.nsbm.group03.roomManagementService.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsbm.group03.roomManagementService.Entity.Room;
import com.nsbm.group03.roomManagementService.Enum.RoomStatus;
import com.nsbm.group03.roomManagementService.Repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    //View all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    //Create a room
    public Room insertRoom(Room room) {
        return roomRepository.save(room);        
    }

    //Update a room status with room number
    public Room updateRoomStatus(Room room) { 
        Room existingRoom = roomRepository.findByRoomNumber(room.getRoomNumber());
        if (existingRoom != null) {
            existingRoom.setStatus(room.getStatus());
            return roomRepository.save(existingRoom);
        }
        return null;       
    }

    //Get only available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus(RoomStatus.AVAILABLE);        
    }

    //Get a room by room number  
    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    //Delete a room by room number
    public void deleteRoom(String roomNumber) {
        Room room = getRoomByNumber(roomNumber);
        if (room != null) {
            roomRepository.delete(room);
        }
    }

}
