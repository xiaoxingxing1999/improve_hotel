package com.example.hotel.vo;

import com.example.hotel.po.Order.comment;

import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/06/14
 * @Version 1.0
 **/

public class CommentVO {
    private Integer id;
    private String userUrl;
    private String userName;
    private String url;
    private String content;
    private LocalDateTime createTime;
    private Double rate;
    private Integer repliesNum;

    public CommentVO() {
    }

    public CommentVO(comment Comment, String userUrl, String userName, Integer repliesNum){
        this.id = Comment.getId();
        this.userUrl = userUrl;
        this.userName = userName;
        this.url = Comment.getUrl();
        this.content = Comment.getContent();
        this.createTime = Comment.getCreateTime();
        this.rate = Comment.getRate();
        this.repliesNum = repliesNum;
    }

    public CommentVO(Integer id, String userUrl, String userName, String url, String content, LocalDateTime createTime, Double rate, Integer repliesNum) {
        this.id = id;
        this.userUrl = userUrl;
        this.userName = userName;
        this.url = url;
        this.content = content;
        this.createTime = createTime;
        this.rate = rate;
        this.repliesNum = repliesNum;
    }

    public Integer getRepliesNum() {
        return repliesNum;
    }

    public void setRepliesNum(Integer repliesNum) {
        this.repliesNum = repliesNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
