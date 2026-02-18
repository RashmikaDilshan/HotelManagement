package com.nsbm.group03.roomManagementService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nsbm.group03.roomManagementService.Entity.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
   
    

}
