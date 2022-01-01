package com.example.hotel.bl.Role;

import com.example.hotel.Form.Client.*;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.bill.BillMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.UserMapper;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.LoginVO;
import org.apache.tomcat.jni.Time;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    ClientService clientService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    BillMapper billMapper;

    private String clientToken;
    private String clientToken2;
    private String clientToken3;
    private String hotelManagerToken;

    @Before
    public void setUp() throws Exception {
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken = res.getToken();
        loginForm = new LoginForm("2@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken2 = res.getToken();
        loginForm = new LoginForm("3@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken3 = res.getToken();
        loginForm = new LoginForm("4@qq.com","123456");
        res = userService.login(loginForm);
        this.hotelManagerToken = res.getToken();
    }

    @Test
    @Transactional
    @Rollback
    public void register() throws Exception{
        int cnt = 0;
        //客户正常注册
        ClientForm clientForm = new ClientForm("","940@qq.com",
                "123456","Register","18851083288");
        clientService.addClient(clientForm);
        Assert.assertNotNull(userMapper.findByEmail("1@qq.com"));

        //邮箱重名
        clientForm = new ClientForm("","940@qq.com",
                "123456","Register","18851083288");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("该邮箱已经注册过请直接登录",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //手机号长度不为11
        clientForm = new ClientForm("","100@qq.com",
                "123456","Register","1885108328");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号长度错误",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //手机号长度不全为数字
        clientForm = new ClientForm("","100@qq.com",
                "123456","Register","1885108328a");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号应该全为数字",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //密码长度<6
        clientForm = new ClientForm("","100@qq.com",
                "12345","Register","18851083288");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //密码长度>20
        clientForm = new ClientForm("","100@qq.com",
                "123456789123456789123","Register","18851083288");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //密码不全为数字和字母
        clientForm = new ClientForm("","100@qq.com",
                "12345!a","Register","18851083288");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码应该只包含数字和字母",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //用户名不符合格式
        clientForm = new ClientForm("","100@qq.com",
                "123456","R","18851083288");
        try{
            clientService.addClient(clientForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

    }

    @Test
    @Transactional
    @Rollback
    public void addOrder() throws Exception {
        int cnt = 0;
        //格式正确
        OrderForm orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",clientToken);
        clientService.addOrder(orderForm);
        Assert.assertNotNull(billMapper.findByUid(1));

        //用户没有登录
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288","");
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户不是客户
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",hotelManagerToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //账单金额小于等于0
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,0.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单金额应该大于0",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //房间数量小于等于0
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",0,1
                ,false,1.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("房间数量应该大于0",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //入住时间比现在早
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().minusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单时间格式错误",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //入住时间晚于退房时间
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().minusDays(2))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单时间格式错误",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //当前房间数量不足
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",100,1
                ,false,1.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("该时间范围内剩余房间数量不足",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        //所能容纳的人数不足
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,4
                ,false,1.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您的订单中的人数超过房间所能容纳的人数",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        //用户剩余的信用值不足
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,100.0,"客户一","18851083288",clientToken);
        try{
            clientService.addOrder(orderForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户的账户余额不足",e.getMessage());
        }
        Assert.assertEquals(9,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void annualOrder() throws Exception{
        int cnt = 0;
        //6小时外取消
        OrderForm orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusHours(9))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",clientToken);
        clientService.addOrder(orderForm);
        //拉取到最新一次新建的订单
        List<hotelorder> hotelorders = orderMapper.findByUser(1);
        clientService.annualOrder(hotelorders.get(hotelorders.size()-1).getId(),clientToken);
        List<bill> bills = billMapper.findByUid(1);
        Assert.assertEquals(1,bills.get(bills.size()-1).getCredit(),1e-7);

        //6小时内取消
        orderForm = new OrderForm(1,"AddOrder", TimeHelper.getTimeStamp(LocalDateTime.now().plusHours(1))
                ,TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(2)),"大床房",1,1
                ,false,1.0,"客户一","18851083288",clientToken);
        clientService.addOrder(orderForm);
        hotelorders = orderMapper.findByUser(1);
        clientService.annualOrder(hotelorders.get(hotelorders.size()-1).getId(),clientToken);
        bills = billMapper.findByUid(1);
        Assert.assertEquals(0.5,bills.get(bills.size()-1).getCredit(),1e-7);

        //用户未登录
        try{
            clientService.annualOrder(1,"");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户类型不是客户
        try{
            clientService.annualOrder(1,hotelManagerToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //没有找到对应订单
        try{
            clientService.annualOrder(100,clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到订单信息",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //用户取消他人订单
        try{
            clientService.annualOrder(2,clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("无权取消他人订单",e.getMessage());
        }
        Assert.assertEquals(4,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void updateVIP() throws Exception{
        int cnt = 0;
        //更新企业会员
        ClientVIPForm clientVIPForm = new ClientVIPForm(VIPType.ENTERPRISE,"企业会员", clientToken2);
        userService.updateVIP(clientVIPForm);

        //更新普通会员
        clientVIPForm = new ClientVIPForm(VIPType.NORMAL_LEVEL1,"2000-09-01", clientToken2);
        userService.updateVIP(clientVIPForm);

        //用户没有登陆
        clientVIPForm = new ClientVIPForm(VIPType.NORMAL_LEVEL1,"2000-09-01", "");
        try{
            userService.updateVIP(clientVIPForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户不是客户
        clientVIPForm = new ClientVIPForm(VIPType.NORMAL_LEVEL1,"2000-09-01", hotelManagerToken);
        try{
            userService.updateVIP(clientVIPForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //用户不是会员
        clientVIPForm = new ClientVIPForm(VIPType.NORMAL_LEVEL1,"2000-09-01", clientToken);
        try{
            userService.updateVIP(clientVIPForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您还不是会员",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //用户已经登记过会员
        clientVIPForm = new ClientVIPForm(VIPType.NORMAL_LEVEL1,"2000-09-01", clientToken3);
        try{
            userService.updateVIP(clientVIPForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("会员信息已经登记过请不要重复登记",e.getMessage());
        }
        Assert.assertEquals(4,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void comment() throws Exception{
        int cnt = 0;
        //评分小于0
        CommentForm commentForm = new CommentForm(4,"测试Comment","没有什么内容"
                ,-1.0,clientToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单评分应当在0到5分之间",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //评分大于5
        commentForm = new CommentForm(4,"测试Comment","没有什么内容"
                ,6.0,clientToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单评分应当在0到5分之间",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //发表评论
        commentForm = new CommentForm(4,"测试Comment","没有什么内容"
                ,0.0,clientToken);
        clientService.comment(commentForm);

        //用户没有登录
        commentForm = new CommentForm(1,"测试Comment","没有什么内容"
                ,0.0,"");
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //用户类型不为客户
        commentForm = new CommentForm(1,"测试Comment","没有什么内容"
                ,0.0,hotelManagerToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //没有找到订单
        commentForm = new CommentForm(100,"测试Comment","没有什么内容"
                ,0.0,clientToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到这个订单",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //评价他人订单
        commentForm = new CommentForm(4,"测试Comment","没有什么内容"
                ,0.0,clientToken2);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有权限评价他人的订单",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //订单还没有完成
        commentForm = new CommentForm(1,"测试Comment","没有什么内容"
                ,0.0,clientToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单还没有完成无法评价",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        //订单不能重复评价
        commentForm = new CommentForm(4,"测试Comment","没有什么内容"
                ,0.0,clientToken);
        try{
            clientService.comment(commentForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户已经评论过这个订单，请不要重复评论",e.getMessage());
        }
        Assert.assertEquals(8,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void OrderMatchCoupons() throws Exception{
        int cnt = 0;
        hotelorder hotelorder = new hotelorder(1, TimeHelper.getTimeStamp(LocalDateTime.now())
                , TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)), 1, 200.0);
        Assert.assertNotNull(clientService.matchCoupons(clientToken,hotelorder));

        try{
            clientService.matchCoupons("",hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            clientService.matchCoupons(hotelManagerToken,hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //没有找到对应酒店
        hotelorder = new hotelorder(10, TimeHelper.getTimeStamp(LocalDateTime.now())
                , TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)), 1, 200.0);
        try{
            clientService.matchCoupons(clientToken,hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应的酒店信息",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //订单金额为负
        hotelorder = new hotelorder(1, TimeHelper.getTimeStamp(LocalDateTime.now())
                , TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)), 1, -1.0);
        try{
            clientService.matchCoupons(clientToken,hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单金额不能为负",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //订单房间数量小于0
        hotelorder = new hotelorder(1, TimeHelper.getTimeStamp(LocalDateTime.now())
                , TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)), -1, 200.0);
        try{
            clientService.matchCoupons(clientToken,hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单房间数不能为负",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //入住时间晚于退房时间
        hotelorder = new hotelorder(1, TimeHelper.getTimeStamp(LocalDateTime.now().plusHours(1))
                , TimeHelper.getTimeStamp(LocalDateTime.now()), 1, 200.0);
        try{
            clientService.matchCoupons(clientToken,hotelorder);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("入住时间应该早于退房时间",e.getMessage());
        }
        Assert.assertEquals(6,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getMyBills() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(clientService.getMyBills(clientToken));
        try{
            clientService.getMyBills("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            clientService.getMyBills(hotelManagerToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getMyOrders() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(clientService.getMyOrders(clientToken));
        try{
            clientService.getMyOrders("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            clientService.getMyOrders(hotelManagerToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

}