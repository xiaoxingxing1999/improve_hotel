package com.example.hotel.util;

import com.example.hotel.data.bill.BillMapper;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.data.hotel.*;
import com.example.hotel.data.order.CommentMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.order.ReplyMapper;
import com.example.hotel.data.user.UserMapper;
import com.example.hotel.enums.*;
import com.example.hotel.enums.Coupon.CouponStatus;
import com.example.hotel.enums.Coupon.CouponType;
import com.example.hotel.enums.Coupon.Type;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Hotel.*;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.Order.reply;
import com.example.hotel.po.User.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/24
 * @Version 1.0
 **/

@Component
public class InitUtil {
    @Autowired
    BillMapper billMapper;
    @Autowired
    BizRegionMapper bizRegionMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    HotelEditMapper hotelEditMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    HotelServeMapper hotelServeMapper;
    @Autowired
    HotelSituationMapper hotelSituationMapper;
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    RoomTypeMapper roomTypeMapper;
    @Autowired
    UserMapper userMapper;

    private void clear(){
        System.out.println("开始清空数据库，艹！");
        billMapper.deleteAll();
        bizRegionMapper.deleteAll();
        commentMapper.deleteAll();
        couponMapper.deleteAll();
        hotelMapper.deleteAll();
        hotelEditMapper.deleteAll();
        orderMapper.deleteAll();
        roomMapper.deleteAll();
        hotelServeMapper.deleteAll();
        hotelSituationMapper.deleteAll();
        replyMapper.deleteAll();
        roomTypeMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Scheduled(fixedRate = 365 * 24 * 60 * 60 * 1000)
    private void init_Database(){
        List<user> users = userMapper.findAll();
        if(users.size() != 0){
            System.out.println(LocalDateTime.now() + ":数据库已经初始化过");
            return;
        }

        System.out.println(LocalDateTime.now() + ":初始化数据库");
        user User1 = new user(1,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"1@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","客户一"
                ,"18851083188",10.0, VIPType.NOT_VIP,null
                , UserType.Client,10.0);
        user User2 = new user(2,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"2@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","客户二"
                ,"18851083188",200.0, VIPType.NORMAL_LEVEL1,null
                , UserType.Client,200.0);
        user User3 = new user(3,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"3@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","客户三"
                ,"18851083188",200.0, VIPType.NORMAL_LEVEL1,"2000-09-01"
                , UserType.Client,200.0);
        user User4 = new user(4,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"4@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","七天酒店工作人员"
                ,"18851083188",0.0, null,null
                , UserType.HotelManager,0.0);
        user User5 = new user(5,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"5@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","维度酒店工作人员"
                ,"18851083188",0.0, null,null
                , UserType.HotelManager,0.0);
        user User6 = new user(6,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"6@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","老门东度假酒店工作人员"
                ,"18851083188",0.0, null,null
                , UserType.HotelManager,0.0);
        user User7 = new user(7,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"7@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","网站营销人员"
                ,"18851083188",0.0, null,null
                , UserType.SiteMarket,0.0);
        user User8 = new user(8,"https://twitter-content.oss-cn-shanghai.aliyuncs.com/1.jpg"
                ,"8@qq.com","4QrcOUm6Wau+VuBX8g+IPg==","网站管理员"
                ,"18851083188",0.0, null,null
                , UserType.Manager,0.0);
        userMapper.save(User1);
        userMapper.save(User2);
        userMapper.save(User3);
        userMapper.save(User4);
        userMapper.save(User5);
        userMapper.save(User6);
        userMapper.save(User7);
        userMapper.save(User8);

        bill Bill = new bill(1,7,0.0, LocalDateTime.now(),"测试账单");
        billMapper.save(Bill);

        bizregion BizRegion1 = new bizregion(1, "老门东",  7, LocalDateTime.now());
        bizregion BizRegion2 = new bizregion(2, "中山陵",  7, LocalDateTime.now());
        bizregion BizRegion3 = new bizregion(3, "南京大学仙林校区",  7, LocalDateTime.now());
        bizregion BizRegion4 = new bizregion(4, "南京大学鼓楼校区",  7, LocalDateTime.now());
        bizRegionMapper.save(BizRegion1);
        bizRegionMapper.save(BizRegion2);
        bizRegionMapper.save(BizRegion3);
        bizRegionMapper.save(BizRegion4);

        comment comment1 = new comment(1, 1, 1
                , "https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg"
                ,"评论1号订单",LocalDateTime.now(),2.5);
        comment comment2 = new comment(2, 1, 2
                , "https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg"
                ,"评论2号订单",LocalDateTime.now(),0.0);
        comment comment3 = new comment(3, 1, 3
                , "https://software3.oss-cn-beijing.aliyuncs.com/2020-06-21/1592732112963-4a00d8e54e934bd89e.jpeg"
                ,"评论3号订单",LocalDateTime.now(),5.0);
        commentMapper.save(comment1);
        commentMapper.save(comment2);
        commentMapper.save(comment3);

        coupon Coupon1 = new coupon(1,"生日优惠券满减描述",1, CouponType.BIRTHDAY,"生日满减优惠券",500,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),100, Type.MANJIAN);
        coupon Coupon2 = new coupon(2,"多间优惠券满减描述",1, CouponType.ROOMS,"多间满减优惠券",2,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),110, Type.MANJIAN);
        coupon Coupon3 = new coupon(3,"满减优惠券满减描述",1, CouponType.MONEY,"满减满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),120, Type.MANJIAN);
        coupon Coupon4 = new coupon(4,"限时优惠券满减描述",1, CouponType.TIME,"限时满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),130, Type.MANJIAN);
        coupon Coupon5 = new coupon(5,"生日优惠券满减描述",-1, CouponType.BIRTHDAY,"生日满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),100, Type.MANJIAN);
        coupon Coupon6 = new coupon(6,"多间优惠券满减描述",-1, CouponType.ROOMS,"多间满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),110, Type.MANJIAN);
        coupon Coupon7 = new coupon(7,"满减优惠券满减描述",-1, CouponType.MONEY,"满减满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),120, Type.MANJIAN);
        coupon Coupon8 = new coupon(8,"限时优惠券满减描述",-1, CouponType.TIME,"限时满减优惠券",300,0, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),130, Type.MANJIAN);
        coupon Coupon9 = new coupon(9,"限时优惠券满减描述",-1, CouponType.TIME,"限时满减优惠券",300,0, CouponStatus.INVALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),100, Type.MANJIAN);
        coupon Coupon10 = new coupon(10,"生日优惠券折扣描述",1, CouponType.BIRTHDAY,"生日折扣优惠券",300,0.75, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);coupon Coupon11 = new coupon(11,"多间优惠券折扣描述",1, CouponType.ROOMS,"多间折扣优惠券",2,0.8, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon12 = new coupon(12,"满减优惠券折扣描述",1, CouponType.MONEY,"满减折扣优惠券",300,0.85, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon13 = new coupon(13,"限时优惠券折扣描述",1, CouponType.TIME,"限时折扣优惠券",300,0.9, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon14 = new coupon(14,"生日优惠券折扣描述",-1, CouponType.BIRTHDAY,"生日折扣优惠券",300,0.75, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon15 = new coupon(15,"多间优惠券折扣描述",-1, CouponType.ROOMS,"多间折扣优惠券",300,0.8, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon16 = new coupon(16,"满减优惠券折扣描述",-1, CouponType.MONEY,"满减折扣优惠券",300,0.85, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        coupon Coupon17 = new coupon(17,"限时优惠券折扣描述",-1, CouponType.TIME,"限时折扣优惠券",300,0.9, CouponStatus.VALID,LocalDateTime.now(),LocalDateTime.now().plusYears(1),0, Type.ZHEKOU);
        couponMapper.save(Coupon1);
        couponMapper.save(Coupon2);
        couponMapper.save(Coupon3);
        couponMapper.save(Coupon4);
        couponMapper.save(Coupon5);
        couponMapper.save(Coupon6);
        couponMapper.save(Coupon7);
        couponMapper.save(Coupon8);
        couponMapper.save(Coupon9);
        couponMapper.save(Coupon10);
        couponMapper.save(Coupon11);
        couponMapper.save(Coupon12);
        couponMapper.save(Coupon13);
        couponMapper.save(Coupon14);
        couponMapper.save(Coupon15);
        couponMapper.save(Coupon16);
        couponMapper.save(Coupon17);

        hotel Hotel1 = new hotel(1,"七天酒店","七天酒店坐落在南京大学仙林大道。","南京大学仙林大道","南京大学仙林校区", HotelStar.Five,"18851083388",1.25,4,"WIFI|热水","https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg",0.0,0.0,LocalDateTime.now());
        hotel Hotel2 = new hotel(2,"维度酒店","维度酒店坐落在南京大学广州路。","南京大学广州路","南京大学鼓楼校区", HotelStar.Three,"18851083388",2.5,5,"WIFI|热水","https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg",0.0,0.0,LocalDateTime.now());
        hotel Hotel3 = new hotel(3,"老门东度假酒店","老门东度假酒店坐落在南京大学老门东。","南京大学老门东","老门东", HotelStar.One,"18851083388",3.75,6,"WIFI","https://twitter-content.oss-cn-shanghai.aliyuncs.com/2.jpg",0.0,0.0,LocalDateTime.now());
        hotelMapper.save(Hotel1);
        hotelMapper.save(Hotel2);
        hotelMapper.save(Hotel3);

        hoteledit HotelEdit1 = new hoteledit(1,"七天酒店修改","七天酒店信息修改申请","18851083388",1,"","",LocalDateTime.now(), HotelEditStatus.WAIT, null,null);
        hoteledit HotelEdit2 = new hoteledit(2,"维度酒店修改","维度酒店信息修改申请","18851083388",2,"","",LocalDateTime.now(), HotelEditStatus.WAIT, null,null);
        hoteledit HotelEdit3 = new hoteledit(3,"老门东度假酒店","老门东酒店信息修改申请","18851083388",3,"","",LocalDateTime.now(), HotelEditStatus.PASS, "通过申请",LocalDateTime.now().plusDays(1));
        hotelEditMapper.save(HotelEdit1);
        hotelEditMapper.save(HotelEdit2);
        hotelEditMapper.save(HotelEdit3);

        hotelorder HotelOrder1 = new hotelorder(1,1,1,"七天酒店"
                ,LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2)
                ,null,null,null,null
                ,null,null,"标准间",1,2
                ,false,LocalDateTime.now(),200.0,"客户一","18851083188"
                , OrderState.WAIT);
        hotelorder HotelOrder2 = new hotelorder(2,2,1,"七天酒店"
                ,LocalDateTime.now().minusDays(2),LocalDateTime.now().minusDays(1)
                ,null,null,null,null
                ,null,null,"大床房",1,2
                ,true,LocalDateTime.now(),200.0,"客户二","18851083188"
                , OrderState.WAIT);
        hotelorder HotelOrder3 = new hotelorder(3,3,1,"七天酒店"
                ,LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2)
                ,null,null,LocalDateTime.now(),null
                ,null,null,"标准间",1,2
                ,true,LocalDateTime.now(),200.0,"客户三","18851083188"
                , OrderState.CANCEL);
        hotelorder HotelOrder4 = new hotelorder(4,1,1,"七天酒店"
                ,LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2)
                ,LocalDateTime.now().plusHours(5),null,null,LocalDateTime.now().plusDays(1)
                ,LocalDateTime.now().plusDays(2),null,"标准间",1,2
                ,false,LocalDateTime.now(),200.0,"客户一","18851083188"
                , OrderState.CHECKOUT);
        hotelorder HotelOrder5 = new hotelorder(5,1,1,"七天酒店"
                ,LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2)
                ,LocalDateTime.now().plusDays(1),null,null,LocalDateTime.now().plusDays(2)
                ,null,null,"标准间",1,2
                ,false,LocalDateTime.now(),200.0,"客户一","18851083188"
                , OrderState.CHECKIN);
        hotelorder HotelOrder6 = new hotelorder(6,1,1,"七天酒店",LocalDateTime.now().plusDays(1)
                ,LocalDateTime.now().plusDays(2)
                ,null,LocalDateTime.now().plusDays(3),null,null
                ,null,null,"标准间",1,2
                ,false,LocalDateTime.now(),200.0,"客户一","18851083188"
                , OrderState.REFUSE);
        orderMapper.save(HotelOrder1);
        orderMapper.save(HotelOrder2);
        orderMapper.save(HotelOrder3);
        orderMapper.save(HotelOrder4);
        orderMapper.save(HotelOrder5);
        orderMapper.save(HotelOrder6);

        hotelroom HotelRoom1 = new hotelroom(1,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,1,"大床房",2,"WIFI|热水","");
        hotelroom HotelRoom2 = new hotelroom(2,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,1,"标准间",2,"WIFI|热水","");
        hotelroom HotelRoom3 = new hotelroom(3,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,2,"大床房",2,"WIFI|热水","");
        hotelroom HotelRoom4 = new hotelroom(4,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,2,"标准间",2,"WIFI|热水","");
        hotelroom HotelRoom5 = new hotelroom(5,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,3,"大床房",2,"WIFI|热水","");
        hotelroom HotelRoom6 = new hotelroom(6,200.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10",10,3,"标准间",2,"WIFI|热水","");
        roomMapper.save(HotelRoom1);
        roomMapper.save(HotelRoom2);
        roomMapper.save(HotelRoom3);
        roomMapper.save(HotelRoom4);
        roomMapper.save(HotelRoom5);
        roomMapper.save(HotelRoom6);

        hotelserve HotelServe1 = new hotelserve(1,"WIFI",4,LocalDateTime.now());
        hotelserve HotelServe2 = new hotelserve(2,"热水",4,LocalDateTime.now());
        hotelServeMapper.save(HotelServe1);
        hotelServeMapper.save(HotelServe2);

        hotelsituation hotelsituation1 = new hotelsituation(1,1,TimeHelper.dateToString(LocalDateTime.now().minusDays(2)),10,1,6,7,8,20);
        hotelsituation hotelsituation2 = new hotelsituation(2,2,TimeHelper.dateToString(LocalDateTime.now().minusDays(2)),12,3,8,0,6,20);
        hotelsituation hotelsituation3 = new hotelsituation(3,1,TimeHelper.dateToString(LocalDateTime.now().minusDays(1)),10,1,6,7,8,20);
        hotelsituation hotelsituation4 = new hotelsituation(4,2,TimeHelper.dateToString(LocalDateTime.now().minusDays(1)),12,3,8,0,6,20);
        hotelsituation hotelsituation5 = new hotelsituation(5,2,TimeHelper.dateToString(LocalDateTime.now()),10,1,6,7,8,20);
        hotelsituation hotelsituation6 = new hotelsituation(6,3,TimeHelper.dateToString(LocalDateTime.now()),12,3,8,0,6,20);
        hotelSituationMapper.save(hotelsituation1);
        hotelSituationMapper.save(hotelsituation2);
        hotelSituationMapper.save(hotelsituation3);
        hotelSituationMapper.save(hotelsituation4);
        hotelSituationMapper.save(hotelsituation5);
        hotelSituationMapper.save(hotelsituation6);

        reply Reply1 = new reply(1,1,-1,1,"客户一","回复一号订单的一号回复",LocalDateTime.now());
        reply Reply2 = new reply(2,1,-1,1,"客户一","回复一号订单的二号回复",LocalDateTime.now().plusHours(1));
        reply Reply3 = new reply(3,1,1,1,"客户一","回复一号回复的一号二级回复",LocalDateTime.now().plusHours(2));
        reply Reply4 = new reply(4,1,1,1,"客户一","回复一号回复的二号二级回复",LocalDateTime.now().plusHours(3));
        reply Reply5 = new reply(5,2,-1,1,"客户一","回复二号订单",LocalDateTime.now().plusHours(4));
        reply Reply6 = new reply(6,3,-1,1,"客户一","回复三号订单",LocalDateTime.now().plusHours(5));
        replyMapper.save(Reply1);
        replyMapper.save(Reply2);
        replyMapper.save(Reply3);
        replyMapper.save(Reply4);
        replyMapper.save(Reply5);
        replyMapper.save(Reply6);

        roomtype RoomType1 = new roomtype(1,"大床房",4,LocalDateTime.now());
        roomtype RoomType2 = new roomtype(2,"标准间",4,LocalDateTime.now());
        roomtype RoomType3 = new roomtype(3,"总统套房",4,LocalDateTime.now());
        roomTypeMapper.save(RoomType1);
        roomTypeMapper.save(RoomType2);
        roomTypeMapper.save(RoomType3);
    }
}
