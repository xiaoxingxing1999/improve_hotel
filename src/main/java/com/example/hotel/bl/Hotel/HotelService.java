package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.HotelVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface HotelService {
    /**
     * 更新酒店信息
     * @param hotelEdit
     * @throws ServiceException
     */
    void editHotel(hoteledit hotelEdit) throws ServiceException;

    /**
     * 新建酒店
     * @param hotel
     * @return
     * @throws ServiceException
     */
    boolean addHotel(hotel hotel) throws ServiceException;

    /**
     * 为已有酒店添加酒店管理员
     * @param hotelId
     * @param hotelManagerId
     * @return
     * @throws ServiceException
     */
    boolean addHotelManager(Integer hotelId, Integer hotelManagerId) throws ServiceException;

    /**
     * 更新酒店评分
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    boolean updateRate(Integer hotelId) throws ServiceException;

    /**
     * 根据酒店工作人员ID查找酒店ID
     * @param managerId
     * @return
     * @throws ServiceException
     */
    Integer findByManagerId(Integer managerId) throws ServiceException;

    /**
     * 根据ID查找酒店
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    hotel findById(Integer hotelId) throws ServiceException;

    /**
     * 根据酒店工作人员ID查找酒店
     * @param managerId
     * @return
     * @throws ServiceException
     */
    hotel findHotelByManagerId(Integer managerId) throws ServiceException;

    /**
     * 根据非null条件执行联合高级搜索
     * @param timeStart
     * @param timeEnd
     * @param star
     * @param lowerPrice
     * @param highPrice
     * @param roomType
     * @param rate
     * @param name
     * @param serves
     * @return
     * @throws ServiceException
     */
    List<HotelVO> findHotelsInCondition(Long timeStart, Long timeEnd, ArrayList<String> star, Integer lowerPrice
            , Integer highPrice, ArrayList<String> roomType, Double rate, String name, ArrayList<String> serves)
            throws ServiceException;

    /**
     * 根据酒店ID和用户ID（可选，用于确定是否预订过）查找酒店详细信息
     * @param hotelId
     * @param userId
     * @return
     * @throws ServiceException
     */
    HotelVO findDetails(Integer hotelId, Integer userId) throws ServiceException;

    /**
     * 根据用户ID（可选，用于确定是否预订过）查找所有酒店信息
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<HotelVO> findAllHotels(Integer userId) throws ServiceException;

    /**
     * 查找到数据库中存储的所有的酒店ID
     * @return
     * @throws ServiceException
     */
    List<Integer> findHotelIds() throws ServiceException;

    /**
     * 查找对应日期新建的酒店数量
     * @param day
     * @return
     */
    Integer getHotelNumber(String day);
}
