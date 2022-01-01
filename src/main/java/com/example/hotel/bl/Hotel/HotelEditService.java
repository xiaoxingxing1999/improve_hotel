package com.example.hotel.bl.Hotel;

import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface HotelEditService {
    /**
     * 新建酒店信息修改申请
     * @param hotelEdit
     * @return
     * @throws ServiceException
     */
    boolean addHotelEdit(hoteledit hotelEdit) throws ServiceException;

    /**
     * 审核酒店信息修改申请
     * @param status
     * @param replyContent
     * @param id
     * @return
     * @throws ServiceException
     */
    boolean checkHotelEdit(HotelEditStatus status, String replyContent, Integer id) throws ServiceException;

    /**
     * 根据ID查找酒店信息修改申请
     * @param hotelEditId
     * @return
     * @throws ServiceException
     */
    hoteledit findById(Integer hotelEditId) throws ServiceException;

    /**
     * 根据酒店查找酒店信息修改申请
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<hoteledit> findByHotel(Integer hotelId) throws ServiceException;

    /**
     * 查找所有的酒店信息申请
     * @return
     * @throws ServiceException
     */
    List<hoteledit> findAllHotelEdits() throws ServiceException;
}
