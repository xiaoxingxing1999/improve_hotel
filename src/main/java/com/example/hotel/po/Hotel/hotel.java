package com.example.hotel.po.Hotel;

import com.example.hotel.Form.Manager.HotelForm;
import com.example.hotel.enums.HotelStar;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_new4")
public class hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelName;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelDescription;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String address;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String bizRegion;
    private HotelStar hotelStar;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String phoneNum;
    private double rate;
    private Integer managerId;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelServe;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String picture;
    private double longitude;
    private double latitude;
    private LocalDateTime createTime;

    public hotel() {
    }

    public hotel(HotelForm hotelForm){
        this.id = -1;
        this.hotelName = hotelForm.getHotelName();
        this.hotelDescription = hotelForm.getHotelDescription();
        this.address = hotelForm.getAddress();
        this.bizRegion = hotelForm.getBizRegion();
        this.hotelStar = hotelForm.getHotelStar();
        this.phoneNum = hotelForm.getPhoneNum();
        this.rate = 0;
        this.managerId = -1;
        this.hotelServe = hotelForm.getHotelServe();
        this.picture = hotelForm.getPicture();
        this.longitude = hotelForm.getLongitude();
        this.latitude = hotelForm.getLatitude();
        this.createTime = LocalDateTime.now();
    }

    public hotel(Integer id, String hotelName, String hotelDescription, String address, String bizRegion, HotelStar hotelStar, String phoneNum, double rate, Integer managerId, String hotelServe, String picture, double longitude, double latitude, LocalDateTime createTime) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.address = address;
        this.bizRegion = bizRegion;
        this.hotelStar = hotelStar;
        this.phoneNum = phoneNum;
        this.rate = rate;
        this.managerId = managerId;
        this.hotelServe = hotelServe;
        this.picture = picture;
        this.longitude = longitude;
        this.latitude = latitude;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }

    public HotelStar getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(HotelStar hotelStar) {
        this.hotelStar = hotelStar;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelServe() {
        return hotelServe;
    }

    public void setHotelServe(String hotelServe) {
        this.hotelServe = hotelServe;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                ", address='" + address + '\'' +
                ", bizRegion='" + bizRegion + '\'' +
                ", hotelStar=" + hotelStar +
                ", phoneNum='" + phoneNum + '\'' +
                ", rate=" + rate +
                ", managerId=" + managerId +
                ", hotelServe='" + hotelServe + '\'' +
                ", picture='" + picture + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", createTime=" + createTime +
                '}';
    }
}

