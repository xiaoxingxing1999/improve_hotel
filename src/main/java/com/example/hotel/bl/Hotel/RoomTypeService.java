package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.roomtype;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface RoomTypeService {
    /**
     * 新建房间类型
     * @param roomType
     * @return
     * @throws ServiceException
     */
    boolean addRoomType(roomtype roomType) throws ServiceException;

    /**
     * 根据名称查找房间类型
     * @param name
     * @return
     * @throws ServiceException
     */
    roomtype findByName(String name) throws ServiceException;

    /**
     * 查找所有的房间类型
     * @return
     * @throws ServiceException
     */
    List<roomtype> findAllRoomTypes() throws ServiceException;
}
