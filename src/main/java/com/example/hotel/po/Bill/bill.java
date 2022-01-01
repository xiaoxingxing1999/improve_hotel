package com.example.hotel.po.Bill;

import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/


@Entity
@Table(name="bill_new4")
public class bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer uid;
    private Integer mid;
    private Double credit;
    private LocalDateTime createTime;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String content;

    public bill() {
    }

    public bill(Integer uid, Integer mid, Double credit,String content){
        this.id = -1;
        this.uid = uid;
        this.mid = mid;
        this.credit = credit;
        this.createTime = LocalDateTime.now();
        this.content = content;
    }

    public bill(Integer uid, Integer mid, Double credit, LocalDateTime createTime, String content) {
        this.uid = uid;
        this.mid = mid;
        this.credit = credit;
        this.createTime = createTime;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
