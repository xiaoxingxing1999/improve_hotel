package com.example.hotel.blImpl.Coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import org.springframework.stereotype.Service;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param hotelorderVO
     * @return
     */
    @Override
    public boolean isMatch(hotelorder hotelorderVO, coupon coupon) throws ServiceException {
        if (coupon.getCouponType().equals(CouponType.TIME)) {
            long start = 0, end = 0, now = -1;
            try{
                start = TimeHelper.getTimeStamp(coupon.getStartTime());
                end = TimeHelper.getTimeStamp(coupon.getEndTime());
                now = System.currentTimeMillis();
            }catch(Exception e){
                e.printStackTrace();
                throw new ServiceException("格式化时间错误");
            }
            if((start <= now) && (now <= end)){
                return true;
            }
        }
        return false;
    }
}
