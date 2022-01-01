package com.example.hotel.data.coupon;

import com.example.hotel.enums.Coupon.CouponStatus;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.po.Coupon.coupon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponMapperTest {
    @Autowired
    CouponMapper couponMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        coupon Coupon = new coupon(1,"save",1,
                CouponType.MONEY,"save",0.0,
                0.0, CouponStatus.VALID, LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),0.0,0);
        couponMapper.save(Coupon);
    }

    @Test
    @Transactional
    @Rollback
    public void selectById() {
        Assert.assertNotNull(couponMapper.selectById(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByHotelId() {
        Assert.assertNotNull(couponMapper.selectByHotelId(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectBySite() {
        Assert.assertNotNull(couponMapper.selectBySite());
    }

    @Test
    @Transactional
    @Rollback
    public void updateStatus() {
        couponMapper.updateStatus(1, String.valueOf(CouponStatus.INVALID.ordinal()));
        coupon Coupon = couponMapper.selectById(1);
        Assert.assertEquals(CouponStatus.INVALID,Coupon.getStatus());
    }
}