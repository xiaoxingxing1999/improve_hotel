package com.example.hotel.Form.Client;

import com.example.hotel.enums.VIPType;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class ClientVIPForm {
    private VIPType vipType;
    private String vipInfo;
    private String token;

    public ClientVIPForm() {
    }

    public ClientVIPForm(VIPType vipType, String vipInfo, String token) {
        this.vipType = vipType;
        this.vipInfo = vipInfo;
        this.token = token;
    }

    public VIPType getVipType() {
        return vipType;
    }

    public void setVipType(VIPType vipType) {
        this.vipType = vipType;
    }

    public String getVipInfo() {
        return vipInfo;
    }

    public void setVipInfo(String vipInfo) {
        this.vipInfo = vipInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ClientVIPForm{" +
                "vipType=" + vipType +
                ", vipInfo='" + vipInfo + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
