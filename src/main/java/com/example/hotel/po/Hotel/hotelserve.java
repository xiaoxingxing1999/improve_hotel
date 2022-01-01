package com.example.hotel.po.Hotel;

import com.example.hotel.Form.HotelManager.ServeForm;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/21
 * @Version 1.0
 **/

@Entity
@Table(name = "hotelserve_new4")
public class hotelserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String name;
    private Integer uid;
    private LocalDateTime createTime;

    public hotelserve() {
    }

    public hotelserve(Integer uid, ServeForm serveForm){
        this.id = -1;
        this.name = serveForm.getName();
        this.uid = uid;
        this.createTime = LocalDateTime.now();
    }


    public hotelserve(Integer id, String name, Integer uid, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
