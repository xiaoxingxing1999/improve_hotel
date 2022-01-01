package com.example.hotel.blImpl.Order;

import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.bl.Order.SituationService;
import com.example.hotel.bl.UserService;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.po.Hotel.hotelsituation;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.HotelSituationVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.SiteSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/18
 * @Version 1.0
 **/

@Service
public class SituationServiceImpl implements SituationService {
    //需要添加每日盈利 低优先级

    @Autowired
    OrderService orderService;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelSituationService hotelSituationService;
    @Autowired
    UserService userService;

    /**
     * 酒店查看自己的情况
     * @param hotelId
     * @return
     */
    @Override
    public List<HotelSituationVO> getHotelSituation(Integer hotelId) throws ServiceException {
        //酒店查看自己的具体情况
        List<HotelSituationVO> hotelSituationVOS = new ArrayList<>();

        List<hotelsituation> hotelSituations = hotelSituationService.selectByHotelId(hotelId);
        if (hotelSituations == null) {
            return null;
        }
        //合并
        for (hotelsituation HotelSituation : hotelSituations) {
            HotelSituationVO hotelSituationVO = new HotelSituationVO(HotelSituation);
            hotelSituationVOS.add(hotelSituationVO);
        }
        for (int i = 1; i < hotelSituationVOS.size(); i++){
            Integer clientChange = hotelSituationVOS.get(i).getClientNum() - hotelSituationVOS.get(i - 1).getClientNum();
            Integer VIPChange = hotelSituationVOS.get(i).getVIPNum() - hotelSituationVOS.get(i - 1).getVIPNum();
            Integer orderDoneChange = hotelSituationVOS.get(i).getOrderDoneNum() - hotelSituationVOS.get(i - 1).getOrderDoneNum();
            Integer orderBadChange = hotelSituationVOS.get(i).getOrderBadNum() - hotelSituationVOS.get(i - 1).getOrderBadNum();
            hotelSituationVOS.get(i).setClientChange(clientChange);
            hotelSituationVOS.get(i).setVIPChange(VIPChange);
            hotelSituationVOS.get(i).setOrderDoneChange(orderDoneChange);
            hotelSituationVOS.get(i).setOrderBadChange(orderBadChange);
        }
        return hotelSituationVOS;
    }

    /**
     * 网站查看自己的情况
     * @return
     */
    @Override
    public List<SiteSituationVO> getSiteSituation() throws ServiceException{
        //网站查看全网站的具体情况
        List<SiteSituationVO> siteSituationVOS = new ArrayList<>();

        List<hotelsituation> hotelSituations = hotelSituationService.selectAll();
        if (hotelSituations == null) {
            return null;
        }
        //初始化
        SiteSituationVO siteSituationVO = null;
        for(int i = 0 ; i < hotelSituations.size(); i ++){
            HotelSituationVO hotelSituationVO = new HotelSituationVO(hotelSituations.get(i));
            SiteSituationVO temp = new SiteSituationVO(hotelSituationVO);
            Integer hotelNum = hotelService.getHotelNumber(temp.getToday());
            temp.setHotelNum(hotelNum);
            if(i == 0){
                siteSituationVO = temp;
                continue;
            }
            if(siteSituationVO.getToday().equals(temp.getToday())){
                siteSituationVO.add(temp);
            }else{
                siteSituationVOS.add(siteSituationVO);
                siteSituationVO = temp;
            }
        }
        //添加最后的粘性末端
        siteSituationVOS.add(siteSituationVO);
        //计算差
        for (int i = 1; i < siteSituationVOS.size(); i++){
            Integer hotelChange = siteSituationVOS.get(i).getHotelNum() - siteSituationVOS.get(i - 1).getHotelNum();
            Integer clientChange = siteSituationVOS.get(i).getClientNum() - siteSituationVOS.get(i - 1).getClientNum();
            Integer VIPChange = siteSituationVOS.get(i).getVIPNum() - siteSituationVOS.get(i - 1).getVIPNum();
            Integer orderDoneChange = siteSituationVOS.get(i).getOrderDoneNum() - siteSituationVOS.get(i - 1).getOrderDoneNum();
            Integer orderBadChange = siteSituationVOS.get(i).getOrderBadNum() - siteSituationVOS.get(i - 1).getOrderBadNum();
            siteSituationVOS.get(i).setHotelChange(hotelChange);
            siteSituationVOS.get(i).setClientChange(clientChange);
            siteSituationVOS.get(i).setVIPChange(VIPChange);
            siteSituationVOS.get(i).setOrderDoneChange(orderDoneChange);
            siteSituationVOS.get(i).setOrderBadChange(orderBadChange);
        }
        return siteSituationVOS;
    }
}
