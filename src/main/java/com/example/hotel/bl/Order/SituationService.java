package com.example.hotel.bl.Order;

import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.HotelSituationVO;
import com.example.hotel.vo.SiteSituationVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/18
 * @Version 1.0
 **/

public interface SituationService {

    /**
     * 查找一个酒店的所有每日经营情况
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    List<HotelSituationVO> getHotelSituation(Integer hotelId) throws ServiceException;

    /**
     * 查找网站每日的经营情况
     * @return
     * @throws ServiceException
     */
    List<SiteSituationVO> getSiteSituation() throws ServiceException;
}
