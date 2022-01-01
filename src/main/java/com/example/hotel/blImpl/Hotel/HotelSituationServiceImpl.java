package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.data.hotel.HotelSituationMapper;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.po.Hotel.hotelsituation;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/19
 * @Version 1.0
 **/

@Service
public class HotelSituationServiceImpl implements HotelSituationService {
    @Autowired
    HotelSituationMapper hotelSituationMapper;
    @Autowired
    HotelService hotelService;

    @Override
    public boolean createLeastSituation(Integer hotelId) throws ServiceException {
        HotelVO hotelVO = hotelService.findDetails(hotelId ,null);
        //检查调用情况
        String today = TimeHelper.dateToString(LocalDateTime.now());
        hotelsituation HotelSituation = getLatestSituation(hotelId);
        if(HotelSituation != null && HotelSituation.getToday().equals(today)){
            throw new ServiceException("酒店今天的记录已经创建请不要重复创建");
        }

        List<hotelroom> HotelRooms = hotelVO.getRoom();
        Integer roomTotalNumber = 0;
        for(hotelroom HotelRoom: HotelRooms){
            roomTotalNumber += HotelRoom.getTotal();
        }

        Integer totalClientNum = HotelSituation == null ? 0 : HotelSituation.getTotalClientNum();
        hotelsituation newDay = new hotelsituation(hotelId, today,
                roomTotalNumber, totalClientNum);

        try{
            hotelSituationMapper.save(newDay);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("新建酒店信息数据库失败");
        }
        return true;
    }

    @Override
    public List<hotelsituation> selectByHotelId(Integer hotelId) throws ServiceException{
        List<hotelsituation> hotelsituations = null;
        try{
            hotelsituations = hotelSituationMapper.selectByHotelId(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店的基本信息数据库失败");
        }
        return hotelsituations;
    }
    @Override
    public List<hotelsituation> selectAll() throws ServiceException{
        List<hotelsituation> hotelsituations = null;
        try{
            hotelsituations = hotelSituationMapper.selectAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找网站的基本信息数据库失败");
        }
        return hotelsituations;
    }
    @Override
    public boolean updateClientNum(Integer hotelId, String today, Integer clientNum) throws ServiceException{
        try{
            hotelSituationMapper.updateClientNum(hotelId, today, clientNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新ClientNum失败");
        }
        return true;
    }
    @Override
    public boolean updateVIPNum(Integer hotelId, String today, Integer VIPNum) throws ServiceException{
        try{
            hotelSituationMapper.updateVIPNum(hotelId, today, VIPNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新VIPNum失败");
        }
        return true;
    }
    @Override
    public boolean updateOrderDoneNum(Integer hotelId, String today, Integer orderDoneNum) throws ServiceException{
        try{
            hotelSituationMapper.updateOrderDoneNum(hotelId, today, orderDoneNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新OrderDoneNum失败");
        }
        return true;
    }
    @Override
    public boolean updateOrderBadNum(Integer hotelId, String today, Integer orderBadNum) throws ServiceException{
        try{
            hotelSituationMapper.updateOrderBadNum(hotelId, today, orderBadNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新OrderBadNum失败");
        }
        return true;
    }
    @Override
    public boolean updateTotalClientNum(Integer hotelId, String today, Integer totalClientNum) throws ServiceException{
        try{
            hotelSituationMapper.updateTotalClientNum(hotelId, today, totalClientNum);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新TotalClientNum失败");
        }
        return true;
    }

    /**
     * 获取对应酒店最新的经营情况
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    private hotelsituation getLatestSituation(Integer hotelId) throws ServiceException{
        hotelsituation HotelSituation = null;
        try{
            HotelSituation = hotelSituationMapper.findLatestHotel(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("拉取酒店最新信息数据失败");
        }
        return HotelSituation;
    }

}
