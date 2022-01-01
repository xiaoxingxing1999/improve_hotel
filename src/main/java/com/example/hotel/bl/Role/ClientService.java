package com.example.hotel.bl.Role;

import com.example.hotel.Form.Client.*;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface ClientService {
    /**
     * 客户新建订单
     * @param orderForm
     * @return
     * @throws ServiceException
     */
    boolean addOrder(OrderForm orderForm) throws ServiceException;

    /**
     * 客户编辑订单
     * @param orderEditForm
     * @return
     * @throws ServiceException
     */
    boolean editOrder(OrderEditForm orderEditForm) throws ServiceException;

    /**
     * 客户取消订单
     * @param orderId
     * @param token
     * @return
     * @throws ServiceException
     */
    boolean annualOrder(Integer orderId, String token) throws ServiceException;

    /**
     * 客户更新VIP信息
     * @param clientVIPForm
     * @return
     * @throws ServiceException
     */
    boolean updateVIP(ClientVIPForm clientVIPForm) throws ServiceException;

    /**
     * 客户注册
     * @param clientForm
     * @return
     * @throws ServiceException
     */
    boolean addClient(ClientForm clientForm) throws ServiceException;

    /**
     * 客户评价订单
     * @param commentForm
     * @return
     * @throws ServiceException
     */
    boolean comment(CommentForm commentForm) throws ServiceException;

    /**
     * 获取用户的所有账单
     * @param token
     * @return
     * @throws ServiceException
     */
    List<bill> getMyBills(String token) throws ServiceException;

    /**
     * 获取订单适用的的优惠券
     * @param token
     * @param hotelorder
     * @return
     * @throws ServiceException
     */
    List<coupon> matchCoupons(String token, hotelorder hotelorder) throws ServiceException;

    /**
     * 获取自己的订单
     * @param token
     * @return
     * @throws ServiceException
     */
    List<hotelorder> getMyOrders(String token) throws ServiceException;
}
