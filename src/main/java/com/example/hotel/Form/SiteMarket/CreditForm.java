package com.example.hotel.Form.SiteMarket;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class CreditForm {
    private Integer uid;
    private Double credit;
    private String token;

    public CreditForm() {
    }

    public CreditForm(Integer uid, Double credit, String token) {
        this.uid = uid;
        this.credit = credit;
        this.token = token;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreditForm{" +
                "uid=" + uid +
                ", credit=" + credit +
                ", token='" + token + '\'' +
                '}';
    }
}
