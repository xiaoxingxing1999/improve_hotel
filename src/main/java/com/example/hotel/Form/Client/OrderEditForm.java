package com.example.hotel.Form.Client;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class OrderEditForm {
    private Integer id;
    private long checkInDate;
    private long checkOutDate;
    private Integer peopleNum;
    private boolean haveChild;
    private String token;

    public OrderEditForm() {
    }

    public OrderEditForm(Integer id, long checkInDate, long checkOutDate, Integer peopleNum, boolean haveChild, String token) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.peopleNum = peopleNum;
        this.haveChild = haveChild;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long checkOutDate) {
        this.checkOutDate = checkOutDate;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "OrderEditForm{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", peopleNum=" + peopleNum +
                ", haveChild=" + haveChild +
                ", token='" + token + '\'' +
                '}';
    }
}
