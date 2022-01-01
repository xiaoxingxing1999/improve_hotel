package com.example.hotel.po.Hotel;

import com.example.hotel.Form.HotelManager.HotelEditForm;
import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

@Entity
@Table(name = "hoteledit_new4")
public class hoteledit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelName;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelDescription;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String phoneNum;
    private Integer hotelId;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String hotelServe;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String picture;
    private LocalDateTime createTime;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private HotelEditStatus status;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String replyContent;
    private LocalDateTime replyTime;

    public hoteledit() {
    }

    public hoteledit(Integer hotelId, HotelEditForm hotelEditForm){
        this.id = -1;
        this.hotelName = hotelEditForm.getHotelName();
        this.hotelDescription = hotelEditForm.getHotelDescription();
        this.phoneNum = hotelEditForm.getPhoneNum();
        this.hotelId = hotelId;
        this.hotelServe = hotelEditForm.getHotelServe();
        this.picture = hotelEditForm.getPicture();
        this.createTime = LocalDateTime.now();
        this.status = HotelEditStatus.WAIT;
        this.replyContent = "";
        this.replyTime = null;
    }

    public hoteledit(Integer id, String hotelName, String hotelDescription, String phoneNum, Integer hotelId, String hotelServe, String picture, LocalDateTime createTime, HotelEditStatus status, String replyContent, LocalDateTime replyTime) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.phoneNum = phoneNum;
        this.hotelId = hotelId;
        this.hotelServe = hotelServe;
        this.picture = picture;
        this.createTime = createTime;
        this.status = status;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

    public HotelEditStatus getStatus() {
        return status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }

    public String getHotelServe() {
        return hotelServe;
    }

    public void setHotelServe(String hotelServe) {
        this.hotelServe = hotelServe;
    }
}
