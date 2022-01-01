package com.example.hotel.bl.Role;

import com.example.hotel.Form.HotelManager.CheckHotelEditForm;
import com.example.hotel.Form.HotelManager.HotelManagerForm;
import com.example.hotel.Form.Manager.HotelForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.UserService;
import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.util.Response.ServiceException;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    ManagerService managerService;

    private String managerToken;
    private String clientToken;

    @Before
    public void setUp() throws Exception {
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken = res.getToken();
        loginForm = new LoginForm("8@qq.com","123456");
        res = userService.login(loginForm);
        this.managerToken = res.getToken();
    }

    @Test
    @Transactional
    @Rollback
    public void addHotelManager() throws ServiceException{
        int cnt = 0;
        //没有登录
        HotelManagerForm hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","18851083288","",1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户权限不是管理人员
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","18851083288",clientToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //用户手机号不为11位
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","1885108328",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号长度错误",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //用户手机号应该全为数字
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","1885108328a",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("手机号应该全为数字",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //用户名小于2位
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张","18851083288",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字至少应该为2个字符及以上",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //用户名应该全为中文
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张zhy","18851083288",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("名字应该为全中文",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //用户密码小于6位
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"12345","张洪胤","18851083288",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        //用户密码大于20位
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456789012345678901","张洪胤","18851083288",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("密码长度应该在6-20位",e.getMessage());
        }
        Assert.assertEquals(8,cnt);

        //没有对应ID的酒店
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","18851083288",managerToken,10);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应的酒店信息",e.getMessage());
        }
        Assert.assertEquals(9,cnt);

        //正常注册
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","9@qq.com"
                ,"123456","张洪胤","18851083288",managerToken,1);
        managerService.addHotelManager(hotelManagerForm);

        //用户的邮箱已经注册过
        hotelManagerForm = new HotelManagerForm("测试注册酒店管理人员","1@qq.com"
                ,"123456","张洪胤","18851083288",managerToken,1);
        try{
            managerService.addHotelManager(hotelManagerForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("该邮箱已经注册过请直接登录",e.getMessage());
        }
        Assert.assertEquals(10,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void addHotel() throws ServiceException {
        int cnt = 0;
        //用户没有登陆
        HotelForm hotelForm = new HotelForm("南大国际会议中心","hotelDesc"
                ,"address","南京大学仙林校区", HotelStar.One,"18851083288"
                ,"","picture","",100.0,30.0);
        try{
            managerService.addHotel(hotelForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户没有管理员权限
        hotelForm = new HotelForm("南大国际会议中心","hotelDesc"
                ,"address","南京大学仙林校区", HotelStar.One,"18851083288"
                ,"","picture",clientToken,100.0,30.0);
        try{
            managerService.addHotel(hotelForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //用户新建重名酒店
        hotelForm = new HotelForm("七天酒店","hotelDesc"
                ,"address","南京大学仙林校区", HotelStar.One,"18851083288"
                ,"","picture",managerToken,100.0,30.0);
        try{
            managerService.addHotel(hotelForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("已经有重名的酒店了",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //没有找到对应商圈
        hotelForm = new HotelForm("测试酒店","hotelDesc"
                ,"address","南京大学", HotelStar.One,"18851083288"
                ,"","picture",managerToken,100.0,30.0);
        try{
            managerService.addHotel(hotelForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应商圈",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //用户新建成功
        hotelForm = new HotelForm("南大国际会议中心","hotelDesc"
                ,"address","南京大学仙林校区", HotelStar.One,"18851083288"
                ,"","picture",managerToken,100.0,30.0);
        managerService.addHotel(hotelForm);
    }

    @Test
    @Transactional
    @Rollback
    public void reply() throws ServiceException {
        int cnt = 0;
        //通过
        CheckHotelEditForm checkHotelEditForm = new CheckHotelEditForm(1,
                HotelEditStatus.PASS, "接受", managerToken);
        managerService.reply(checkHotelEditForm);

        //拒绝
        checkHotelEditForm = new CheckHotelEditForm(2,
                HotelEditStatus.REFUSE, "拒绝", managerToken);
        managerService.reply(checkHotelEditForm);

        //用户没有登陆
        checkHotelEditForm = new CheckHotelEditForm(1,
                HotelEditStatus.PASS, "接受", "");
        try {
            managerService.reply(checkHotelEditForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("用户没有登陆", e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户没有权限
        checkHotelEditForm = new CheckHotelEditForm(1,
                HotelEditStatus.PASS, "接受", clientToken);
        try {
            managerService.reply(checkHotelEditForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("用户没有权限", e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //没有找到
        checkHotelEditForm = new CheckHotelEditForm(10,
                HotelEditStatus.PASS, "接受", managerToken);
        try {
            managerService.reply(checkHotelEditForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("没有找到酒店信息修改申请", e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //不能处理已经处理过的
        checkHotelEditForm = new CheckHotelEditForm(3,
                HotelEditStatus.PASS, "接受", managerToken);
        try {
            managerService.reply(checkHotelEditForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("该酒店修改申请已经处理完成", e.getMessage());
        }
        Assert.assertEquals(4,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllHotelManager() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(managerService.getAllHotelManager(managerToken));
        try{
            managerService.getAllHotelManager("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            managerService.getAllHotelManager(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllHotelEdit() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(managerService.getAllHotelEdit(managerToken));
        try{
            managerService.getAllHotelEdit("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            managerService.getAllHotelEdit(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }
}