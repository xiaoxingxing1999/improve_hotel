package com.example.hotel.util;

import com.example.hotel.enums.UserType;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author stormbroken
 * Create by 2020/05/23
 * @Version 1.0
 **/

@Component
public class UserHelper {
    /**
     * 内存持有登录状态
     */
    private static Map<String, user> userMap = Collections.synchronizedMap(new HashMap<>());

    /**
     * 查询对应tokne的用户登录状态
     * @param token
     * @return
     * @throws ServiceException
     */
    public static user getByToken(String token) throws ServiceException {
        for(String string: userMap.keySet()){
            if(string.equals(token)){
                return userMap.get(string);
            }
        }
        throw new ServiceException("用户没有登陆");
    }

    /**
     * 查看用户是否登录，是否为对应角色
     * @param token
     * @param userType
     * @return
     * @throws ServiceException
     */
    public static user checkRole(String token, UserType userType) throws ServiceException{
        user user = UserHelper.getByToken(token);
        if(!user.getUserType().equals(userType)){
            throw new ServiceException("用户没有权限");
        }
        return user;
    }

    /**
     * 用户登录，加载内存
     * @param token
     * @param user
     */
    public static void login(String token, user user){
        userMap.put(token, user);
    }

    /**
     * 用户登出，释放内存
     * @param token
     * @return
     */
    public static boolean logOut(String token){
        for(String string: userMap.keySet()){
            if(string.equals(token)){
                System.out.println(token +":用户登出成功");
                userMap.remove(token);
                return true;
            }
        }
        return false;
    }

}
