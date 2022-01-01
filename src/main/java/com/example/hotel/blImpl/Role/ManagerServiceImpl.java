package com.example.hotel.blImpl.Role;

import com.example.hotel.Form.HotelManager.CheckHotelEditForm;
import com.example.hotel.Form.HotelManager.HotelManagerForm;
import com.example.hotel.Form.Manager.HotelForm;
import com.example.hotel.bl.Hotel.HotelEditService;
import com.example.hotel.bl.Hotel.HotelServeService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Role.ManagerService;
import com.example.hotel.bl.UserService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    UserService userService;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelEditService hotelEditService;

    @Override
    public boolean addHotel(HotelForm hotelForm) throws ServiceException {
        UserHelper.checkRole(hotelForm.getToken(), UserType.Manager);
        hotel hotel = new hotel(hotelForm);
        hotelService.addHotel(hotel);
        return true;
    }

    @Override
    public boolean reply(CheckHotelEditForm checkHotelEditForm) throws ServiceException {
        UserHelper.checkRole(checkHotelEditForm.getToken(), UserType.Manager);
        return hotelEditService.checkHotelEdit(checkHotelEditForm.getStatus(),
                checkHotelEditForm.getReplyContent(), checkHotelEditForm.getId());
    }

    @Override
    public boolean addHotelManager(HotelManagerForm hotelManagerForm) throws ServiceException{
        UserHelper.checkRole(hotelManagerForm.getToken(), UserType.Manager);
        //检查有没有这个酒店
        hotelService.findById(hotelManagerForm.getHotelId());

        //新建一个酒店管理人员
        user user = new user(hotelManagerForm);
        userService.addUser(user);
        //找到酒店管理人员的id
        user = userService.findByEmail(user.getEmail());

        return hotelService.addHotelManager(hotelManagerForm.getHotelId(), user.getId());
    }

    @Override
    public List<user> getAllHotelManager(String token) throws ServiceException{
        UserHelper.checkRole(token, UserType.Manager);
        return userService.getHotelManager();
    }

    @Override
    public List<hoteledit> getAllHotelEdit(String token) throws ServiceException {
        UserHelper.checkRole(token, UserType.Manager);
        List<hoteledit> hoteledits = null;
        try{
            hoteledits = hotelEditService.findAllHotelEdits();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return hoteledits;
    }
}
