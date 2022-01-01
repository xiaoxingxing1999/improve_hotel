package com.example.hotel.data.bill;

import com.example.hotel.po.Bill.bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

public interface BillMapper extends JpaRepository<bill, Integer> {

    @Query(value = "Select * from bill_new4 where uid = :uid",nativeQuery = true)
    List<bill> findByUid(Integer uid);

    @Query(value = "Select * from bill_new4 where mid = :userId",nativeQuery = true)
    List<bill> findAll(Integer userId);
}
