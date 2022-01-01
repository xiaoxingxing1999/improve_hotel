package com.example.hotel.data.hotel;

import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel.hotel;
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
public class HotelMapperTest {
    @Autowired
    HotelMapper hotelMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        hotel Hotel = new hotel(-1,"save","save简介","save地址","老门东",
                HotelStar.One,"18851083288",0.0,5,"",""
                ,0.0,0.0, LocalDateTime.now());
        hotelMapper.save(Hotel);
    }

    @Test
    @Transactional
    @Rollback
    public void selectByManagerId() {
        Assert.assertNotNull(hotelMapper.selectByManagerId(4));
    }

    @Test
    @Transactional
    @Rollback
    public void selectById() {
        Assert.assertNotNull(hotelMapper.selectById(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectAllHotel() {
        Assert.assertNotNull(hotelMapper.selectAllHotel());
    }

    @Test
    @Transactional
    @Rollback
    public void updateHotel() {
        hotelMapper.updateHotel("new Name","new Description","15511691076","","",3);
    }

    @Test
    @Transactional
    @Rollback
    public void updateManager() {
        hotelMapper.updateManager(1,2);
    }

    @Test
    @Transactional
    @Rollback
    public void updateHotelRate() {
        hotelMapper.updateHotelRate(1,2.5);
        hotel Hotel = hotelMapper.findById(1).orElse(null);
        Assert.assertEquals(2.5,Hotel.getRate(),1e-7);
    }

    @Test
    @Transactional
    @Rollback
    public void selectByName() {
        Assert.assertNotNull(hotelMapper.selectByName("七天酒店"));
    }

    @Test
    @Transactional
    @Rollback
    public void getHotelIds() {
        Assert.assertNotNull(hotelMapper.getHotelIds());
    }

    @Test
    @Transactional
    @Rollback
    public void getHotelNumber() {
        Assert.assertNotNull(hotelMapper.getHotelNumber("2020-06-20"));
    }
}