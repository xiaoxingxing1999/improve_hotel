package com.example.hotel.data.hotel;

import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.po.Hotel.hoteledit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Access;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelEditMapperTest {
    @Autowired
    HotelEditMapper hotelEditMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        hoteledit HotelEdit = new hoteledit(1,"测试修改申请","测试酒店申请描述","18851083288",
                2,"","", LocalDateTime.now(), HotelEditStatus.WAIT, null, null);
        hotelEditMapper.save(HotelEdit);
    }

    @Test
    @Transactional
    @Rollback
    public void findByManager() {
        Assert.assertNotNull(hotelEditMapper.findByHotel(1));
    }

    @Test
    @Transactional
    @Rollback
    public void findByStatus() {
        Assert.assertNotNull(hotelEditMapper.findByStatus(HotelEditStatus.WAIT.ordinal()));
    }

    @Test
    @Transactional
    @Rollback
    public void findAllEdits() {
        Assert.assertNotNull(hotelEditMapper.findAllEdits());
    }

    @Test
    @Transactional
    @Rollback
    public void reply() {
        hotelEditMapper.reply(HotelEditStatus.PASS.ordinal(),"通过申请",LocalDateTime.now(),1);
        hoteledit HotelEdit = hotelEditMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelEdit);
        Assert.assertEquals(HotelEdit.getReplyContent(),"通过申请");
    }
}