package com.example.hotel.Form.Client;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class CommentForm {
    private Integer oid;
    private String url;
    private String content;
    private Double rate;
    private String token;

    public CommentForm() {
    }

    public CommentForm(Integer oid, String url, String content, Double rate, String token) {
        this.oid = oid;
        this.url = url;
        this.content = content;
        this.rate = rate;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "oid=" + oid +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", rate=" + rate +
                ", token='" + token + '\'' +
                '}';
    }
}
