package com.example.hotel.data.coupon;

import com.example.hotel.enums.Coupon.CouponStatus;
import com.example.hotel.po.Coupon.coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CouponMapper extends JpaRepository<coupon, Integer> {

    @Query(value = "Select * from coupon_new4 where id = :id limit 1" ,nativeQuery = true)
    coupon selectById(Integer id);
    @Query(value = "Select * from coupon_new4 where hotel_id = :hotelId",nativeQuery = true)
    List<coupon> selectByHotelId(Integer hotelId);
    @Query(value = "Select * from coupon_new4 where hotel_id = -1",nativeQuery = true)
    List<coupon> selectBySite();

    @Transactional
    @Modifying
    @Query(value = "Update coupon_new4 set status = :status where id = :id",nativeQuery = true)
    int updateStatus(Integer id, String status);
}
