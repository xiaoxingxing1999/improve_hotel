package com.example.hotel.po.Order;

import com.example.hotel.Form.Client.CommentForm;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

@Entity
@Table(name = "comment_new4")
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer uid;
    private Integer oid;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String url;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL")
    private String content;
    private LocalDateTime createTime;
    private Double rate;

    public comment() {
    }

    public comment(Integer userId, CommentForm commentForm){
        this.id = -1;
        this.uid = userId;
        this.oid = commentForm.getOid();
        this.url = commentForm.getUrl();
        this.content = commentForm.getContent();
        this.createTime = LocalDateTime.now();
        this.rate = commentForm.getRate();
    }

    public comment(Integer id, Integer uid, Integer oid, String url, String content, LocalDateTime createTime, Double rate) {
        this.id = id;
        this.uid = uid;
        this.oid = oid;
        this.url = url;
        this.content = content;
        this.createTime = createTime;
        this.rate = rate;
    }

    public comment(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
