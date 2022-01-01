package com.example.hotel.po.Hotel;

import javax.persistence.*;

/**
 * @Author stormbroken
 * Create by 2020/06/19
 * @Version 1.0
 **/

@Entity
@Table(name = "hotelsituation_new4")
public class hotelsituation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer hotelId;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String today;//"yyyy-MM-dd"
    /**
     * 新入住客户总数
     */
    private Integer clientNum;
    /**
     * 新入住VIP总数
     */
    private Integer vipNum;
    /**
     * 每天完成的订单数量(CheckOut)
     */
    private Integer orderDoneNum;
    /**
     * 每天订单撤销/异常订单总数
     */
    private Integer orderBadNum;
    /**
     * 每天酒店的在住人数
     */
    private Integer totalClientNum;
    /**
     * 房间总数
     */
    private Integer roomTotalNumber;

    public hotelsituation() {
    }

    public hotelsituation(Integer hotelId, String today, Integer roomTotalNumber, Integer totalClientNum){
        this.id = -1;
        this.hotelId = hotelId;
        this.today = today;
        this.clientNum = 0;
        this.vipNum = 0;
        this.orderDoneNum = 0;
        this.orderBadNum = 0;
        this.totalClientNum = totalClientNum;//结余前一天的
        this.roomTotalNumber = roomTotalNumber;
    }

    public hotelsituation(Integer id,Integer hotelId, String today, Integer clientNum, Integer vipNum, Integer orderDoneNum, Integer orderBadNum, Integer totalClientNum, Integer roomTotalNumber) {
        this.id = id;
        this.hotelId = hotelId;
        this.today = today;
        this.clientNum = clientNum;
        this.vipNum = vipNum;
        this.orderDoneNum = orderDoneNum;
        this.orderBadNum = orderBadNum;
        this.totalClientNum = totalClientNum;
        this.roomTotalNumber = roomTotalNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public Integer getClientNum() {
        return clientNum;
    }

    public void setClientNum(Integer clientNum) {
        this.clientNum = clientNum;
    }

    public Integer getVipNum() {
        return vipNum;
    }

    public void setVipNum(Integer vipNum) {
        this.vipNum = vipNum;
    }

    public Integer getOrderDoneNum() {
        return orderDoneNum;
    }

    public void setOrderDoneNum(Integer orderDoneNum) {
        this.orderDoneNum = orderDoneNum;
    }

    public Integer getOrderBadNum() {
        return orderBadNum;
    }

    public void setOrderBadNum(Integer orderBadNum) {
        this.orderBadNum = orderBadNum;
    }

    public Integer getTotalClientNum() {
        return totalClientNum;
    }

    public void setTotalClientNum(Integer totalClientNum) {
        this.totalClientNum = totalClientNum;
    }

    public Integer getRoomTotalNumber() {
        return roomTotalNumber;
    }

    public void setRoomTotalNumber(Integer roomTotalNumber) {
        this.roomTotalNumber = roomTotalNumber;
    }


    @Override
    public String toString() {
        return "hotelsituation{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", today='" + today + '\'' +
                ", clientNum=" + clientNum +
                ", vipNum=" + vipNum +
                ", orderDoneNum=" + orderDoneNum +
                ", orderBadNum=" + orderBadNum +
                ", totalClientNum=" + totalClientNum +
                ", roomTotalNumber=" + roomTotalNumber +
                '}';
    }
}
