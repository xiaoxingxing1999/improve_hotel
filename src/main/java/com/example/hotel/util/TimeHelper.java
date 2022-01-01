package com.example.hotel.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

@Component
public class TimeHelper {
    /**
     * 根据毫秒级时间戳获取LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDateTime(long timestamp){
        LocalDateTime time =LocalDateTime.ofEpochSecond(timestamp/1000,0, ZoneOffset.ofHours(8));
        return time;
    }

    /**
     * 获取默认时间戳2000-01-01的LocalDateTime
     * @return
     */
    public static LocalDateTime getDefaultLocalDateTime(){
        LocalDateTime time = LocalDateTime.ofEpochSecond(946656000/1000,0,ZoneOffset.ofHours(8));
        return time;
    }

    /**
     * 获取毫秒时间戳
     * @param localDateTime
     * @return
     */
    public static long getTimeStamp(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 将LocalDateTime格式化为2020-01-01的格式
     * @param localDateTime
     * @return
     */
    public static String dateToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateTime = localDateTime.format(formatter);
        return dateTime;
    }

    /**
     * 获取以今天为开始的序列index
     * @param time
     * @return
     */
    public static Integer getDayIndex(LocalDateTime time){
        String[] date = TimeHelper.dateToString(LocalDateTime.now()).split("-");
        long today = getTimeStamp(LocalDateTime.of(Integer.valueOf(date[0])
                ,Integer.valueOf(date[1]), Integer.valueOf(date[2]),0,0));
        long timeStamp = getTimeStamp(time);
        return (int) (timeStamp - today) / (24 * 60 * 60 * 1000);
    }
}
