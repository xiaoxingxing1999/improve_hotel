package com.example.hotel.enums;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public enum HotelEditStatus {
    WAIT("等待审核中"),
    PASS("通过申请"),
    REFUSE("拒绝申请"),
    ERROR("出现异常");

    private String value;

    HotelEditStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
