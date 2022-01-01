package com.example.hotel.util;

import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.po.Hotel.hotelroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/06/19
 * @Version 1.0
 **/

@Component
public class HotelSituationHelper {
    @Autowired
    HotelSituationService hotelSituationService;
    @Autowired
    HotelService hotelService;

    /**
     * 每天晚上12点新建酒店经营情况
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateHotelSituation(){
        System.out.print(LocalDateTime.now());
        System.out.println(" :新的一天开始新建酒店情况");
        List<Integer> hotelIds = null;
        try{
            hotelIds = hotelService.findHotelIds();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(hotelIds == null){
            System.out.println("系统没有酒店");
            return ;
        }
        for(Integer hotelId: hotelIds){
            try{
                hotelSituationService.createLeastSituation(hotelId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
