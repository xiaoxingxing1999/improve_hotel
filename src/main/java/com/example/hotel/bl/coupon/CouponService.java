package com.example.hotel.bl.coupon;

import com.example.hotel.Form.HotelManager.Coupon.BirthdayCoupon;
import com.example.hotel.Form.HotelManager.Coupon.RoomCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TargetMoneyCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TimeCoupon;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface CouponService {

    /**
     * 添加满减优惠策略
     * @param targetMoneyCoupon
     * @return
     * @throws ServiceException
     */
    boolean addHotelTargetMoneyCoupon(TargetMoneyCoupon targetMoneyCoupon) throws ServiceException;

    /**
     * 添加限时优惠策略
     * @param timeCoupon
     * @return
     * @throws ServiceException
     */
    boolean addTimeCoupon(TimeCoupon timeCoupon) throws ServiceException;

    /**
     * 添加生日优惠策略
     * @param birthdayCoupon
     * @return
     * @throws ServiceException
     */
    boolean addBirthdayCoupon(BirthdayCoupon birthdayCoupon) throws ServiceException;

    /**
     * 添加多间优惠策略
     * @param roomCoupon
     * @return
     * @throws ServiceException
     */
    boolean addRoomCoupon(RoomCoupon roomCoupon) throws ServiceException;

    /**
     * 失效优惠券
     * @param token
     * @param couponId
     * @return
     * @throws ServiceException
     */
    boolean unableCoupon(String token, Integer couponId) throws ServiceException;

    /**
     * 返回某一订单可用的优惠策略列表
     * @param hotelorder
     * @return
     * @throws ServiceException
     */
    List<coupon> getMatchOrderCoupon(hotelorder hotelorder) throws ServiceException;

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<coupon> getHotelAllCoupon(Integer hotelId) throws ServiceException;

    /**
     * 查看网站营销人员的所有的优惠券（包含失效优惠券)
     * @return
     * @throws ServiceException
     */
    List<coupon> getSiteAllCoupon() throws ServiceException;

    /**
     * 查看所有的优惠券
     * @return
     * @throws ServiceException
     */
    List<coupon> getAllCoupon() throws ServiceException;
}
