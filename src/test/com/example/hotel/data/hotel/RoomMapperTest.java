package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotelroom;
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
public class RoomMapperTest {
    @Autowired
    RoomMapper roomMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        hotelroom HotelRoom = new hotelroom(-1,10.0,"10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10"
                ,10,1,"save RoomType",2,"","");
        roomMapper.save(HotelRoom);
    }

    @Test
    @Transactional
    @Rollback
    public void getRoomCurNum() {
        Assert.assertNotNull(roomMapper.getRoomCurNum(1,"大床房"));
    }

    @Test
    @Transactional
    @Rollback
    public void findByHotelAndType() {
        Assert.assertNotNull(roomMapper.findByHotelAndType(1,"大床房"));
    }

    @Test
    @Transactional
    @Rollback
    public void findRoomsByHotelId() {
        Assert.assertNotNull(roomMapper.findRoomsByHotelId(1));
    }

    @Test
    @Transactional
    @Rollback
    public void updateRoomInfo() {
        roomMapper.updateRoomInfo(1,"大床房","8|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10");
        hotelroom HotelRoom = roomMapper.findByHotelAndType(1,"大床房");
        Assert.assertNotNull("8|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10|10", HotelRoom.getCurNum());
    }

    @Test
    @Transactional
    @Rollback
    public void delRoom() {
        roomMapper.delRoom(1);
        Assert.assertNull(roomMapper.findById(1).orElse(null));
    }
}