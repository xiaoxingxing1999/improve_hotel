package com.example.hotel.po.Coupon;
import com.example.hotel.Form.HotelManager.Coupon.BirthdayCoupon;
import com.example.hotel.Form.HotelManager.Coupon.RoomCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TargetMoneyCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TimeCoupon;
import com.example.hotel.enums.Coupon.Type;
import com.example.hotel.enums.Coupon.CouponStatus;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon_new4")
public class coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String description;
    private Integer hotelId;//-1表示网站推出
    private CouponType couponType;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String couponName;
    private Double targetMoney;//TargetMoney还可以是房间数量
    @Column(columnDefinition = "double NULL")
    private Double discount;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL")
    private CouponStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Column(columnDefinition = "double NULL")
    private Double discountMoney;
    private Integer type;

    public coupon() {
    }

    public coupon(TargetMoneyCoupon targetMoneyCoupon){
        this.id = -1;
        this.description = targetMoneyCoupon.getDescription();
        this.hotelId = targetMoneyCoupon.getHotelId();
        this.couponType = CouponType.MONEY;
        this.couponName = targetMoneyCoupon.getCouponName();
        this.targetMoney = targetMoneyCoupon.getTarget();
        this.discount = 0.0;
        this.status = CouponStatus.VALID;
        this.startTime = TimeHelper.getDefaultLocalDateTime();
        this.endTime = TimeHelper.getDefaultLocalDateTime();
        this.discountMoney = targetMoneyCoupon.getDiscountMoney();
        this.type = Type.MANJIAN;
    }

    public coupon(RoomCoupon roomCoupon){
        this.id = -1;
        this.description = roomCoupon.getDescription();
        this.hotelId = roomCoupon.getHotelId();
        this.couponType = CouponType.ROOMS;
        this.couponName = roomCoupon.getCouponName();
        this.targetMoney = roomCoupon.getTarget();
        this.discount = roomCoupon.getDiscount();
        this.status = CouponStatus.VALID;
        this.startTime = TimeHelper.getDefaultLocalDateTime();
        this.endTime = TimeHelper.getDefaultLocalDateTime();
        this.discountMoney = roomCoupon.getDiscountMoney();
        this.type = roomCoupon.getType();
    }

    public coupon(TimeCoupon timeCoupon){
        this.id = -1;
        this.description = timeCoupon.getDescription();
        this.hotelId = timeCoupon.getHotelId();
        this.couponType = CouponType.TIME;
        this.couponName = timeCoupon.getCouponName();
        this.targetMoney = timeCoupon.getTarget();
        this.discount = timeCoupon.getDiscount();
        this.discountMoney = timeCoupon.getDiscountMoney();
        this.status = CouponStatus.VALID;
        this.startTime = TimeHelper.getLocalDateTime(timeCoupon.getStartTime());
        this.endTime = TimeHelper.getLocalDateTime(timeCoupon.getEndTime());
        this.discountMoney = timeCoupon.getDiscountMoney();
        this.type = timeCoupon.getType();
    }

    public coupon(BirthdayCoupon birthdayCoupon){
        this.id = -1;
        this.description = birthdayCoupon.getDescription();
        this.hotelId = birthdayCoupon.getHotelId();
        this.couponType = CouponType.BIRTHDAY;
        this.couponName = birthdayCoupon.getCouponName();
        this.targetMoney = birthdayCoupon.getTarget();
        this.discount = birthdayCoupon.getDiscount();
        this.discountMoney = birthdayCoupon.getDiscountMoney();
        this.status = CouponStatus.VALID;
        this.startTime = TimeHelper.getDefaultLocalDateTime();
        this.endTime = TimeHelper.getDefaultLocalDateTime();
        this.discountMoney = birthdayCoupon.getDiscountMoney();
        this.type = birthdayCoupon.getType();
    }

    public coupon(Integer id, String description, Integer hotelId, CouponType couponType, String couponName, double targetMoney, double discount, CouponStatus status, LocalDateTime startTime, LocalDateTime endTime, double discountMoney, Integer type) {
        this.id = id;
        this.description = description;
        this.hotelId = hotelId;
        this.couponType = couponType;
        this.couponName = couponName;
        this.targetMoney = targetMoney;
        this.discount = discount;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.discountMoney = discountMoney;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Double getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Double targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CouponStatus getStatus() {
        return status;
    }

    public void setStatus(CouponStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
