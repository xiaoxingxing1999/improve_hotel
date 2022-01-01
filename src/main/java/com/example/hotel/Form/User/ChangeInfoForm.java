package com.example.hotel.Form.User;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public class ChangeInfoForm {
    private String userName;
    private String phoneNumber;
    private String url;
    private String token;

    public ChangeInfoForm() {
    }

    public ChangeInfoForm(String userName, String phoneNumber, String url, String token) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.url = url;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ChangeInfoForm{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", url='" + url + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
