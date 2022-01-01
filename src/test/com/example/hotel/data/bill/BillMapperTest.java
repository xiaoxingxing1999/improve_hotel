package com.example.hotel.data.bill;

import com.example.hotel.po.Bill.bill;
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
public class BillMapperTest {

    @Autowired
    BillMapper billMapper;

    @Before
    public void before(){
        try{
            Thread.sleep(200);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void save(){
        bill Bill = new bill(1,1,10.0,"测试账单");
        billMapper.save(Bill);
    }

    @Test
    @Transactional
    @Rollback
    public void findByUid() {
        Assert.assertNotNull(billMapper.findByUid(1));
    }

    @Test
    @Transactional
    @Rollback
    public void findAll() {
        Assert.assertNotNull(billMapper.findAll());
    }
}