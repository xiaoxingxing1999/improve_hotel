package com.example.hotel.blImpl.Coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class TargetMoneyCouponStrategyImpl implements CouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     * @param hotelorderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(hotelorder hotelorderVO, coupon coupon)  throws ServiceException {
        if (coupon.getCouponType().equals(CouponType.MONEY)
                && hotelorderVO.getPrice() >= coupon.getTargetMoney()) {
            return true;
        }
        return false;
    }
}
