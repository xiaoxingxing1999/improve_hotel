package com.example.hotel.bl.Hotel;

import com.example.hotel.po.Hotel.bizregion;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface BizRegionService {

    /**
     * 酒店工作人员或网站营销人员添加商圈
     * @param bizRegion
     * @return
     * @throws ServiceException
     */
    boolean addBizRegion(bizregion bizRegion) throws ServiceException;

    /**
     * 根据名称查找商圈
     * @param name
     * @return
     * @throws ServiceException
     */
    bizregion findByName(String name) throws ServiceException;

    /**
     * 拉取所有商圈
     * @return
     * @throws ServiceException
     */
    List<bizregion> findAllBizRegions() throws ServiceException;
}
