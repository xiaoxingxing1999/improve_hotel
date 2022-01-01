package com.example.hotel.Form.HotelManager.Coupon;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class TargetMoneyCoupon {
    private String description;
    private Integer hotelId;
    private String couponName;
    private double target;
    private double discountMoney;
    private String token;

    public TargetMoneyCoupon() {
    }

    public TargetMoneyCoupon(String description, Integer hotelId, String couponName, double target, double discountMoney, String token) {
        this.description = description;
        this.hotelId = hotelId;
        this.couponName = couponName;
        this.target = target;
        this.discountMoney = discountMoney;
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getCouponName() {
        return couponName;
    }


    public void setTarget(double target) {
        this.target = target;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getTarget() {
        return target;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TargetMoneyCoupon{" +
                "description='" + description + '\'' +
                ", hotelId=" + hotelId +
                ", couponName='" + couponName + '\'' +
                ", target=" + target +
                ", discountMoney=" + discountMoney +
                ", token='" + token + '\'' +
                '}';
    }
}
