package com.example.hotel.po.User;

import com.example.hotel.Form.Client.ClientForm;
import com.example.hotel.Form.HotelManager.HotelManagerForm;
import com.example.hotel.enums.UserType;
import com.example.hotel.enums.VIPType;

import javax.persistence.*;

@Entity
@Table(name = "user_new4")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String url;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String email;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String password;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String userName;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String phoneNumber;
    private double credit;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private VIPType vipType;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private String vipInfo;
    @Column(columnDefinition = "mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL")
    private UserType userType;
    private double totalcredit;

    public user() {
    }

    public user(Integer id, String url, String email, String password, String userName, String phoneNumber, double credit, VIPType vipType, String vipInfo, UserType userType, double totalcredit) {
        this.id = id;
        this.url = url;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.credit = credit;
        this.vipType = vipType;
        this.vipInfo = vipInfo;
        this.userType = userType;
        this.totalcredit = totalcredit;
    }

    public user(ClientForm clientForm) {
        this.id = -1;
        this.url = clientForm.getUrl();
        this.email = clientForm.getEmail();
        this.password = clientForm.getPassword();
        this.userName = clientForm.getUserName();
        this.phoneNumber = clientForm.getPhoneNumber();
        this.credit = 0;
        this.vipType = VIPType.NOT_VIP;
        this.vipInfo = "";
        this.userType = UserType.Client;
        this.totalcredit= 0;
    }

    public user(HotelManagerForm hotelManagerForm){
        this.id = -1;
        this.url = hotelManagerForm.getUrl();
        this.email = hotelManagerForm.getEmail();
        this.password = hotelManagerForm.getPassword();
        this.userName = hotelManagerForm.getUserName();
        this.phoneNumber = hotelManagerForm.getPhoneNumber();
        this.credit = 0;
        this.vipType = VIPType.NOT_VIP;
        this.vipInfo = "";
        this.userType = UserType.HotelManager;
        this.totalcredit = 0;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public VIPType getVipType() {
        return vipType;
    }

    public void setVipType(VIPType vipType) {
        this.vipType = vipType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getVipInfo() {
        return vipInfo;
    }

    public void setVipInfo(String vipInfo) {
        this.vipInfo = vipInfo;
    }

    public double getTotalcredit() {
        return totalcredit;
    }

    public void setTotalcredit(double totalcredit) {
        this.totalcredit = totalcredit;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", credit=" + credit +
                ", vipType=" + vipType +
                ", vipInfo='" + vipInfo + '\'' +
                ", userType=" + userType +
                ", totalcredit=" + totalcredit +
                '}';
    }
}
