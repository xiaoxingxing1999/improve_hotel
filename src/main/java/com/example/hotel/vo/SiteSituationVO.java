package com.example.hotel.vo;

/**
 * @Author stormbroken
 * Create by 2020/06/18
 * @Version 1.0
 **/

public class SiteSituationVO {
    /**
     * 酒店总数
     */
    private Integer hotelNum;
    /**
     * 酒店总数变化量，相较于前一天
     */
    private Integer hotelChange;
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

    public SiteSituationVO() {
    }

    public SiteSituationVO(HotelSituationVO hotelSituationVO){
        this.hotelNum = 0;
        this.hotelChange = 0;
        this.clientNum = hotelSituationVO.getClientNum();
        this.clientChange = 0;
        this.VIPNum = hotelSituationVO.getVIPNum();
        this.VIPChange = 0;
        this.orderDoneNum = hotelSituationVO.getOrderDoneNum();
        this.orderDoneChange = 0;
        this.orderBadNum = hotelSituationVO.getOrderBadNum();
        this.orderBadChange = 0;
        this.roomNumber = hotelSituationVO.getRoomNumber();
        this.roomTotalNumber = hotelSituationVO.getRoomTotalNumber();
        this.today = hotelSituationVO.getToday();
    }

    public SiteSituationVO(Integer hotelNum, Integer hotelChange, Integer clientNum, Integer clientChange, Integer VIPNum, Integer VIPChange, Integer orderDoneNum, Integer orderDoneChange, Integer orderBadNum, Integer orderBadChange, Integer roomNumber, Integer roomTotalNumber, String today) {
        this.hotelNum = hotelNum;
        this.hotelChange = hotelChange;
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

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public Integer getHotelNum() {
        return hotelNum;
    }

    public void setHotelNum(Integer hotelNum) {
        this.hotelNum = hotelNum;
    }

    public Integer getHotelChange() {
        return hotelChange;
    }

    public void setHotelChange(Integer hotelChange) {
        this.hotelChange = hotelChange;
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

    public Integer getRoomTotalNumber() {
        return roomTotalNumber;
    }

    public void setRoomTotalNumber(Integer roomTotalNumber) {
        this.roomTotalNumber = roomTotalNumber;
    }

    public void add(SiteSituationVO siteSituationVO){
        if(!this.today.equals(siteSituationVO.getToday())){
            return;
        }
        this.hotelNum += siteSituationVO.getHotelNum();
        this.hotelChange += siteSituationVO.getHotelChange();
        this.clientNum += siteSituationVO.getClientNum();
        this.clientChange += siteSituationVO.getClientChange();
        this.VIPNum += siteSituationVO.getVIPNum();
        this.VIPChange += siteSituationVO.getVIPChange();
        this.orderDoneNum += siteSituationVO.getOrderDoneNum();
        this.orderDoneChange += siteSituationVO.getOrderDoneChange();
        this.orderBadNum += siteSituationVO.getOrderBadNum();
        this.orderBadChange += siteSituationVO.getOrderBadChange();
        this.roomNumber += siteSituationVO.getRoomNumber();
        this.roomTotalNumber += siteSituationVO.getRoomTotalNumber();
    }
}
