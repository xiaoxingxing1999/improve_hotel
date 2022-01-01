package com.example.hotel.Form.HotelManager;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class HotelEditForm {
    private String hotelName;
    private String hotelDescription;
    private String phoneNum;
    private String hotelServe;
    private String picture;
    private String token;

    public HotelEditForm() {
    }

    public HotelEditForm(String hotelName, String hotelDescription, String phoneNum, String hotelServe, String picture, String token) {
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.phoneNum = phoneNum;
        this.hotelServe = hotelServe;
        this.picture = picture;
        this.token = token;
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

    @Override
    public String toString() {
        return "HotelEditForm{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", hotelServe='" + hotelServe + '\'' +
                ", picture='" + picture + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
