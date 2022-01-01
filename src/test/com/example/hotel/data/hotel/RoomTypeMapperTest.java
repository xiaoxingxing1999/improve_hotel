package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.roomtype;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomTypeMapperTest {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        roomtype RoomType = new roomtype(1,"测试房间类型",1, LocalDateTime.now());
        roomTypeMapper.save(RoomType);
        Assert.assertNotNull(roomTypeMapper.selectByName("测试房间类型"));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByName() {
        Assert.assertNotNull(roomTypeMapper.selectByName("大床房"));
    }

    @Test
    @Transactional
    @Rollback
    public void selectAllNames() {
        Assert.assertNotNull(roomTypeMapper.selectAllNames());
    }
}