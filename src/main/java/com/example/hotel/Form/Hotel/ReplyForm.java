package com.example.hotel.Form.Hotel;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class ReplyForm {
    private int cid;
    private int rid;
    private String content;
    private String token;

    public ReplyForm() {
    }

    public ReplyForm(int cid, int rid, String content, String token) {
        this.cid = cid;
        this.rid = rid;
        this.content = content;
        this.token = token;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ReplyForm{" +
                "cid=" + cid +
                ", rid=" + rid +
                ", content='" + content + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
