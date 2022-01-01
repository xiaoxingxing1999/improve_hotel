package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotelserve;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServeMapperTest {
    @Autowired
    HotelServeMapper hotelServeMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        hotelserve HotelServe = new hotelserve(1,"测试HotelServe",1, LocalDateTime.now());
        hotelServeMapper.save(HotelServe);
    }

    @Test
    @Transactional
    @Rollback
    public void selectByName() {
        Assert.assertNotNull(hotelServeMapper.selectByName("WIFI"));
    }

    @Test
    @Transactional
    @Rollback
    public void selectAllNames() {
        Assert.assertNotNull(hotelServeMapper.selectAllNames());
    }
}