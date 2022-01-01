package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotelsituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/19
 * @Version 1.0
 **/

public interface HotelSituationMapper extends JpaRepository<hotelsituation, Integer> {
    @Query(value = "Select * from hotelsituation_new4 where hotel_Id = :hotelId order by id DESC limit 1"
            , nativeQuery = true)
    hotelsituation findLatestHotel(Integer hotelId);
    @Query(value = "Select * from hotelsituation_new4 where hotel_id = :hotelId", nativeQuery = true)
    List<hotelsituation> selectByHotelId(Integer hotelId);

    @Query(value = "Select * from hotelsituation_new4", nativeQuery = true)
    List<hotelsituation> selectAll();

    @Transactional
    @Modifying
    @Query(value = "Update hotelsituation_new4 " +
            "set client_num = client_num + :clientNum " +
            "where hotel_id = :hotelId and today = :today", nativeQuery = true)
    int updateClientNum(Integer hotelId, String today, Integer clientNum);

    @Transactional
    @Modifying
    @Query(value = "Update hotelsituation_new4 " +
            "set vip_num = vip_num + :VIPNum " +
            "where hotel_id = :hotelId and today = :today", nativeQuery = true)
    int updateVIPNum(Integer hotelId, String today, Integer VIPNum);

    @Transactional
    @Modifying
    @Query(value = "Update hotelsituation_new4 " +
            "set order_done_num = order_done_num + :orderDoneNum " +
            "where hotel_id = :hotelId and today = :today", nativeQuery = true)
    int updateOrderDoneNum(Integer hotelId, String today, Integer orderDoneNum);

    @Transactional
    @Modifying
    @Query(value = "Update hotelsituation_new4 " +
            "set order_bad_num = order_bad_num + :orderBadNum " +
            "where hotel_id = :hotelId and today = :today", nativeQuery = true)
    int updateOrderBadNum(Integer hotelId, String today, Integer orderBadNum);

    @Transactional
    @Modifying
    @Query(value = "Update hotelsituation_new4 " +
            "set total_client_num = total_client_num + :totalClientNum " +
            "where hotel_id = :hotelId and today = :today", nativeQuery = true)
    int updateTotalClientNum(Integer hotelId, String today, Integer totalClientNum);
}