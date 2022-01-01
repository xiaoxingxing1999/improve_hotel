package com.example.hotel.bl;

import com.example.hotel.po.Bill.bill;
import com.example.hotel.util.Response.ServiceException;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface BillService {
    /**
     * 新建账单
     * @param bill
     * @return
     * @throws ServiceException
     */
    boolean addBill(bill bill) throws ServiceException;

    /**
     * 根据ID查找账单
     * @param billId
     * @return
     * @throws ServiceException
     */
    bill findById(Integer billId) throws ServiceException;

    /**
     * 根据用户查找账单
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<bill> findByUser(Integer userId) throws ServiceException;

    /**
     * 网站营销人员查找充值账单
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<bill> findAllBills(Integer userId) throws ServiceException;
}
