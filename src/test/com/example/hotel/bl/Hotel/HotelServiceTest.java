package com.example.hotel.bl.Hotel;

import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.UserService;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.LoginVO;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    HotelService hotelService;

    @Before
    public void before(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void search() throws ServiceException {
        int cnt = 0;
        try{
            hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1))
                    ,TimeHelper.getTimeStamp(LocalDateTime.now()),null,null,null
                    ,null,null,null,null);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("入住时间或者退房时间有误",e.getMessage());
        }
        Assert.assertEquals(1,cnt);

        ArrayList<String> stars = new ArrayList<>();
        stars.add(HotelStar.Five.name());

        List<HotelVO> hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),stars,null,null,
                null,null,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,0,null,
                null,null,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,1000,
                null,null,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,0,1000,
                null,null,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        ArrayList<String> roomTypes = new ArrayList<>();
        roomTypes.add("标准间");

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,null,
                roomTypes,null,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,null,
                null,0.0,null,null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,null,
                null,5.0,null,null);
        Assert.assertEquals(0,hotelVOList.size());

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,null,
                null,null,"七天酒店",null);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        ArrayList<String> hotelServes = new ArrayList<>();
        hotelServes.add("WIFI");
        hotelServes.add("热水");
        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),null,null,null,
                null,null,null, hotelServes);
        Assert.assertEquals(true,(hotelVOList.size() > 0));

        hotelVOList = hotelService.findHotelsInCondition(TimeHelper.getTimeStamp(LocalDateTime.now()),
                TimeHelper.getTimeStamp(LocalDateTime.now().plusDays(1)),stars,0,1000,
                roomTypes,0.0,"七天酒店",hotelServes);
        Assert.assertEquals(true,(hotelVOList.size() > 0));
    }

    @Test
    @Transactional
    @Rollback
    public void hotelDetail() throws ServiceException{
        int cnt = 0;
        HotelVO hotelVO = hotelService.findDetails(1,null);
        Assert.assertNotNull(hotelVO);
        Assert.assertEquals(false, hotelVO.isOrdered());

        hotelVO = hotelService.findDetails(1,1);
        Assert.assertNotNull(hotelVO);
        Assert.assertEquals(true, hotelVO.isOrdered());

        hotelVO = hotelService.findDetails(2,1);
        Assert.assertNotNull(hotelVO);
        Assert.assertEquals(false, hotelVO.isOrdered());

        try{
            hotelService.findDetails(10,1);
        }catch (Exception e){
            cnt++;
            Assert.assertEquals("没有找到对应的酒店信息",e.getMessage());
        }
        Assert.assertEquals(1,cnt);
    }

    @Test
    @Transactional
    @Rollback
    public void AllHotel() throws ServiceException{
        List<HotelVO> hotelVOS = hotelService.findAllHotels(null);
        for(HotelVO hotelVO : hotelVOS){
            Assert.assertEquals(false, hotelVO.isOrdered());
        }
    }
}