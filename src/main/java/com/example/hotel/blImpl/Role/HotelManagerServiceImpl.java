package com.example.hotel.blImpl.Role;

import com.example.hotel.Form.HotelManager.*;
import com.example.hotel.bl.Hotel.*;
import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.bl.Order.SituationService;
import com.example.hotel.bl.Role.HotelManagerService;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Hotel.*;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
import com.example.hotel.vo.HotelSituationVO;
import com.example.hotel.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class HotelManagerServiceImpl implements HotelManagerService {
    @Autowired
    OrderService orderService;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelRoomService hotelRoomService;
    @Autowired
    HotelEditService hotelEditService;
    @Autowired
    CouponService couponService;
    @Autowired
    HotelServeService hotelServeService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    SituationService situationService;

    @Override
    public boolean addRoom(RoomForm roomForm) throws ServiceException {
        user User = UserHelper.checkRole(roomForm.getToken(), UserType.HotelManager);
        Integer hotelId = hotelService.findByManagerId(User.getId());
        if(!hotelId.equals(roomForm.getHotelId())){
            throw new ServiceException("不能为其他酒店新建房间");
        }
        hotelroom hotelRoom = new hotelroom(roomForm);
        hotelRoomService.addHotelRoom(hotelRoom);
        return true;
    }

    @Override
    public boolean editHotel(HotelEditForm hotelEditForm) throws ServiceException {
        user user = UserHelper.checkRole(hotelEditForm.getToken(), UserType.HotelManager);
        Integer hotelId = hotelService.findByManagerId(user.getId());
        hoteledit hotelEdit = new hoteledit(hotelId, hotelEditForm);
        return hotelEditService.addHotelEdit(hotelEdit);
    }

    @Override
    public boolean confirm(OrderCheckForm orderCheckForm) throws ServiceException {
        user User = UserHelper.checkRole(orderCheckForm.getToken(), UserType.HotelManager);
        hotelorder HotelOrder = orderService.findById(orderCheckForm.getId());
        Integer hotelId =hotelService.findByManagerId(User.getId());
        if(!HotelOrder.getHotelId().equals(hotelId)){
            throw new ServiceException("不能审核其他酒店的订单");
        }
        orderService.updateOrderState(orderCheckForm.getId(),
                    orderCheckForm.getOrderState());
        return true;
    }

    @Override
    public List<hotelorder> findHotelOrders(String token) throws ServiceException {
        user user = UserHelper.checkRole(token, UserType.HotelManager);
        Integer hotelId = hotelService.findByManagerId(user.getId());
        return orderService.findByHotel(hotelId);
    }

    @Override
    public List<hoteledit> findByManager(String token) throws ServiceException {
        user user = UserHelper.checkRole(token, UserType.HotelManager);
        Integer hotelId = hotelService.findByManagerId(user.getId());
        return hotelEditService.findByHotel(hotelId);
    }

    @Override
    public boolean delRoom(String token, Integer roomId) throws ServiceException {
        user User = UserHelper.checkRole(token, UserType.HotelManager);
        hotelroom HotelRoom = hotelRoomService.findById(roomId);
        Integer hotelId = hotelService.findByManagerId(User.getId());
        if(!HotelRoom.getHotelId().equals(hotelId)) {
            throw new ServiceException("您不能删除其他酒店的房间");
        }
        //检查当前是否有正在进行中的订单
        orderService.findByRoomValid(HotelRoom.getHotelId(),HotelRoom.getRoomType());
        return hotelRoomService.delHotelRoom(roomId);
    }

    @Override
    public List<coupon> getMyCoupon(String token) throws ServiceException {
        user user = UserHelper.checkRole(token, UserType.HotelManager);
        List<coupon> coupons = null;
        Integer hotelId = hotelService.findByManagerId(user.getId());
        try{
            coupons = couponService.getHotelAllCoupon(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找一个酒店的全部账单失败");
        }
        //同时返回所有的酒店的优惠券
        List<coupon> siteCoupons = couponService.getSiteAllCoupon();
        coupons.addAll(siteCoupons);
        return coupons;
    }

    @Override
    public boolean addServe(ServeForm serveForm) throws ServiceException {
        user user = UserHelper.checkRole(serveForm.getToken(), UserType.HotelManager);
        hotelserve hotelServe = new hotelserve(user.getId(), serveForm);
        return hotelServeService.addService(hotelServe);
    }

    @Override
    public boolean addRoomType(RoomTypeForm roomTypeForm) throws ServiceException {
        user user = UserHelper.checkRole(roomTypeForm.getToken(), UserType.HotelManager);
        roomtype roomType = new roomtype(user.getId(), roomTypeForm);
        return roomTypeService.addRoomType(roomType);
    }

    @Override
    public List<roomtype> getAllRoomType(String token) throws ServiceException {
        try{
            UserHelper.checkRole(token, UserType.HotelManager);
        }catch (Exception e){
            UserHelper.checkRole(token, UserType.Client);
        }
        return roomTypeService.findAllRoomTypes();
    }

    @Override
    public HotelVO getHotelInfo(String token) throws ServiceException{
        user User = UserHelper.checkRole(token, UserType.HotelManager);
        hotel Hotel = hotelService.findHotelByManagerId(User.getId());
        return hotelService.findDetails(Hotel.getId(), null);
    }

    @Override
    public List<HotelSituationVO> hotelSituation(String token) throws ServiceException{
        user User = UserHelper.checkRole(token, UserType.HotelManager);
        Integer hotelId = hotelService.findByManagerId(User.getId());
        return situationService.getHotelSituation(hotelId);
    }
}
