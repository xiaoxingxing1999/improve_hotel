package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.bizregion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

public interface BizRegionMapper extends JpaRepository<bizregion, Integer> {

    @Query(value = "Select * from bizregion_new4 where name = :name limit 1",nativeQuery = true)
    bizregion findByName(String name);
    @Query(value = "Select * from bizregion_new4",nativeQuery = true)
    ArrayList<bizregion> findAllNames();
}
