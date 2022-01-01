package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.RoomTypeService;
import com.example.hotel.data.hotel.RoomTypeMapper;
import com.example.hotel.po.Hotel.roomtype;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    RoomTypeMapper roomTypeMapper;

    @Override
    public boolean addRoomType(roomtype roomType)
            throws ServiceException{
        roomtype temp = findByName(roomType.getName());
        if(temp != null){
            throw new ServiceException("已经有重名的房间类型");
        }
        try{
            roomTypeMapper.save(roomType);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加房间类型数据库失败");
        }
        return true;
    }

    @Override
    public roomtype findByName(String name)
            throws ServiceException{
        roomtype roomType = null;
        try{
            roomType = roomTypeMapper.selectByName(name);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据名字查找房间类型数据库失败");
        }
        return roomType;
    }

    @Override
    public List<roomtype> findAllRoomTypes()
            throws ServiceException{
        List<roomtype> roomtypes = null;
        try{
            roomtypes = roomTypeMapper.selectAllNames();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有的房间类型数据库失败");
        }
        return roomtypes;
    }
}
