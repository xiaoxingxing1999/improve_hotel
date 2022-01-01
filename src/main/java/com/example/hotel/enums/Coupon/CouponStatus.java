package com.example.hotel.enums.Coupon;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

public enum  CouponStatus {
    VALID("生效中"),
    INVALID("已失效");

    private String value;

    CouponStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
