package com.example.hotel.Form.Client;
/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class OrderForm {
    private Integer hotelId;
    private String hotelName;
    private long checkInDate;
    private long checkOutDate;
    private String roomType;
    private Integer roomNum;
    private Integer peopleNum;
    private boolean haveChild;
    private Double price;
    private String clientName;
    private String phoneNumber;
    private String token;

    public OrderForm() {
    }

    public OrderForm(Integer hotelId, String hotelName, long checkInDate, long checkOutDate, String roomType, Integer roomNum, Integer peopleNum, boolean haveChild, Double price, String clientName, String phoneNumber, String token) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.peopleNum = peopleNum;
        this.haveChild = haveChild;
        this.price = price;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.token = token;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public boolean isHaveChild() {
        return haveChild;
    }

    public void setHaveChild(boolean haveChild) {
        this.haveChild = haveChild;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomType='" + roomType + '\'' +
                ", roomNum=" + roomNum +
                ", peopleNum=" + peopleNum +
                ", haveChild=" + haveChild +
                ", price=" + price +
                ", clientName='" + clientName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
