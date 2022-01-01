package com.example.hotel.blImpl.Coupon;

import com.example.hotel.bl.UserService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

@Service
public class BirthDayCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    UserService userService;
    @Override
    public boolean isMatch(hotelorder hotelorder, coupon coupon) throws ServiceException {
        String today = TimeHelper.dateToString(LocalDateTime.now());
        user user = userService.findById(hotelorder.getUserId());
        if(VIPType.isVip(user.getVipType())){
            return today.equals(user.getVipInfo());
        }
        return false;
    }
}
