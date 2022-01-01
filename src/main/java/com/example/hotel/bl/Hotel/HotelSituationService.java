package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.hotelsituation;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/19
 * @Version 1.0
 **/

public interface HotelSituationService {
    /**
     * 创建最新一天的酒店经营情况
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    boolean createLeastSituation(Integer hotelId) throws ServiceException;

    /**
     * 根据酒店ID查找到所有的酒店经营情况
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<hotelsituation> selectByHotelId(Integer hotelId) throws ServiceException;

    /**
     * 查找所有的酒店经营情况
     * @return
     * @throws ServiceException
     */
    List<hotelsituation> selectAll() throws ServiceException;

    /**
     * 更新当天的新入住人数
     * @param hotelId
     * @param today
     * @param clientNum
     * @return
     * @throws ServiceException
     */
    boolean updateClientNum(Integer hotelId, String today, Integer clientNum) throws ServiceException;

    /**
     * 更新当天的新入住VIP总数
     * @param hotelId
     * @param today
     * @param VIPNum
     * @return
     * @throws ServiceException
     */
    boolean updateVIPNum(Integer hotelId, String today, Integer VIPNum) throws ServiceException;

    /**
     * 更新当天完成的订单数量
     * @param hotelId
     * @param today
     * @param orderDoneNum
     * @return
     * @throws ServiceException
     */
    boolean updateOrderDoneNum(Integer hotelId, String today, Integer orderDoneNum) throws ServiceException;

    /**
     * 更新当天撤销/异常订单的总数
     * @param hotelId
     * @param today
     * @param orderBadNum
     * @return
     * @throws ServiceException
     */
    boolean updateOrderBadNum(Integer hotelId, String today, Integer orderBadNum) throws ServiceException;

    /**
     * 更新每天的在住人数
     * @param hotelId
     * @param today
     * @param totalClientNum
     * @return
     * @throws ServiceException
     */
    boolean updateTotalClientNum(Integer hotelId, String today, Integer totalClientNum) throws ServiceException;
}
