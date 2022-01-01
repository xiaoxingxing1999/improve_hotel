package com.example.hotel.Form.HotelManager.Coupon;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class TimeCoupon {
    private String description;
    private Integer hotelId;
    private String couponName;
    private double target;
    private double discount;
    private long startTime;
    private long endTime;
    private double discountMoney;
    private Integer type;
    private String token;

    public TimeCoupon() {
    }

    public TimeCoupon(String description, Integer hotelId, String couponName, double target, double discount, long startTime, long endTime, double discountMoney, Integer type, String token) {
        this.description = description;
        this.hotelId = hotelId;
        this.couponName = couponName;
        this.target = target;
        this.discount = discount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountMoney = discountMoney;
        this.type = type;
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

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TimeCoupon{" +
                "description='" + description + '\'' +
                ", hotelId=" + hotelId +
                ", couponName='" + couponName + '\'' +
                ", target=" + target +
                ", discount=" + discount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", discountMoney=" + discountMoney +
                ", type=" + type +
                ", token='" + token + '\'' +
                '}';
    }
}
