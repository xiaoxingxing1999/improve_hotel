package com.example.hotel.bl.coupon;

import com.example.hotel.Form.HotelManager.Coupon.BirthdayCoupon;
import com.example.hotel.Form.HotelManager.Coupon.RoomCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TargetMoneyCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TimeCoupon;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.UserService;
import com.example.hotel.enums.Coupon.Type;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.LoginVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceTest {
    @Autowired
    CouponService couponService;
    @Autowired
    UserService userService;

    private String hotelManagerToken;
    private String siteMarketToken;
    private String managerToken;

    @Before
    public void setUp() throws Exception {
        LoginForm loginForm = new LoginForm("4@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.hotelManagerToken = res.getToken();

        loginForm = new LoginForm("7@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.siteMarketToken = res.getToken();

        loginForm = new LoginForm("8@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.managerToken = res.getToken();
    }

    @Test
    @Transactional
    @Rollback
    public void addHotelTargetMoneyCoupon() throws ServiceException {
        int cnt = 0;
        TargetMoneyCoupon targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,100.0,hotelManagerToken);
        couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , -1, "测试满减优惠券",300.0
                ,100.0,siteMarketToken);
        couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,100.0,"");
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆", e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,100.0,managerToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限", e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 2, "测试满减优惠券",300.0
                ,100.0,hotelManagerToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店管理人员没有权限为其他酒店制定优惠", e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,100.0,siteMarketToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("网站营销人员没有权限为特定酒店制定优惠", e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",-1.0
                ,100.0,hotelManagerToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满足条件不应该小于等于0", e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,-1.0,hotelManagerToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("满减优惠的减免金额不能小于等于0", e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        targetMoneyCoupon = new TargetMoneyCoupon("测试满减优惠券描述"
                , 1, "测试满减优惠券",300.0
                ,400.0,hotelManagerToken);
        try{
            couponService.addHotelTargetMoneyCoupon(targetMoneyCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满减的金额不能小于满减需要满足的金额", e.getMessage());
        }
        Assert.assertEquals(7,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void addTimeCoupon() throws ServiceException{
        int cnt = 0;
        TimeCoupon timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, hotelManagerToken);
        couponService.addTimeCoupon(timeCoupon);

        timeCoupon = new TimeCoupon("测试限时策略",-1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, siteMarketToken);
        couponService.addTimeCoupon(timeCoupon);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.5
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                0.0, Type.ZHEKOU, hotelManagerToken);
        couponService.addTimeCoupon(timeCoupon);

        timeCoupon = new TimeCoupon("测试限时策略",-1
                ,"测试限时优惠券",200.0,0.5
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                0.0, Type.ZHEKOU, siteMarketToken);
        couponService.addTimeCoupon(timeCoupon);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, "");
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, managerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",2
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店管理人员没有权限为其他酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, siteMarketToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("网站营销人员没有权限为特定酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",-1.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满足条件不应该小于等于0",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                -1.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("满减优惠的减免金额不能小于等于0",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                400.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满减的金额不能小于满减需要满足的金额",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,0.0
                , TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now()),
                200.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券生效时间必须早于失效时间",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,-1.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(9,cnt);

        timeCoupon = new TimeCoupon("测试限时策略",1
                ,"测试限时优惠券",200.0,2.0
                , TimeHelper.getTimeStamp(LocalDateTime.now())
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusWeeks(1)),
                0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addTimeCoupon(timeCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(10,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void addBirthdayCoupon() throws ServiceException{
        int cnt = 0;
        BirthdayCoupon birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0,
                100.0, Type.MANJIAN, hotelManagerToken);
        couponService.addBirthdayCoupon(birthdayCoupon);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",-1
                ,"测试生日优惠券",200.0,0.0,
                100.0, Type.MANJIAN, siteMarketToken);
        couponService.addBirthdayCoupon(birthdayCoupon);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.5
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        couponService.addBirthdayCoupon(birthdayCoupon);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",-1
                ,"测试生日优惠券",200.0,0.5
                ,0.0, Type.ZHEKOU, siteMarketToken);
        couponService.addBirthdayCoupon(birthdayCoupon);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, "");
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, managerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",2
                ,"测试生日优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店管理人员没有权限为其他酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, siteMarketToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("网站营销人员没有权限为特定酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",-1.0,0.0
                ,100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满足条件不应该小于等于0",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0
                ,-1.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("满减优惠的减免金额不能小于等于0",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,0.0
                ,400.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满减的金额不能小于满减需要满足的金额",e.getMessage());
        }
        Assert.assertEquals(7,cnt);


        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,-1.0
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        birthdayCoupon = new BirthdayCoupon("测试生日策略",1
                ,"测试生日优惠券",200.0,2.0
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addBirthdayCoupon(birthdayCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(9,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void addRoomCoupon() throws ServiceException{
        int cnt = 0;
        RoomCoupon roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0,
                100.0, Type.MANJIAN, hotelManagerToken);
        couponService.addRoomCoupon(roomCoupon);

        roomCoupon = new RoomCoupon("测试多间策略",-1
                ,"测试多间优惠券",200.0,0.0,
                100.0, Type.MANJIAN, siteMarketToken);
        couponService.addRoomCoupon(roomCoupon);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.5
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        couponService.addRoomCoupon(roomCoupon);

        roomCoupon = new RoomCoupon("测试多间策略",-1
                ,"测试多间优惠券",200.0,0.5
                ,0.0, Type.ZHEKOU, siteMarketToken);
        couponService.addRoomCoupon(roomCoupon);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, "");
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, managerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",2
                ,"测试多间优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店管理人员没有权限为其他酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0
                ,100.0, Type.MANJIAN, siteMarketToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("网站营销人员没有权限为特定酒店制定优惠",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",-1.0,0.0
                ,100.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满足条件不应该小于等于0",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0
                ,-1.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("满减优惠的减免金额不能小于等于0",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,0.0
                ,400.0, Type.MANJIAN, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券满减的金额不能小于满减需要满足的金额",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,-1.0
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        roomCoupon = new RoomCoupon("测试多间策略",1
                ,"测试多间优惠券",200.0,2.0
                ,0.0, Type.ZHEKOU, hotelManagerToken);
        try{
            couponService.addRoomCoupon(roomCoupon);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("优惠券折扣不正确",e.getMessage());
        }
        Assert.assertEquals(9,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void unableCoupon() throws ServiceException {
        int cnt = 0;
        try{
            couponService.unableCoupon("",1);
        }catch (Exception e) {
            cnt++;
            Assert.assertEquals("用户没有登陆", e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            couponService.unableCoupon(hotelManagerToken,100);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到优惠券", e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        try{
            couponService.unableCoupon(siteMarketToken,9);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店优惠券已经失效", e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        try{
            couponService.unableCoupon(managerToken,1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限进行操作", e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        try{
            couponService.unableCoupon(hotelManagerToken,5);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限操作这个优惠券", e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        try{
            couponService.unableCoupon(siteMarketToken,1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您没有权限操作这个优惠券", e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        couponService.unableCoupon(hotelManagerToken,1);
    }
}