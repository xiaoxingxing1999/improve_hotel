package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/
public interface CouponMatchStrategy {

    /**
     * 检查当前订单情况有哪些优惠券满足
     * @param hotelorderVO
     * @param coupon
     * @return
     * @throws ServiceException
     */
    boolean isMatch(hotelorder hotelorderVO, coupon coupon)  throws ServiceException;
}
