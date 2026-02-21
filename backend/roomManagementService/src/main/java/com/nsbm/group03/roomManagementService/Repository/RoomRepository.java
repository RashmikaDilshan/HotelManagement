package com.nsbm.group03.roomManagementService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nsbm.group03.roomManagementService.Entity.Room;
import com.nsbm.group03.roomManagementService.Enum.RoomStatus;



public interface RoomRepository extends JpaRepository<Room, String> {

      List<Room> findByStatus(RoomStatus status);
      

}
