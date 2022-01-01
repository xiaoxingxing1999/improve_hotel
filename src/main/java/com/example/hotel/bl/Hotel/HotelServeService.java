package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.hotelserve;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface HotelServeService {
    /**
     * 新建酒店服务
     * @param hotelServe
     * @return
     * @throws ServiceException
     */
    boolean addService(hotelserve hotelServe) throws ServiceException;

    /**
     * 根据名称查找酒店服务
     * @param name
     * @return
     * @throws ServiceException
     */
    hotelserve findByName(String name) throws ServiceException;

    /**
     * 查找所有的酒店服务
     * @return
     * @throws ServiceException
     */
    List<hotelserve> findAllServes() throws ServiceException;
}
