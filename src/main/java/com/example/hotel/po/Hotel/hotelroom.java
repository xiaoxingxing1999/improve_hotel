package com.example.hotel.po.Hotel;

import com.example.hotel.Form.HotelManager.RoomForm;
import com.example.hotel.util.ArrayHelper;
import com.example.hotel.util.DayHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "hotelroom_new4")
public class hotelroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String curNum;
    private Integer total;
    private Integer hotelId;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String roomType;
    private Integer validPeople;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String serve;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String url;

    public hotelroom() {
    }

    public hotelroom(RoomForm roomForm){
        this.id = -1;
        this.price = roomForm.getPrice();
        this.curNum = DayHelper.init(roomForm.getTotal());
        this.total = roomForm.getTotal();
        this.hotelId = roomForm.getHotelId();
        this.roomType = roomForm.getRoomType();
        this.validPeople = roomForm.getValidPeople();
        this.serve = roomForm.getServe();
        this.url = roomForm.getUrl();
    }

    public hotelroom(Integer id, Double price, String curNum, Integer total, Integer hotelId, String roomType, Integer validPeople, String serve, String url) {
        this.id = id;
        this.price = price;
        this.curNum = curNum;
        this.total = total;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.validPeople = validPeople;
        this.serve = serve;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurNum() {
        return curNum;
    }

    public void setCurNum(String curNum) {
        this.curNum = curNum;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Integer getValidPeople() {
        return validPeople;
    }

    public void setValidPeople(Integer validPeople) {
        this.validPeople = validPeople;
    }

    public String getServe() {
        return serve;
    }

    public void setServe(String serve) {
        this.serve = serve;
    }

    @Override
    public String toString() {
        return "hotelroom{" +
                "id=" + id +
                ", price=" + price +
                ", curNum='" + curNum + '\'' +
                ", total=" + total +
                ", hotelId=" + hotelId +
                ", roomType='" + roomType + '\'' +
                ", validPeople=" + validPeople +
                ", serve='" + serve + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
