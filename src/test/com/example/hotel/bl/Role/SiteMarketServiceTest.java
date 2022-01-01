package com.example.hotel.bl.Role;

import com.example.hotel.Form.SiteMarket.CreditForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.BillService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.hotel.BizRegionMapper;
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

import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteMarketServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    SiteMarketService siteMarketService;
    @Autowired
    BillService billService;
    @Autowired
    BizRegionMapper bizRegionMapper;

    private String siteMarketToken = "";
    private String clientToken = "";

    @Before
    public void setUp() throws Exception {
        LoginForm loginForm = new LoginForm("1@qq.com","123456");
        LoginVO res = userService.login(loginForm);
        Assert.assertNotNull(res);
        this.clientToken = res.getToken();
        loginForm = new LoginForm("7@qq.com","123456");
        res = userService.login(loginForm);
        this.siteMarketToken = res.getToken();
    }

    @Test
    @Transactional
    @Rollback
    public void AddCredit() throws Exception {
        int cnt = 0;
        CreditForm creditForm = new CreditForm(1,100.0,siteMarketToken);
        siteMarketService.updateCredit(creditForm);
        creditForm = new CreditForm(1,100.0,"");

        try{
            siteMarketService.updateCredit(creditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        creditForm = new CreditForm(1,100.0,clientToken);
        try{
            siteMarketService.updateCredit(creditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        creditForm = new CreditForm(100,100.0,siteMarketToken);
        try{
            siteMarketService.updateCredit(creditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("系统中没有该用户",e.getMessage());
        }
        Assert.assertEquals(3,cnt);

        creditForm = new CreditForm(1,-1.0,siteMarketToken);
        try{
            siteMarketService.updateCredit(creditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("充值金额应该大于0",e.getMessage());
        }
        Assert.assertEquals(4,cnt);

        creditForm = new CreditForm(7,100.0,siteMarketToken);
        try{
            siteMarketService.updateCredit(creditForm);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("酒店营销人员不能为其他类型用户充值",e.getMessage());
        }
        Assert.assertEquals(5,cnt);
    }


    @Test
    @Transactional
    @Rollback
    public void addBizRegion() throws Exception{
        int cnt = 0;
        siteMarketService.addBizRegion(siteMarketToken,"总统府");
        Assert.assertNotNull(bizRegionMapper.findByName("总统府"));

        try{
            siteMarketService.addBizRegion("","总统府");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            siteMarketService.addBizRegion(clientToken,"总统府");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);

        try{
            siteMarketService.addBizRegion(siteMarketToken,"总统府");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("已经有重名的商圈",e.getMessage());
        }
        Assert.assertEquals(3,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void situation() throws ServiceException {
        int cnt = 0;
        Assert.assertNotNull(siteMarketService.situation(siteMarketToken));
        try{
            siteMarketService.situation("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            siteMarketService.situation(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllBills() throws Exception {
        int cnt = 0;
        Assert.assertNotNull(siteMarketService.getAllBills(siteMarketToken));
        try{
            siteMarketService.getAllBills("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
        try{
            siteMarketService.getAllBills(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void getAllCoupons() throws Exception{
        int cnt = 0;
        Assert.assertNotNull(siteMarketService.getAllCoupons(siteMarketToken));
        try{
            siteMarketService.getAllCoupons("");
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有登陆",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        try{
            siteMarketService.getAllCoupons(clientToken);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("用户没有权限",e.getMessage());
        }
        Assert.assertEquals(2,cnt);
    }
}