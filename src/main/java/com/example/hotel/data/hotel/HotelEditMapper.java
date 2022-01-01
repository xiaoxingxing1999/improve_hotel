package com.example.hotel.data.hotel;

import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.po.Hotel.hoteledit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

public interface HotelEditMapper extends JpaRepository<hoteledit, Integer> {

    @Query(value = "Select * from hoteledit_new4 where hotel_id = :hotelId", nativeQuery = true)
    ArrayList<hoteledit> findByHotel(Integer hotelId);
    @Query(value = "Select * from hoteledit_new4 where status = :status", nativeQuery = true)
    ArrayList<hoteledit> findByStatus(Integer status);
    @Query(value = "Select * from hoteledit_new4", nativeQuery = true)
    ArrayList<hoteledit> findAllEdits();

    @Transactional
    @Modifying
    @Query(value = "Update hoteledit_new4 " +
            "set status = :status, reply_content = :replyContent, reply_time = :replyTime " +
            "where id = :id", nativeQuery = true)
    int reply(Integer status, String replyContent, LocalDateTime replyTime, Integer id);
}
