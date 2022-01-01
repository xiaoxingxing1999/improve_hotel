package com.example.hotel.util;

import com.example.hotel.bl.Hotel.HotelRoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/28
 * @Version 1.0
 **/

@Component
public class DayHelper {
    @Autowired
    RoomMapper roomMapper;
    private static final int MAX_DAY = 91;


    /**
     * 将房间更新为91天的房间大小
     * @param total
     * @return
     */
    public static String init(Integer total){
        String[] temp = new String[MAX_DAY];
        Arrays.fill(temp,String.valueOf(total));
        return ArrayHelper.ArrayToString(temp);
    }

    /**
     * 处理房间预订，计算之后的房间数量
     * @param curNum 当前房间数量
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param roomNum 正数表示撤销订单
     * @return
     */
    public static String calculate(String curNum, LocalDateTime startTime,
                                   LocalDateTime endTime, Integer roomNum) throws ServiceException {
        int startIndex = TimeHelper.getDayIndex(startTime);
        int endIndex = TimeHelper.getDayIndex(endTime);
        ArrayList<String> cur = ArrayHelper.StringToArray(curNum);
        if(startIndex < 0){
            startIndex = 0;
        }
        if(endIndex < 0){
            endIndex = 0;
        }
        for(int i = startIndex; i < endIndex && i < cur.size(); i ++){
            int temp = Integer.valueOf(cur.get(i));
            if(temp < roomNum){
                throw new ServiceException("房间数量不足");
            }
            cur.set(i,String.valueOf(temp - roomNum));
        }
        return ArrayHelper.ArrayToString(cur);
    }

    /**
     * 检查对应时间段允许预定的最大房间数量
     * @param curNum
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer isValid(String curNum, LocalDateTime startTime, LocalDateTime endTime) {
        int startIndex = TimeHelper.getDayIndex(startTime);
        int endIndex = TimeHelper.getDayIndex(endTime);
        ArrayList<String> cur = ArrayHelper.StringToArray(curNum);
        int minNum = Integer.valueOf(cur.get(startIndex));
        for(int i = startIndex + 1; i < endIndex && i < cur.size(); i ++){
            int temp = Integer.valueOf(cur.get(i));
            if(temp < minNum){
                minNum = temp;
            }
        }
        return Integer.valueOf(minNum);
    }

    /**
     * 每天晚上12点更新房间信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateRooms(){
        System.out.print(LocalDateTime.now());
        System.out.println(" :新的一天");
        List<hotelroom> hotelRooms = roomMapper.findAll();
        for(hotelroom HotelRoom: hotelRooms){
            String curNum = HotelRoom.getCurNum();
            ArrayList<String> numbers = ArrayHelper.StringToArray(curNum);
            //向后一天
            numbers.remove(0);
            numbers.add(String.valueOf(HotelRoom.getTotal()));
            curNum = ArrayHelper.ArrayToString(numbers);
            roomMapper.updateRoomInfo(HotelRoom.getHotelId(), HotelRoom.getRoomType(),curNum);
        }
    }

    public static void main(String[] args) throws ServiceException{
        String curNum = "10|1|2|5|7";
        LocalDateTime start = LocalDateTime.of(2020,5,28,0,0);
        LocalDateTime end = LocalDateTime.of(2020,5,29,0,0);
        System.out.println(calculate(curNum,start,end,0));
        System.out.println(isValid(curNum,start,end));
    }
}
