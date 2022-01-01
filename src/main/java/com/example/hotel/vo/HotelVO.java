package com.example.hotel.vo;

import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hotelroom;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/28
 * @Version 1.0
 **/

public class HotelVO {
    private Integer id;
    private String hotelName;
    private String hotelDescription;
    private String address;
    private String bizRegion;
    private HotelStar hotelStar;
    private String phoneNum;
    private double rate;
    private Integer managerId;
    private String hotelServe;
    private String picture;
    private double longitude;
    private double latitude;
    private boolean isOrdered;

    private List<hotelroom> room;

    public HotelVO() {
    }

    public HotelVO(hotel hotel, List<hotelroom> room, boolean isOrdered){
        this.id = hotel.getId();
        this.hotelName = hotel.getHotelName();
        this.hotelDescription = hotel.getHotelDescription();
        this.address = hotel.getAddress();
        this.bizRegion = hotel.getBizRegion();
        this.hotelStar = hotel.getHotelStar();
        this.phoneNum = hotel.getPhoneNum();
        this.rate = hotel.getRate();
        this.managerId = hotel.getManagerId();
        this.hotelServe = hotel.getHotelServe();
        this.picture = hotel.getPicture();
        this.longitude = hotel.getLongitude();
        this.latitude = hotel.getLatitude();
        this.room = room;
        this.isOrdered = isOrdered;
    }

    public HotelVO(Integer id, String hotelName, String hotelDescription, String address, String bizRegion, HotelStar hotelStar, String phoneNum, double rate, Integer managerId, String hotelServe, String picture, List<hotelroom> room) {
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
        this.room = room;
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

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public List<hotelroom> getRoom() {
        return room;
    }

    public void setRoom(List<hotelroom> room) {
        this.room = room;
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

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
