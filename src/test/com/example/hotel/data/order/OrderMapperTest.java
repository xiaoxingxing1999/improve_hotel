package com.example.hotel.data.order;

import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.enums.OrderState;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.TimeHelper;
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
public class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        hotelorder HotelOrder = new hotelorder(-1,1,1,"七天酒店"
                ,LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(2)
                ,null,null,null,null
                ,null,null,"标准间",1,2
                ,false,LocalDateTime.now(),200.0,"客户一","18851083188"
                , OrderState.WAIT);
        orderMapper.save(HotelOrder);
    }

    @Test
    @Transactional
    @Rollback
    public void findByUser() {
        Assert.assertNotNull(orderMapper.findByUser(1));
    }

    @Test
    @Transactional
    @Rollback
    public void findByHotel() {
        Assert.assertNotNull(orderMapper.findByHotel(1));
    }


    @Test
    @Transactional
    @Rollback
    public void findAllOrders() {
        Assert.assertNotNull(orderMapper.findAllOrders());
    }

    @Test
    @Transactional
    @Rollback
    public void updateOrder() {
        orderMapper.updateOrder(LocalDateTime.now(),LocalDateTime.now().plusDays(1),true,10,1);
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertEquals(10, (int)HotelOrder.getPeopleNum());
    }

    @Test
    @Transactional
    @Rollback
    public void updateOrderState() {
        orderMapper.updateOrderState(1, OrderState.PASS.ordinal());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertEquals(OrderState.PASS, HotelOrder.getOrderState());
    }

    @Test
    @Transactional
    @Rollback
    public void updatePassDate() {
        orderMapper.updatePassDate(1,LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealPassDate());
    }

    @Test
    @Transactional
    @Rollback
    public void updateRefuseDate() {
        orderMapper.updateRefuseDate(1,LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealRefuseDate());
    }

    @Test
    @Transactional
    @Rollback
    public void updateCancelDate() {
        orderMapper.updateCancelDate(1,LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealCancelDate());
    }

    @Test
    @Transactional
    @Rollback
    public void updateCheckInDate() {
        orderMapper.updateCheckInDate(1,LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealCheckInDate());
    }

    @Test
    @Transactional
    @Rollback
    public void updateCheckOutDate() {
        orderMapper.updateCheckOutDate(1,LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealCheckOutDate());
    }

    @Test
    @Transactional
    @Rollback
    public void updateErrorDate() {
        orderMapper.updateErrorDate(1, LocalDateTime.now());
        hotelorder HotelOrder = orderMapper.findById(1).orElse(null);
        Assert.assertNotNull(HotelOrder);
        Assert.assertNotNull(HotelOrder.getRealErrorDate());
    }
}