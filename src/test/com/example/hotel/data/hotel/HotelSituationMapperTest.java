package com.example.hotel.data.hotel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelSituationMapperTest {
    @Autowired
    HotelSituationMapper hotelSituationMapper;

    @Test
    @Transactional
    @Rollback
    public void findLatestHotel() {
        Assert.assertNotNull(hotelSituationMapper.findLatestHotel(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByHotelId() {
        Assert.assertNotNull(hotelSituationMapper.selectByHotelId(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectAll() {
        Assert.assertNotNull(hotelSituationMapper.selectAll());
    }

    @Test
    @Transactional
    @Rollback
    public void updateClientNum() {
        hotelSituationMapper.updateClientNum(1,"2020-06-19",1);
    }

    @Test
    @Transactional
    @Rollback
    public void updateVIPNum() {
        hotelSituationMapper.updateVIPNum(1,"2020-06-19",1);
    }

    @Test
    @Transactional
    @Rollback
    public void updateOrderDoneNum() {
        hotelSituationMapper.updateOrderDoneNum(1,"2020-06-19",1);
    }

    @Test
    @Transactional
    @Rollback
    public void updateOrderBadNum() {
        hotelSituationMapper.updateOrderBadNum(1,"2020-06-19",1);
    }

    @Test
    @Transactional
    @Rollback
    public void updateTotalClientNum() {
        hotelSituationMapper.updateTotalClientNum(1,"2020-06-19",1);
    }
}