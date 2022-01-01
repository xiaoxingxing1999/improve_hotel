package com.example.hotel.enums.Coupon;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

public enum CouponType{
    BIRTHDAY("生日优惠券"),
    ROOMS("多间优惠券"),
    MONEY("满减优惠券"),
    TIME("限时优惠券");

    private String value;

    CouponType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
