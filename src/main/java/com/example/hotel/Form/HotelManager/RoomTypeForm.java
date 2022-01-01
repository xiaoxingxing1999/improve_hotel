package com.example.hotel.Form.HotelManager;

/**
 * @Author stormbroken
 * Create by 2020/05/27
 * @Version 1.0
 **/

public class RoomTypeForm {
    private String name;
    private String token;

    public RoomTypeForm() {
    }

    public RoomTypeForm(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RoomTypeForm{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
