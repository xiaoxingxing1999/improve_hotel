package com.example.hotel.controller;

import com.example.hotel.Form.HotelManager.Coupon.BirthdayCoupon;
import com.example.hotel.Form.HotelManager.Coupon.RoomCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TargetMoneyCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TimeCoupon;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.Response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @PostMapping("/hotelTargetMoney")
    public SimpleResponse addHotelTargetMoney(@RequestBody TargetMoneyCoupon targetMoneyCoupon) throws ServiceException {
        System.out.println("POST./api/coupon/hotelTargetMoney:" + targetMoneyCoupon.toString());
        boolean result = couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        return result ? SimpleResponse.ok("新建满减策略成功"):
                SimpleResponse.error("新建满减策略失败");
    }

    @PostMapping("/time")
    public SimpleResponse addTimeCoupon(@RequestBody TimeCoupon timeCoupon) throws ServiceException{
        System.out.println("POST./api/coupon/time:" + timeCoupon.toString());
        boolean result = couponService.addTimeCoupon(timeCoupon);
        return result ? SimpleResponse.ok("新建限时策略成功"):
                SimpleResponse.error("新建限时策略失败");
    }

    @PostMapping("/birthday")
    public SimpleResponse addBirthdayCoupon(@RequestBody BirthdayCoupon birthdayCoupon) throws ServiceException{
        System.out.println("POST./api/coupon/birthday:" + birthdayCoupon.toString());
        boolean result = couponService.addBirthdayCoupon(birthdayCoupon);
        return result ? SimpleResponse.ok("新建生日策略成功"):
                SimpleResponse.error("新建生日策略失败");
    }

    @PostMapping("/room")
    public SimpleResponse hotelTargetMoney(@RequestBody RoomCoupon roomCoupon) throws ServiceException{
        System.out.println("POST./api/coupon/room:" + roomCoupon.toString());
        boolean result = couponService.addRoomCoupon(roomCoupon);
        return result ? SimpleResponse.ok("新建多间策略成功"):
                SimpleResponse.error("新建多间策略失败");
    }

    @PostMapping("/unable")
    public SimpleResponse unableCoupon(@RequestParam String token,
                                       @RequestParam Integer couponId) throws ServiceException{
        System.out.println("POST./api/coupon/unable?token=" + token + "&couponId=" + couponId);
        boolean result = couponService.unableCoupon(token, couponId);
        return result ? SimpleResponse.ok("失效优惠券成功"):
                SimpleResponse.error("失效优惠券失败");
    }
}
