package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.HotelServeService;
import com.example.hotel.data.hotel.HotelServeMapper;
import com.example.hotel.po.Hotel.hotelserve;
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
public class HotelServeServiceImpl implements HotelServeService {
    @Autowired
    HotelServeMapper hotelServeMapper;

    @Override
    public boolean addService(hotelserve hotelServe)
            throws ServiceException{
        hotelserve temp = findByName(hotelServe.getName());
        if(temp != null){
            throw new ServiceException("已经有重名的酒店服务");
        }
        try{
            hotelServeMapper.save(hotelServe);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("新建酒店服务数据库失败");
        }
        return true;
    }

    @Override
    public hotelserve findByName(String name)
            throws ServiceException{
        hotelserve hotelServe = null;
        try{
            hotelServe = hotelServeMapper.selectByName(name);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据名字查找酒店服务数据库失败");
        }
        return hotelServe;
    }

    @Override
    public List<hotelserve> findAllServes()
            throws ServiceException{
        List<hotelserve> names = null;
        try{
            names = hotelServeMapper.selectAllNames();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有的酒店服务信息数据库失败");
        }
        return names;
    }
}
