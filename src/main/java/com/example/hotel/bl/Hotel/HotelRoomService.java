package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.util.Response.ServiceException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface HotelRoomService {

    /**
     * 新建酒店房间
     * @param hotelRoom
     * @return
     * @throws ServiceException
     */
    boolean addHotelRoom(hotelroom hotelRoom) throws ServiceException;

    /**
     * 更新酒店房间剩余数量
     * @param hotelRoomId
     * @param roomType
     * @param AfterNum
     * @return
     * @throws ServiceException
     */
    boolean updateNumber(Integer hotelRoomId, String roomType, String AfterNum) throws ServiceException;

    /**
     * 删除酒店房间
     * @param roomId
     * @return
     * @throws ServiceException
     */
    boolean delHotelRoom(Integer roomId) throws ServiceException;

    /**
     * 查找对应时间段对应房间类型是否有剩余房间
     * @param hotelId
     * @param roomType
     * @param start
     * @param end
     * @return
     * @throws ServiceException
     */
    Integer findValidRooms(Integer hotelId, String roomType, LocalDateTime start, LocalDateTime end) throws ServiceException;

    /**
     * 根据ID查找酒店房间
     * @param hotelRoomId
     * @return
     * @throws ServiceException
     */
    hotelroom findById(Integer hotelRoomId) throws ServiceException;

    /**
     * 根据酒店和房间类型查找房间信息
     * @param hotelId
     * @param roomType
     * @return
     * @throws ServiceException
     */
    hotelroom findByHotelAndType(Integer hotelId, String roomType) throws ServiceException;

    /**
     * 查找酒店所有的房间
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<hotelroom> findByHotelId(Integer hotelId) throws ServiceException;
}
