package com.example.hotel.po.Order;

import com.example.hotel.Form.Client.OrderForm;
import com.example.hotel.enums.OrderState;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
@Entity
@Table(name = "hotelorder_new4")
public class hotelorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer hotelId;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelName;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private LocalDateTime realPassDate;
    private LocalDateTime realRefuseDate;
    private LocalDateTime realCancelDate;
    private LocalDateTime realCheckInDate;
    private LocalDateTime realCheckOutDate;
    private LocalDateTime realErrorDate;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String roomType;
    private Integer roomNum;
    private Integer peopleNum;
    private boolean haveChild;
    private LocalDateTime createDate;
    private Double price;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String clientName;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String phoneNumber;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private OrderState orderState;

    public hotelorder() {
    }

    public hotelorder(Integer hotelId, long checkInDate, long checkOutDate, Integer roomNum, Double price) {
        this.hotelId = hotelId;
        this.checkInDate = TimeHelper.getLocalDateTime(checkInDate);
        this.checkOutDate = TimeHelper.getLocalDateTime(checkOutDate);
        this.roomNum = roomNum;
        this.price = price;
    }

    public hotelorder(Integer userId, OrderForm orderForm){
        this.id = -1;
        this.userId = userId;
        this.hotelId = orderForm.getHotelId();
        this.hotelName = orderForm.getHotelName();
        this.checkInDate = TimeHelper.getLocalDateTime(orderForm.getCheckInDate());
        this.checkOutDate = TimeHelper.getLocalDateTime(orderForm.getCheckOutDate());
        this.realPassDate = null;
        this.realRefuseDate = null;
        this.realCancelDate = null;
        this.realCheckInDate = null;
        this.realCheckOutDate = null;
        this.realErrorDate = null;
        this.roomType = orderForm.getRoomType();
        this.roomNum = orderForm.getRoomNum();
        this.peopleNum = orderForm.getPeopleNum();
        this.haveChild = orderForm.isHaveChild();
        this.createDate = LocalDateTime.now();
        this.price = orderForm.getPrice();
        this.clientName = orderForm.getClientName();
        this.phoneNumber = orderForm.getPhoneNumber();
        this.orderState = OrderState.WAIT;
    }

    public hotelorder(Integer id, Integer userId, Integer hotelId, String hotelName, LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime realPassDate, LocalDateTime realRefuseDate, LocalDateTime realCancelDate, LocalDateTime realCheckInDate, LocalDateTime realCheckOutDate, LocalDateTime realErrorDate, String roomType, Integer roomNum, Integer peopleNum, boolean haveChild, LocalDateTime createDate, Double price, String clientName, String phoneNumber, OrderState orderState) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.realPassDate = realPassDate;
        this.realRefuseDate = realRefuseDate;
        this.realCancelDate = realCancelDate;
        this.realCheckInDate = realCheckInDate;
        this.realCheckOutDate = realCheckOutDate;
        this.realErrorDate = realErrorDate;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.peopleNum = peopleNum;
        this.haveChild = haveChild;
        this.createDate = createDate;
        this.price = price;
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.orderState = orderState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public LocalDateTime getRealCheckInDate() {
        return realCheckInDate;
    }

    public void setRealCheckInDate(LocalDateTime realCheckInDate) {
        this.realCheckInDate = realCheckInDate;
    }

    public LocalDateTime getRealCheckOutDate() {
        return realCheckOutDate;
    }

    public void setRealCheckOutDate(LocalDateTime realCheckOutDate) {
        this.realCheckOutDate = realCheckOutDate;
    }

    public LocalDateTime getRealPassDate() {
        return realPassDate;
    }

    public void setRealPassDate(LocalDateTime realPassDate) {
        this.realPassDate = realPassDate;
    }

    public LocalDateTime getRealRefuseDate() {
        return realRefuseDate;
    }

    public void setRealRefuseDate(LocalDateTime realRefuseDate) {
        this.realRefuseDate = realRefuseDate;
    }

    public LocalDateTime getRealCancelDate() {
        return realCancelDate;
    }

    public void setRealCancelDate(LocalDateTime realCancelDate) {
        this.realCancelDate = realCancelDate;
    }

    public LocalDateTime getRealErrorDate() {
        return realErrorDate;
    }

    public void setRealErrorDate(LocalDateTime realErrorDate) {
        this.realErrorDate = realErrorDate;
    }

    @Override
    public String toString() {
        return "hotelorder{" +
                "id=" + id +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", realPassDate=" + realPassDate +
                ", realRefuseDate=" + realRefuseDate +
                ", realCancelDate=" + realCancelDate +
                ", realCheckInDate=" + realCheckInDate +
                ", realCheckOutDate=" + realCheckOutDate +
                ", realErrorDate=" + realErrorDate +
                ", roomType='" + roomType + '\'' +
                ", roomNum=" + roomNum +
                ", peopleNum=" + peopleNum +
                ", haveChild=" + haveChild +
                ", createDate=" + createDate +
                ", price=" + price +
                ", clientName='" + clientName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderState=" + orderState +
                '}';
    }
}
