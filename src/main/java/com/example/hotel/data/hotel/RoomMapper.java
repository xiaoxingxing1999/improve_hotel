package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotelroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;

public interface RoomMapper extends JpaRepository<hotelroom, Integer> {

    @Query(value = "Select cur_num from hotelroom_new4 where hotel_id=:hotelId and room_type=:roomType limit 1",nativeQuery = true)
    String getRoomCurNum(Integer hotelId, String roomType);
    @Query(value = "Select * from hotelroom_new4 where hotel_id = :hotelId and room_type = :roomType",nativeQuery = true)
    hotelroom findByHotelAndType(Integer hotelId, String roomType);
    @Query(value = "Select * from hotelroom_new4 where hotel_id = :hotelId",nativeQuery = true)
    List<hotelroom> findRoomsByHotelId(Integer hotelId);

    @Transactional
    @Modifying
    @Query(value = "Update hotelroom_new4 " +
            "set cur_num = :AfterNum where hotel_id = :hotelId and room_type = :roomType",nativeQuery = true)
    void updateRoomInfo(Integer hotelId, String roomType, String AfterNum);

    @Transactional
    @Modifying
    @Query(value = "Delete from hotelroom_new4 where id = :id",nativeQuery = true)
    int delRoom(Integer id);
}
