package com.example.hotel.bl.Role;

import com.example.hotel.Form.HotelManager.*;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.Hotel.HotelRoomService;
import com.example.hotel.bl.Hotel.RoomTypeService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.OrderState;
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
public class HotelManagerServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    HotelManagerService hotelManagerService;
    @Autowired
    RoomMapper roomMapper;

    private String clientToken;
    private String hotelManagerToken;
    private String hotelManagerToken2;
    private String hotelManagerToken3;
    private String siteMarketToken;

    @Before
    public void setUp() throws Exception {
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken = res.getToken();
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
        this.hotelManagerToken3 = res.getToken();
        loginForm = new LoginForm("7@qq.com","123456");
        res = userService.login(loginForm);
        this.siteMarketToken = res.getToken();
    }

    @Test
    @Transactional
    @Rollback
    public void addRoom() throws ServiceException{
        int cnt = 0;
        RoomForm roomForm = new RoomForm(1000.0,0,1,"总统套房"
                ,4,"",hotelManagerToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店房间数量应该大于零",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        roomForm = new RoomForm(0.0,1,1,"总统套房"
                ,4,"",hotelManagerToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店房间价格应该大于零",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        roomForm = new RoomForm(1000.0,2,2,"总统套房"
                ,4,"",hotelManagerToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("不能为其他酒店新建房间",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        roomForm = new RoomForm(1000.0,10,1,"总统套房"
                ,4,"",hotelManagerToken,"");
        hotelManagerService.addRoom(roomForm);
        Assert.assertNotNull(roomMapper.findByHotelAndType(1,"总统套房"));

        //用户没有登陆
        roomForm = new RoomForm(1000.0,10,1,"总统套房"
                ,4,"", "","");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //用户不是酒店工作人员
        roomForm = new RoomForm(1000.0,10,1,"总统套房"
                ,4,"", clientToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //没有对应房间类型
        roomForm = new RoomForm(1000.0,10,1,"假的总统套房"
                ,4,"", hotelManagerToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有这个房间类型",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //新建已有的房间
        roomForm = new RoomForm(1000.0,10,1,"标准间"
                ,4,"", hotelManagerToken,"");
        try{
            hotelManagerService.addRoom(roomForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("已经有对应类型的房间，无法新建",e.getMessage());
        }
        Assert.assertEquals(7,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void editHotel() throws ServiceException{
        int cnt = 0;
        HotelEditForm hotelEditForm = new HotelEditForm("修改老门东度假酒店"
                ,"hotelDescription","18851083288"
                ,"","picture",hotelManagerToken3);
        hotelManagerService.editHotel(hotelEditForm);

        hotelEditForm = new HotelEditForm("修改七天酒店"
                ,"hotelDescription","18851083288"
                ,"","picture","");
        try{
            hotelManagerService.editHotel(hotelEditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        hotelEditForm = new HotelEditForm("修改七天酒店"
                ,"hotelDescription","18851083288"
                ,"","picture",clientToken);
        try{
            hotelManagerService.editHotel(hotelEditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        hotelEditForm = new HotelEditForm("修改七天酒店"
                ,"hotelDescription","18851083288"
                ,"","picture",hotelManagerToken);
        try{
            hotelManagerService.editHotel(hotelEditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("有还没被处理的酒店修改申请，请不要重复提交",e.getMessage());
        }
        Assert.assertEquals(3,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void confirm() throws ServiceException{
        int cnt = 0;
        //用户没有登陆
        OrderCheckForm orderCheckForm = new OrderCheckForm(1, OrderState.PASS
                ,"");
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户不是酒店工作人员
        orderCheckForm = new OrderCheckForm(1, OrderState.PASS
                ,clientToken);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //没有找到对应订单
        orderCheckForm = new OrderCheckForm(100, OrderState.PASS
                ,hotelManagerToken);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到这个订单",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //拒绝订单
        orderCheckForm = new OrderCheckForm(2, OrderState.REFUSE
                ,hotelManagerToken);
        hotelManagerService.confirm(orderCheckForm);

        //接受订单
        orderCheckForm = new OrderCheckForm(1, OrderState.PASS
                ,hotelManagerToken);
        hotelManagerService.confirm(orderCheckForm);

        //异常订单
        orderCheckForm = new OrderCheckForm(2, OrderState.ERROR
                ,hotelManagerToken);
        hotelManagerService.confirm(orderCheckForm);

        //订单状态不是PASS，审核状态为CHECKIN
        orderCheckForm = new OrderCheckForm(5, OrderState.CHECKIN
                ,hotelManagerToken);

        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单状态不是PASS状态",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //订单状态不是CHECKIN，审核状态为CHECHOUT
        orderCheckForm = new OrderCheckForm(1, OrderState.CHECKOUT
                ,hotelManagerToken);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单状态不是CHECKIN状态",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

        //审核其他酒店订单
        orderCheckForm = new OrderCheckForm(2, OrderState.PASS
                ,hotelManagerToken2);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("不能审核其他酒店的订单",e.getMessage());
        }
        Assert.assertEquals(6,cnt);

        //审核拒绝的订单
        orderCheckForm = new OrderCheckForm(6, OrderState.PASS
                ,hotelManagerToken);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单已经被拒绝，无法继续处理",e.getMessage());
        }
        Assert.assertEquals(7,cnt);

        //审核取消的订单
        orderCheckForm = new OrderCheckForm(3, OrderState.PASS
                ,hotelManagerToken);
        try{
            hotelManagerService.confirm(orderCheckForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("订单已经被用户取消，无法继续处理",e.getMessage());
        }
        Assert.assertEquals(8,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void delRoom() throws ServiceException{
        int cnt = 0;
        //成功删除
        hotelManagerService.delRoom(hotelManagerToken3, 6);

        //用户没有登陆
        try{
            hotelManagerService.delRoom("", 1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //用户不是酒店管理员
        try{
            hotelManagerService.delRoom(clientToken, 1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //没有对应房间
        try{
            hotelManagerService.delRoom(hotelManagerToken, 100);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到房间信息",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        //不能删除其他酒店的房间
        try{
            hotelManagerService.delRoom(hotelManagerToken2, 1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("您不能删除其他酒店的房间",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        //有正在进行中的订单无法删除订单
        try{
            hotelManagerService.delRoom(hotelManagerToken, 2);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("该房间有正在进行中的订单，无法删除房间",e.getMessage());
        }
        Assert.assertEquals(5,cnt);

    }

    @Test
    @Transactional
    @Rollback
    public void addServe() throws ServiceException {
        int cnt = 0;
        //成功新建
        ServeForm serveForm = new ServeForm("叫醒服务",hotelManagerToken);
        hotelManagerService.addServe(serveForm);

        //没有登录
        serveForm = new ServeForm("叫醒服务","");
        try{
            hotelManagerService.addServe(serveForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //不是酒店管理员
        serveForm = new ServeForm("叫醒服务",clientToken);
        try{
            hotelManagerService.addServe(serveForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //重复服务
        serveForm = new ServeForm("叫醒服务",hotelManagerToken);
        try{
            hotelManagerService.addServe(serveForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("已经有重名的酒店服务",e.getMessage());
        }
        Assert.assertEquals(3,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void addRoomType() throws ServiceException {
        int cnt = 0;
        RoomTypeForm roomTypeForm = new RoomTypeForm("双床房", hotelManagerToken);
        hotelManagerService.addRoomType(roomTypeForm);

        //没有登录
        roomTypeForm = new RoomTypeForm("双床房", "");
        try {
            hotelManagerService.addRoomType(roomTypeForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("用户没有登陆", e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        //没有权限
        roomTypeForm = new RoomTypeForm("双床房", clientToken);
        try {
            hotelManagerService.addRoomType(roomTypeForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("用户没有权限", e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        //重名房间
        roomTypeForm = new RoomTypeForm("双床房", hotelManagerToken);
        try {
            hotelManagerService.addRoomType(roomTypeForm);
        } catch (Exception e) {
            cnt++;
            Assert.assertEquals("已经有重名的房间类型", e.getMessage());
        }
        Assert.assertEquals(3,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void checkEdit() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.findByManager(hotelManagerToken));
        try{
            hotelManagerService.findByManager("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            hotelManagerService.findByManager(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void GetHotelOrders() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.findHotelOrders(hotelManagerToken));
        try{
            hotelManagerService.findHotelOrders("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            hotelManagerService.findHotelOrders(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void HotelSituation() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.hotelSituation(hotelManagerToken));
        try{
            hotelManagerService.hotelSituation("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            hotelManagerService.hotelSituation(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

    }

    @Test
    @Transactional
    @Rollback
    public void HotelAllCoupon() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.getMyCoupon(hotelManagerToken));
        try{
            hotelManagerService.getMyCoupon("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            hotelManagerService.getMyCoupon(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllRoomType() throws ServiceException {
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.getAllRoomType(hotelManagerToken));
        Assert.assertNotNull(hotelManagerService.getAllRoomType(clientToken));
        try{
            hotelManagerService.getAllRoomType("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            hotelManagerService.getAllRoomType(siteMarketToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void hotelInfo() throws ServiceException{
        int cnt = 0;
        Assert.assertNotNull(hotelManagerService.getHotelInfo(hotelManagerToken));
        try{
            hotelManagerService.getHotelInfo("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            hotelManagerService.getHotelInfo(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }
}