package com.example.hotel.data.user;

import com.example.hotel.enums.VIPType;
import com.example.hotel.po.User.user;
import org.junit.Assert;
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
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void findByEmail() {
        Assert.assertNotNull(userMapper.findByEmail("1@qq.com"));
    }

    @Test
    @Transactional
    @Rollback
    public void updateUser() {
        userMapper.updateUser("","updateUser","18851083388",1);
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Assert.assertEquals("updateUser", User.getUserName());
    }

    @Test
    @Transactional
    @Rollback
    public void updatePassWord() {
        userMapper.updatePassWord(1,"654321");
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Assert.assertEquals("654321", User.getPassword());
    }

    @Test
    @Transactional
    @Rollback
    public void updateCredit() {
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Double before = User.getCredit();
        System.out.println(userMapper.updateCredit(1,10.0));
        User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Double after = User.getCredit();
    }

    @Test
    @Transactional
    @Rollback
    public void updateTotalCredit() {
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Double before = User.getTotalcredit();
        System.out.println(userMapper.updateTotalCredit(1,10.0));
        user User2 = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User2);
        Double after = User2.getTotalcredit();
    }

    @Test
    @Transactional
    @Rollback
    public void updateVIP() {
        userMapper.updateVIP(1,"0000-00-00");
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Assert.assertEquals("0000-00-00",User.getVipInfo());
    }

    @Test
    @Transactional
    @Rollback
    public void updateVIPEnterprise() {
        userMapper.updateVIPEnterprise(1, VIPType.ENTERPRISE.ordinal(),"企业会员");
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Assert.assertEquals(VIPType.ENTERPRISE,User.getVipType());
        Assert.assertEquals("企业会员", User.getVipInfo());
    }

    @Test
    @Transactional
    @Rollback
    public void updateVIPLevel() {
        userMapper.updateVIPLevel(1, VIPType.NORMAL_LEVEL5.ordinal());
        user User = userMapper.findById(1).orElse(null);
        Assert.assertNotNull(User);
        Assert.assertEquals(VIPType.NORMAL_LEVEL5,User.getVipType());
    }

    @Test
    @Transactional
    @Rollback
    public void getAllManagers() {
        Assert.assertNotNull(userMapper.getAllManagers());
    }
}