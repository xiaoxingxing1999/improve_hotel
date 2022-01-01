package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.roomtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

public interface RoomTypeMapper extends JpaRepository<roomtype, Integer> {

    @Query(value = "Select * from roomtype_new4 where name = :name",nativeQuery = true)
    roomtype selectByName(String name);
    @Query(value = "Select * from roomtype_new4",nativeQuery = true)
    List<roomtype> selectAllNames();
}
