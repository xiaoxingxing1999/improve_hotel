package com.example.hotel.bl.Order;

import com.example.hotel.Form.Client.OrderEditForm;
import com.example.hotel.enums.OrderState;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface OrderService {
    /**
     * 新建订单
     * @param hotelorder
     * @return
     * @throws ServiceException
     */
    boolean addOrder(hotelorder hotelorder) throws ServiceException;

    /**
     * 更新订单状态
     * @param orderId
     * @param orderState
     * @return
     * @throws ServiceException
     */
    boolean updateOrderState(Integer orderId, OrderState orderState) throws ServiceException;

    /**
     * 编辑订单信息
     * @param orderEditForm
     * @return
     * @throws ServiceException
     */
    boolean editOrder(OrderEditForm orderEditForm) throws ServiceException;

    /**
     * 取消订单信息
     * @param orderId
     * @param userId
     * @return
     * @throws ServiceException
     */
    boolean annualOrder(Integer orderId, Integer userId) throws ServiceException;

    /**
     * 检验用户是否订过该酒店
     * @param userId
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    boolean isOrdered(Integer userId,Integer hotelId) throws ServiceException;

    /**
     * 根据ID查找订单
     * @param orderId
     * @return
     * @throws ServiceException
     */
    hotelorder findById(Integer orderId) throws ServiceException;

    /**
     * 根据用户查找订单
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<hotelorder> findByUser(Integer userId) throws ServiceException;

    /**
     * 根据酒店查找订单
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<hotelorder> findByHotel(Integer hotelId) throws ServiceException;

    /**
     * 查找对应酒店对应房间的订单
     * @param hotelId
     * @param roomType
     * @return
     * @throws ServiceException
     */
    List<hotelorder> findByRoomValid(Integer hotelId, String roomType) throws ServiceException;

    /**
     * 查找所有的订单信息
     * @return
     * @throws ServiceException
     */
    List<hotelorder> findAllOrders() throws ServiceException;
}
