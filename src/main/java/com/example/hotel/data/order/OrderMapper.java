package com.example.hotel.data.order;

import com.example.hotel.enums.OrderState;
import com.example.hotel.po.Order.hotelorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */

public interface OrderMapper extends JpaRepository<hotelorder,Integer> {

    @Query(value = "Select * from hotelorder_new4 where user_id = :userId",nativeQuery = true)
    List<hotelorder> findByUser(Integer userId);
    @Query(value = "Select * from hotelorder_new4 where hotel_id = :hotelId",nativeQuery = true)
    List<hotelorder> findByHotel(Integer hotelId);

    @Query(value = "Select * from hotelorder_new4 where hotel_id = :hotelId and user_id = :userId",nativeQuery = true)
    List<hotelorder> findByUserAndHotel(Integer userId, Integer hotelId);
    @Query(value = "Select * from hotelorder_new4",nativeQuery = true)
    List<hotelorder> findAllOrders();

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set check_in_date = :checkInDate, check_out_date = :checkOutDate" +
            ", have_child = :haveChild, people_num = :peopleNum " +
            "where id = :id",nativeQuery = true)
    int updateOrder(LocalDateTime checkInDate, LocalDateTime checkOutDate, boolean haveChild, Integer peopleNum, Integer id);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set order_state = :orderState where id = :id", nativeQuery = true)
    int updateOrderState(Integer id, Integer orderState);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_pass_date = :realPassDate where id = :id", nativeQuery = true)
    int updatePassDate(Integer id, LocalDateTime realPassDate);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_refuse_date = :realRefuseDate where id = :id", nativeQuery = true)
    int updateRefuseDate(Integer id, LocalDateTime realRefuseDate);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_cancel_date = :realCancelDate where id = :id", nativeQuery = true)
    int updateCancelDate(Integer id, LocalDateTime realCancelDate);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_check_in_date = :realCheckInDate where id = :id", nativeQuery = true)
    int updateCheckInDate(Integer id, LocalDateTime realCheckInDate);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_check_out_date = :realCheckOutDate where id = :id", nativeQuery = true)
    int updateCheckOutDate(Integer id, LocalDateTime realCheckOutDate);

    @Transactional
    @Modifying
    @Query(value = "Update hotelorder_new4 set real_error_date = :realErrorDate where id = :id", nativeQuery = true)
    int updateErrorDate(Integer id, LocalDateTime realErrorDate);

    @Query(value = "Select * from hotelorder_new4 where hotel_id = :hotelId and room_type = :roomType",nativeQuery = true)
    List<hotelorder> findByRoom(Integer hotelId, String roomType);
}
