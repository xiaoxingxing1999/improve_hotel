package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.HotelEditService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.data.hotel.HotelEditMapper;
import com.example.hotel.enums.HotelEditStatus;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class HotelEditServiceImpl implements HotelEditService {
    @Autowired
    HotelEditMapper hotelEditMapper;
    @Autowired
    HotelService hotelService;

    @Override
    public boolean addHotelEdit(hoteledit hotelEdit) throws ServiceException {
        List<hoteledit> hoteledits = findByHotel(hotelEdit.getHotelId());
        for(hoteledit HotelEdit: hoteledits){
            if(HotelEdit.getStatus().equals(HotelEditStatus.WAIT)){
                throw new ServiceException("有还没被处理的酒店修改申请，请不要重复提交");
            }
        }
        try{
            hotelEditMapper.save(hotelEdit);
        }catch (Exception e){
            e.printStackTrace();
           throw new ServiceException("添加酒店修改申请数据库失败");
        }
        return true;
    }

    @Override
    public boolean checkHotelEdit(HotelEditStatus status, String replyContent, Integer id) throws ServiceException {
        hoteledit hotelEdit = findById(id);
        if(!hotelEdit.getStatus().equals(HotelEditStatus.WAIT)){
            throw new ServiceException("该酒店修改申请已经处理完成");
        }
        try{
            hotelEditMapper.reply(status.ordinal(), replyContent, LocalDateTime.now(), id);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("回复酒店修改申请数据库失败");
        }
        if(status.equals(HotelEditStatus.PASS)){
            //修改酒店信息
            hotelService.editHotel(hotelEdit);
        }
        return true;
    }

    @Override
    public hoteledit findById(Integer hotelEditId) throws ServiceException {
        hoteledit hotelEdit = null;
        try{
            hotelEdit = hotelEditMapper.findById(hotelEditId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店修改申请数据库失败");
        }
        if(hotelEdit == null){
            throw new ServiceException("没有找到酒店信息修改申请");
        }
        return hotelEdit;
    }

    @Override
    public List<hoteledit> findByHotel(Integer hotelId) throws ServiceException {
        List<hoteledit> hoteledits = null;
        try{
            hoteledits = hotelEditMapper.findByHotel(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据管理人员查找酒店信息修改申请数据库失败");
        }
        return hoteledits;
    }

    @Override
    public List<hoteledit> findAllHotelEdits() throws ServiceException {
        List<hoteledit> hoteledits = null;
        try{
            hoteledits = hotelEditMapper.findAllEdits();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有的酒店修改申请失败");
        }
        return hoteledits;
    }
}
