package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.BizRegionService;
import com.example.hotel.data.hotel.BizRegionMapper;
import com.example.hotel.po.Hotel.bizregion;
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
public class BizRegionServiceImpl implements BizRegionService {
    @Autowired
    BizRegionMapper bizRegionMapper;

    @Override
    public boolean addBizRegion(bizregion bizRegion) throws ServiceException {
        if(findByName(bizRegion.getName())!= null){
            throw new ServiceException("已经有重名的商圈");
        }
        try{
            bizRegionMapper.save(bizRegion);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加商圈数据库错误");
        }
        return true;
    }

    @Override
    public bizregion findByName(String name) throws ServiceException {
        bizregion bizRegion = null;
        try{
            bizRegion = bizRegionMapper.findByName(name);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找商圈数据库失败");
        }
        return bizRegion;
    }

    @Override
    public List<bizregion> findAllBizRegions() throws ServiceException {
        List<bizregion> bizregions = null;
        try{
            bizregions = bizRegionMapper.findAllNames();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找商圈列表数据库失败");
        }
        return bizregions;
    }
}
