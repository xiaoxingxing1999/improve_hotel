package com.example.hotel.blImpl.Coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.stereotype.Service;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

@Service
public class RoomCouponStrategyImpl implements CouponMatchStrategy {

    @Override
    public boolean isMatch(hotelorder hotelorder, coupon coupon)  throws ServiceException {
        //TargetMoney还可以是房间数量
        if (coupon.getCouponType().equals(CouponType.ROOMS)
                && hotelorder.getRoomNum() >= coupon.getTargetMoney()) {
            return true;
        }
        return false;
    }
}
