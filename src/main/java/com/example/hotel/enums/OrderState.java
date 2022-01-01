package com.example.hotel.enums;

/**
 * @Author stormbroken
 * Create by 2020/05/22
 * @Version 1.0
 **/

public enum OrderState {
    WAIT("审核中"),
    PASS("通过预定"),
    REFUSE("拒绝预订"),
    CANCEL("已取消"),
    CHECKIN("已入住"),
    CHECKOUT("已退房"),
    ERROR("状态异常");

    private String value;
    OrderState(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }
}
