package com.example.hotel.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Component
public class ArrayHelper {
    /**
     * 将ArrayList的字符串转换为|分隔的字符串
     * @param input
     * @return
     */
    public static String ArrayToString(ArrayList<String> input){
        if(input.size() == 0){
            return "";
        }
        StringBuffer str = new StringBuffer(input.get(0));
        for(int i = 1 ; i < input.size() ; i ++){
            str.append("|" + input.get(i));
        }
        return str.toString();
    }

    /**
     * 将String的数组转换为|分隔的字符串
     * @param input
     * @return
     */
    public static String ArrayToString(String[] input){
        if(input.length == 0){
            return "";
        }
        StringBuffer str = new StringBuffer(input[0]);
        for(int i = 1 ; i < input.length ; i ++){
            str.append("|" + input[i]);
        }
        return str.toString();
    }

    /**
     * 将String按照|进行切割
     * @param input
     * @return
     */
    public static ArrayList<String> StringToArray(String input){
        String[] temp = input.split("\\|");
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0 ;i < temp.length; i ++){
            result.add(temp[i]);
        }
        return result;
    }
}
