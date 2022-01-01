package com.example.hotel.bl.Role;

import com.example.hotel.Form.SiteMarket.CreditForm;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.SiteSituationVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface SiteMarketService {
    /**
     * 新建商圈
     * @param token
     * @param name
     * @return
     * @throws ServiceException
     */
    boolean addBizRegion(String token, String name) throws ServiceException;

    /**
     * 更新信用值
     * @param creditForm
     * @return
     * @throws ServiceException
     */
    boolean updateCredit(CreditForm creditForm) throws ServiceException;

    /**
     * 获取所有的充值账单
     * @param token
     * @return
     * @throws ServiceException
     */
    List<bill> getAllBills(String token) throws ServiceException;

    /**
     * 获取酒店的经营情况
     * @param token
     * @return
     * @throws ServiceException
     */
    List<SiteSituationVO> situation(String token) throws ServiceException;

    /**
     * 获取所有的酒店优惠券
     * @param token
     * @return
     * @throws ServiceException
     */
    List<coupon> getAllCoupons(String token) throws ServiceException;
}
