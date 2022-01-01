package com.example.hotel.bl;

import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.Form.User.ChangeInfoForm;
import com.example.hotel.Form.User.ChangePwdForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
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


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    private String clientToken;
    private String clientToken2;
    private String clientToken3;
    private String hotelManagerToken;
    private String hotelManagerToken2;
    private String hotelManagerToken3;
    private String siteMarketToken;
    private String managerToken;

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
        Assert.assertNotNull(res);
        this.hotelManagerToken = res.getToken();

        loginForm = new LoginForm("5@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.hotelManagerToken2 = res.getToken();

        loginForm = new LoginForm("6@qq.com","123456");
        res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.hotelManagerToken3 = res.getToken();

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
    public void login() throws Exception{
        int cnt = 0;
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        UserHelper.logOut(res.getToken());
        loginForm = new LoginForm("0@qq.com","123456");
        try{
            userService.login(loginForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应的用户",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        loginForm = new LoginForm("1@qq.com","223456");
        try{
            userService.login(loginForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户的账号或者密码错误",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void changePwd() throws Exception{
        int cnt = 0;
        ChangePwdForm changePwdForm = new ChangePwdForm(clientToken, "123456"
                ,"234567");
        userService.changePwd(changePwdForm);
        //再次登陆
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken = res.getToken();

        //用户没有登陆
        changePwdForm = new ChangePwdForm("", "123456"
                ,"234567");
        try{
            userService.changePwd(changePwdForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户新密码小于6位
        changePwdForm = new ChangePwdForm(clientToken, "123456"
                ,"23456");
        try{
            userService.changePwd(changePwdForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //用户新密码大于20位
        changePwdForm = new ChangePwdForm(clientToken, "123456"
                ,"123456789012345678901");
        try{
            userService.changePwd(changePwdForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //用户新密码不全为数字和字母
        changePwdForm = new ChangePwdForm(clientToken, "123456"
                ,"23456!Z");
        try{
            userService.changePwd(changePwdForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码应该只包含数字和字母",e.getMessage());
        }
        Assert.assertEquals(4,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void updateInfo() throws Exception{
        int cnt = 0;
        ChangeInfoForm changeInfoForm = new ChangeInfoForm("修改客户",
                "18851083288","",clientToken);
        userService.updateInfo(changeInfoForm);

        changeInfoForm = new ChangeInfoForm("修改酒店工作人员",
                "18851083288","",hotelManagerToken);
        userService.updateInfo(changeInfoForm);

        changeInfoForm = new ChangeInfoForm("修改网站营销人员",
                "18851083288","",siteMarketToken);
        userService.updateInfo(changeInfoForm);

        changeInfoForm = new ChangeInfoForm("修改网站管理员",
                "18851083288","",managerToken);
        userService.updateInfo(changeInfoForm);

        changeInfoForm = new ChangeInfoForm("修",
                "18851083288","",clientToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        changeInfoForm = new ChangeInfoForm("修",
                "18851083288","",hotelManagerToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        changeInfoForm = new ChangeInfoForm("修",
                "18851083288","",siteMarketToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        changeInfoForm = new ChangeInfoForm("修",
                "18851083288","",managerToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        changeInfoForm = new ChangeInfoForm("修zhy",
                "18851083288","",hotelManagerToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字应该为全中文",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        changeInfoForm = new ChangeInfoForm("修zhy",
                "18851083288","",siteMarketToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字应该为全中文",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        changeInfoForm = new ChangeInfoForm("修zhy",
                "18851083288","",managerToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字应该为全中文",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        changeInfoForm = new ChangeInfoForm("修改客户",
                "1885108328","",clientToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号长度错误",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        changeInfoForm = new ChangeInfoForm("修改客户",
                "1885108328q","",clientToken);
        try{
            userService.updateInfo(changeInfoForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号应该全为数字",e.getMessage());
        }
        Assert.assertEquals(9,cnt);

    }

    @Test
    @Transactional
    @Rollback
    public void addReply() throws Exception{
        int cnt = 0;
        ReplyForm replyForm = new ReplyForm(1,1,"测试reply",clientToken);
        userService.addReply(replyForm);

        replyForm = new ReplyForm(1,1,"测试reply","");
        try{
            userService.addReply(replyForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        replyForm = new ReplyForm(100,1,"测试reply",clientToken);
        try{
            userService.addReply(replyForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到这个订单回复",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void AllReplies() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(userService.findByComment(1, 0));
        try{
            userService.findByComment(100,0);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到这个订单回复",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getUserInfo() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(userService.getUserInfo(clientToken));
        try{
            userService.getUserInfo("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

    @Test
    public void getBizRegions() throws Exception{
        Assert.assertNotNull(userService.getBizRegions());
    }

    @Test
    @Transactional
    @Rollback
    public void getOrderComments() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(userService.getOrderComments(clientToken,1));
        try{
            userService.getOrderComments("",1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void gerHotelComments() throws  Exception{
        int cnt = 0;
        Assert.assertNotNull(userService.getHotelComments(clientToken,1,0));
        try{
            userService.getHotelComments("",1,0);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            userService.getHotelComments(clientToken,10,0);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应的酒店信息",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getReplyReply() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(userService.getReplyReply(clientToken,1,0));
        try{
            userService.getReplyReply("",1,0);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            userService.getReplyReply(clientToken,0,0);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到这个回复",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getMyReply() throws Exception{
        int cnt = 1;
        Assert.assertNotNull(userService.getMyReply(clientToken,0));
        try{
            userService.getMyReply("",0);
        }catch (Exception e){
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllServe() throws ServiceException {
        int cnt = 0;
        Assert.assertNotNull(userService.getAllServe(hotelManagerToken));
        Assert.assertNotNull(userService.getAllServe(clientToken));
        try{
            userService.getAllServe("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

}