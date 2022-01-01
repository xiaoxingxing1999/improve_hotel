package com.example.hotel.bl.Role;

import com.example.hotel.Form.HotelManager.CheckHotelEditForm;
import com.example.hotel.Form.HotelManager.HotelManagerForm;
import com.example.hotel.Form.Manager.HotelForm;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface ManagerService {
    /**
     * 新建酒店
     * @param hotelForm
     * @return
     * @throws ServiceException
     */
    boolean addHotel(HotelForm hotelForm) throws ServiceException;

    /**
     * 新建酒店工作人员并绑定
     * @param hotelManagerForm
     * @return
     * @throws ServiceException
     */
    boolean addHotelManager(HotelManagerForm hotelManagerForm) throws ServiceException;

    /**
     * 审核酒店信息修改申请
     * @param checkHotelEditForm
     * @return
     * @throws ServiceException
     */
    boolean reply(CheckHotelEditForm checkHotelEditForm) throws ServiceException;

    /**
     * 获取所有的酒店工作人员
     * @param token
     * @return
     * @throws ServiceException
     */
    List<user> getAllHotelManager(String token) throws ServiceException;

    /**
     * 获取所有的酒店修改信息申请
     * @param token
     * @return
     * @throws ServiceException
     */
    List<hoteledit> getAllHotelEdit(String token) throws ServiceException;
}
