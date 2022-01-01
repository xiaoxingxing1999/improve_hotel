package com.example.hotel.vo;

import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.po.Hotel.hotelsituation;

/**
 * @Author stormbroken
 * Create by 2020/06/18
 * @Version 1.0
 **/

public class HotelSituationVO {
    /**
     * 入住客户总数
     */
    private Integer clientNum;
    /**
     * 入住客户变化量
     */
    private Integer clientChange;
    /**
     * 入住VIP总数
     */
    private Integer VIPNum;
    /**
     * 入住VIP变化量
     */
    private Integer VIPChange;
    /**
     * 每天完成的订单数量(CheckOut)
     */
    private Integer orderDoneNum;
    /**
     * 每天订单完成变化量
     */
    private Integer orderDoneChange;
    /**
     * 每天订单撤销/异常订单总数
     */
    private Integer orderBadNum;
    /**
     * 每天订单撤销/异常订单变化量
     */
    private Integer orderBadChange;
    /**
     * 每天酒店有多少人入住(CheckIn-)
     */
    private Integer roomNumber;
    /**
     * 房间总数
     */
    private Integer roomTotalNumber;
    private String today;

    public HotelSituationVO() {
        this.clientNum = 0;
        this.clientChange = 0;
        this.VIPNum = 0;
        this.VIPChange = 0;
        this.orderDoneNum = 0;
        this.orderDoneChange = 0;
        this.orderBadNum = 0;
        this.orderBadChange = 0;
        this.roomNumber = 0;
        this.roomTotalNumber = 0;
        this.today = null;
    }

    public HotelSituationVO(hotelsituation HotelSituation){
        this.clientNum = HotelSituation.getClientNum();
        this.clientChange = 0;
        this.VIPNum = HotelSituation.getVipNum();
        this.VIPChange = 0;
        this.orderDoneNum = HotelSituation.getOrderDoneNum();
        this.orderDoneChange = 0;
        this.orderBadNum = HotelSituation.getOrderBadNum();
        this.orderBadChange = 0;
        this.roomNumber = HotelSituation.getTotalClientNum();
        this.roomTotalNumber = HotelSituation.getRoomTotalNumber();
        this.today = HotelSituation.getToday();
    }

    public HotelSituationVO(Integer clientNum, Integer clientChange, Integer VIPNum, Integer VIPChange, Integer orderDoneNum, Integer orderDoneChange, Integer orderBadNum, Integer orderBadChange, Integer roomNumber, Integer roomTotalNumber, String today) {
        this.clientNum = clientNum;
        this.clientChange = clientChange;
        this.VIPNum = VIPNum;
        this.VIPChange = VIPChange;
        this.orderDoneNum = orderDoneNum;
        this.orderDoneChange = orderDoneChange;
        this.orderBadNum = orderBadNum;
        this.orderBadChange = orderBadChange;
        this.roomNumber = roomNumber;
        this.roomTotalNumber = roomTotalNumber;
        this.today = today;
    }

    public Integer getRoomTotalNumber() {
        return roomTotalNumber;
    }

    public void setRoomTotalNumber(Integer roomTotalNumber) {
        this.roomTotalNumber = roomTotalNumber;
    }

    public Integer getClientNum() {
        return clientNum;
    }

    public void setClientNum(Integer clientNum) {
        this.clientNum = clientNum;
    }

    public Integer getClientChange() {
        return clientChange;
    }

    public void setClientChange(Integer clientChange) {
        this.clientChange = clientChange;
    }

    public Integer getVIPNum() {
        return VIPNum;
    }

    public void setVIPNum(Integer VIPNum) {
        this.VIPNum = VIPNum;
    }

    public Integer getVIPChange() {
        return VIPChange;
    }

    public void setVIPChange(Integer VIPChange) {
        this.VIPChange = VIPChange;
    }

    public Integer getOrderDoneNum() {
        return orderDoneNum;
    }

    public void setOrderDoneNum(Integer orderDoneNum) {
        this.orderDoneNum = orderDoneNum;
    }

    public Integer getOrderDoneChange() {
        return orderDoneChange;
    }

    public void setOrderDoneChange(Integer orderDoneChange) {
        this.orderDoneChange = orderDoneChange;
    }

    public Integer getOrderBadNum() {
        return orderBadNum;
    }

    public void setOrderBadNum(Integer orderBadNum) {
        this.orderBadNum = orderBadNum;
    }

    public Integer getOrderBadChange() {
        return orderBadChange;
    }

    public void setOrderBadChange(Integer orderBadChange) {
        this.orderBadChange = orderBadChange;
    }


    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void updateClientNum(){
        this.clientNum++;
    }

    public void updateVIPNum(){
        this.VIPNum++;
    }

    public void updateOrderDoneNum(){
        this.orderDoneNum++;
    }

    public void updateOrderBadNum(){
        this.orderBadNum++;
    }

    public void updateRoomNumber(Integer roomNumber){
        this.roomNumber += roomNumber;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
