package com.example.hotel.blImpl;

import com.example.hotel.bl.BillService;
import com.example.hotel.data.bill.BillMapper;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Override
    public boolean addBill(bill bill)
            throws ServiceException{
        try{
            billMapper.save(bill);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加账单数据库失败");
        }
        return true;
    }

    @Override
    public bill findById(Integer billId)
            throws ServiceException{
        bill bill = null;
        try{
            bill = billMapper.findById(billId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据主键查找账单数据库失败");
        }
        return bill;
    }

    @Override
    public List<bill> findByUser(Integer userId)
            throws ServiceException{
        List<bill> bills = null;
        try{
            bills = billMapper.findByUid(userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据用户主键查找数据库失败");
        }
        return bills;
    }

    @Override
    public List<bill> findAllBills(Integer userId)
            throws ServiceException{
        List<bill> bills = null;
        try{
            bills = billMapper.findAll(userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有账单记录数据库失败");
        }
        return bills;
    }
}
