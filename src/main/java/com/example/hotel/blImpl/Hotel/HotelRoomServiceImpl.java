package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.HotelRoomService;
import com.example.hotel.bl.Hotel.RoomTypeService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.po.Hotel.roomtype;
import com.example.hotel.util.DayHelper;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class HotelRoomServiceImpl implements HotelRoomService {
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    RoomTypeService roomTypeService;

    @Override
    public boolean addHotelRoom(hotelroom hotelRoom) throws ServiceException {
        roomtype roomType = roomTypeService.findByName(hotelRoom.getRoomType());
        if(roomType == null){
            throw new ServiceException("没有这个房间类型");
        }
        hotelroom temp = roomMapper.findByHotelAndType(hotelRoom.getHotelId(),hotelRoom.getRoomType());
        if(temp != null){
            throw new ServiceException("已经有对应类型的房间，无法新建");
        }
        if(hotelRoom.getTotal() <= 0){
            throw new ServiceException("酒店房间数量应该大于零");
        }
        if(hotelRoom.getPrice() <= 0){
            throw new ServiceException("酒店房间价格应该大于零");
        }
        try{
            roomMapper.save(hotelRoom);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加酒店房间信息数据库失败");
        }
        return true;
    }

    @Override
    public boolean delHotelRoom(Integer roomId)
            throws ServiceException {
        try{
            roomMapper.delRoom(roomId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("删除酒店房间信息数据库失败");
        }
        return true;
    }

    @Override
    public boolean updateNumber(Integer hotelId, String roomType, String AfterNum)
            throws ServiceException {
        try{
            roomMapper.updateRoomInfo(hotelId, roomType, AfterNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新酒店剩余房间数量数据库失败");
        }
        return true;
    }

    @Override
    public hotelroom findById(Integer hotelRoomId)
            throws ServiceException{
        hotelroom hotelRoom = null;
        try{
            hotelRoom = roomMapper.findById(hotelRoomId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店房间信息数据库失败");
        }
        if(hotelRoom == null){
            throw new ServiceException("没有找到房间信息");
        }
        return hotelRoom;
    }

    @Override
    public List<hotelroom> findByHotelId(Integer hotelId)
            throws ServiceException{
        List<hotelroom> hotelrooms = null;
        try{
            hotelrooms = roomMapper.findRoomsByHotelId(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店房间信息数据库失败");
        }
        return hotelrooms;
    }

    @Override
    public hotelroom findByHotelAndType(Integer hotelId, String roomType) throws ServiceException {
        hotelroom hotelRoom = null;
        try{
            hotelRoom = roomMapper.findByHotelAndType(hotelId, roomType);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店特定的房间数据库错误");
        }
        if(hotelRoom == null){
            throw new ServiceException("没有找到对应的房间信息");
        }
        return hotelRoom;
    }

    @Override
    public Integer findValidRooms(Integer hotelId, String roomType, LocalDateTime start, LocalDateTime end) throws ServiceException {
        hotelroom hotelRoom = findByHotelAndType(hotelId, roomType);
        String CurNum = hotelRoom.getCurNum();
        return DayHelper.isValid(CurNum, start, end);
    }
}
