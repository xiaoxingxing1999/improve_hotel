package com.example.hotel.po.Order;

import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.util.TimeHelper;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

@Entity
@Table(name = "reply_new4")
public class reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cid;
    private Integer rid;
    private Integer uid;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String username;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL")
    private String content;
    private LocalDateTime createTime;

    public reply(){

    }

    public reply(Integer uid, String username, ReplyForm replyForm){
        this.id = -1;
        this.cid = replyForm.getCid();
        this.rid = replyForm.getRid();
        this.uid = uid;
        this.username = username;
        this.content = replyForm.getContent();
        this.createTime = LocalDateTime.now();
    }

    public reply(Integer id, Integer cid, Integer rid, Integer uid, String username, String content, LocalDateTime createTime) {
        this.id = id;
        this.cid = cid;
        this.rid = rid;
        this.uid = uid;
        this.username = username;
        this.content = content;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
