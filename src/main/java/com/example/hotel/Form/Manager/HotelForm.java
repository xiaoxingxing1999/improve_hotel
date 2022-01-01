package com.example.hotel.Form.Manager;

import com.example.hotel.enums.HotelStar;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class HotelForm {
    private String hotelName;
    private String hotelDescription;
    private String address;
    private String bizRegion;
    private HotelStar hotelStar;
    private String phoneNum;
    private String hotelServe;
    private String picture;
    private String token;
    private double longitude;
    private double latitude;

    public HotelForm() {
    }

    public HotelForm(String hotelName, String hotelDescription, String address, String bizRegion, HotelStar hotelStar, String phoneNum, String hotelServe, String picture, String token, double longitude, double latitude) {
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.address = address;
        this.bizRegion = bizRegion;
        this.hotelStar = hotelStar;
        this.phoneNum = phoneNum;
        this.hotelServe = hotelServe;
        this.picture = picture;
        this.token = token;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "HotelForm{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                ", address='" + address + '\'' +
                ", bizRegion='" + bizRegion + '\'' +
                ", hotelStar=" + hotelStar +
                ", phoneNum='" + phoneNum + '\'' +
                ", hotelServe='" + hotelServe + '\'' +
                ", picture='" + picture + '\'' +
                ", token='" + token + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
