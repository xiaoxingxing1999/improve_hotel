package com.example.hotel.util.password;

import com.example.hotel.util.Response.ServiceException;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author stormbroken
 * Create by 2020/05/20
 * @Version 1.0
 **/

public class MD5Encryption {
    /**
     * 修改密码需要加密,使用单向哈希MD5进行加密
     * @param word
     * @return
     * @throws ServiceException
     */
    public static String encrypt(String word) throws ServiceException{
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("密码无法加密");
        }
        byte[] input = word.getBytes();
        byte[] output = md.digest(input);
        return Base64.encodeBase64String(output);
    }

    public static void main(String[] args) throws Exception{
        System.out.println(encrypt("123456"));
    }
}