package com.example.hotel.Form.HotelManager;

import com.example.hotel.enums.OrderState;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class OrderCheckForm {
    private Integer id;
    private OrderState orderState;
    private String token;

    public OrderCheckForm() {
    }

    public OrderCheckForm(Integer id, OrderState orderState, String token) {
        this.id = id;
        this.orderState = orderState;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "OrderCheckForm{" +
                "id=" + id +
                ", orderState=" + orderState +
                ", token='" + token + '\'' +
                '}';
    }
}
