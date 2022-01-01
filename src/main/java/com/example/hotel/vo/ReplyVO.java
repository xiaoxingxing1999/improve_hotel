package com.example.hotel.vo;

import com.example.hotel.po.Order.reply;

import java.time.LocalDateTime;

/**
 * @Author stormbroken
 * Create by 2020/06/18
 * @Version 1.0
 **/

public class ReplyVO {
    private Integer id;
    private Integer cid;
    private Integer rid;
    private Integer uid;
    private String commentContent;
    private String username;
    private String content;
    private LocalDateTime createTime;
    private String url;
    private Integer repliesNum;

    public ReplyVO() {
    }

    public ReplyVO(reply Reply,String url, Integer repliesNum,String commentContent){
        this.id = Reply.getId();
        this.cid = Reply.getCid();
        this.rid = Reply.getRid();
        this.uid = Reply.getUid();
        this.commentContent = commentContent;
        this.username = Reply.getUsername();
        this.content = Reply.getContent();
        this.createTime = Reply.getCreateTime();
        this.url = url;
        this.repliesNum  = repliesNum;
    }

    public ReplyVO(Integer id, Integer cid, Integer rid, Integer uid, String commentContent, String username, String content, LocalDateTime createTime, String url, Integer repliesNum) {
        this.id = id;
        this.cid = cid;
        this.rid = rid;
        this.uid = uid;
        this.commentContent = commentContent;
        this.username = username;
        this.content = content;
        this.createTime = createTime;
        this.url = url;
        this.repliesNum = repliesNum;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRepliesNum() {
        return repliesNum;
    }

    public void setRepliesNum(Integer repliesNum) {
        this.repliesNum = repliesNum;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
