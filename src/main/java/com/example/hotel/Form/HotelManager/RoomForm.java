package com.example.hotel.Form.HotelManager;

/**
 * @Author stormbroken
 * Create by 2020/05/26
 * @Version 1.0
 **/

public class RoomForm {
    private Double price;
    private Integer total;
    private Integer hotelId;
    private String roomType;
    private Integer validPeople;
    private String serve;
    private String token;
    private String url;

    public RoomForm() {
    }

    public RoomForm(Double price, Integer total, Integer hotelId, String roomType, Integer validPeople, String serve, String token, String url) {
        this.price = price;
        this.total = total;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.validPeople = validPeople;
        this.serve = serve;
        this.token = token;
        this.url = url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getValidPeople() {
        return validPeople;
    }

    public void setValidPeople(Integer validPeople) {
        this.validPeople = validPeople;
    }

    public String getServe() {
        return serve;
    }

    public void setServe(String serve) {
        this.serve = serve;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RoomForm{" +
                "price=" + price +
                ", total=" + total +
                ", hotelId=" + hotelId +
                ", roomType='" + roomType + '\'' +
                ", validPeople=" + validPeople +
                ", serve='" + serve + '\'' +
                ", token='" + token + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
