package com.example.hotel.bl.Role;

import com.example.hotel.Form.HotelManager.*;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.po.Hotel.hotelserve;
import com.example.hotel.po.Hotel.roomtype;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.HotelSituationVO;
import com.example.hotel.vo.HotelVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface HotelManagerService {
    /**
     * 新建房间
     * @param roomForm
     * @return
     * @throws ServiceException
     */
    boolean addRoom(RoomForm roomForm) throws ServiceException;

    /**
     * 编辑酒店信息
     * @param hotelEditForm
     * @return
     * @throws ServiceException
     */
    boolean editHotel(HotelEditForm hotelEditForm) throws ServiceException;

    /**
     * 确认订单信息
     * @param orderCheckForm
     * @return
     * @throws ServiceException
     */
    boolean confirm(OrderCheckForm orderCheckForm) throws ServiceException;

    /**
     * 删除没有订单在进行的房间
     * @param token
     * @param roomId
     * @return
     * @throws ServiceException
     */
    boolean delRoom(String token, Integer roomId) throws ServiceException;

    /**
     * 新建酒店服务
     * @param serveForm
     * @return
     * @throws ServiceException
     */
    boolean addServe(ServeForm serveForm) throws ServiceException;

    /**
     * 新建房间类型
     * @param roomTypeForm
     * @return
     * @throws ServiceException
     */
    boolean addRoomType(RoomTypeForm roomTypeForm) throws ServiceException;

    /**
     * 获取房间信息
     * @param token
     * @return
     * @throws ServiceException
     */
    HotelVO getHotelInfo(String token) throws ServiceException;

    /**
     * 获取酒店工作人员新建的订单
     * @param token
     * @return
     * @throws ServiceException
     */
    List<coupon> getMyCoupon(String token) throws ServiceException;

    /**
     * 查找本酒店的所有酒店订单
     * @param token
     * @return
     * @throws ServiceException
     */
    List<hotelorder> findHotelOrders(String token) throws ServiceException;

    /**
     * 查找酒店工作人员提交的全部酒店信息修改申请
     * @param token
     * @return
     * @throws ServiceException
     */
    List<hoteledit> findByManager(String token) throws ServiceException;

    /**
     * 获取所有的房间类型
     * @param token
     * @return
     * @throws ServiceException
     */
    List<roomtype> getAllRoomType(String token) throws ServiceException;

    /**
     * 获取本酒店的经营情况
     * @param token
     * @return
     * @throws ServiceException
     */
    List<HotelSituationVO> hotelSituation(String token) throws ServiceException;
}
