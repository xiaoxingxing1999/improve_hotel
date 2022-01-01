package com.example.hotel.Form.HotelManager;

import com.example.hotel.enums.HotelEditStatus;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class CheckHotelEditForm {
    private int id;
    private HotelEditStatus status;
    private String replyContent;
    private String token;

    public CheckHotelEditForm() {
    }

    public CheckHotelEditForm(int id, HotelEditStatus status, String replyContent, String token) {
        this.id = id;
        this.status = status;
        this.replyContent = replyContent;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HotelEditStatus getStatus() {
        return status;
    }

    public void setStatus(HotelEditStatus status) {
        this.status = status;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CheckHotelEditForm{" +
                "id=" + id +
                ", status=" + status +
                ", replyContent='" + replyContent + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
