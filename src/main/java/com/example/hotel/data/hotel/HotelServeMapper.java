package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotelserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

public interface HotelServeMapper extends JpaRepository<hotelserve, Integer> {

    @Query(value = "Select * from hotelserve_new4 where name = :name",nativeQuery = true)
    hotelserve selectByName(String name);
    @Query(value = "Select * from hotelserve_new4",nativeQuery = true)
    ArrayList<hotelserve> selectAllNames();
}
