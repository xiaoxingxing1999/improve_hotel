package com.example.hotel.controller.Role;

import com.example.hotel.Form.HotelManager.*;
import com.example.hotel.bl.Role.HotelManagerService;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.Response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/hotelManager")
public class HotelManagerController {
    @Autowired
    HotelManagerService hotelManagerService;

    @PostMapping("/addRoom")
    public SimpleResponse addRoom(@RequestBody RoomForm roomForm) throws ServiceException {
        System.out.println("POST./api/hotelManager/addRoom:" + roomForm.toString());
        boolean result = hotelManagerService.addRoom(roomForm);
        return result ? SimpleResponse.ok("新建房间成功") :
                SimpleResponse.error("新建房间失败");
    }

    @PostMapping("/editHotel")
    public SimpleResponse editHotel(@RequestBody HotelEditForm hotelEditForm) throws ServiceException{
        System.out.println("POST./api/hotelManager/editHotel:" + hotelEditForm.toString());
        boolean result = hotelManagerService.editHotel(hotelEditForm);
        return result ? SimpleResponse.ok("提交酒店信息修改申请成功") :
                SimpleResponse.error("提交酒店信息修改申请失败");
    }

    @PostMapping("/confirm")
    public SimpleResponse confirm(@RequestBody OrderCheckForm orderCheckForm) throws ServiceException{
        System.out.println("POST./api/hotelManager/confirm:" + orderCheckForm.toString());
        boolean result = hotelManagerService.confirm(orderCheckForm);
        return result ? SimpleResponse.ok("审核订单成功") :
                SimpleResponse.error("审核订单错误");
    }

    @PostMapping("/delRoom")
    public SimpleResponse delRoom(@RequestParam String token,
                                  @RequestParam Integer roomId) throws ServiceException{
        System.out.println("POST./api/hotelManager/delRoom?token=" + token + "&roomId=" + roomId);
        boolean result = hotelManagerService.delRoom(token, roomId);
        return result ? SimpleResponse.ok("删除房间成功") :
                SimpleResponse.error("删除房间失败");
    }

    @PostMapping("/addServe")
    public SimpleResponse addServe(@RequestBody ServeForm serveForm) throws ServiceException{
        System.out.println("POST./api/hotelManager/addServe:" + serveForm.toString());
        boolean result = hotelManagerService.addServe(serveForm);
        return result ? SimpleResponse.ok("新建酒店服务成功") :
                SimpleResponse.error("新建酒店服务失败");
    }

    @PostMapping("/addRoomType")
    public SimpleResponse addRoomType(@RequestBody RoomTypeForm roomTypeForm) throws ServiceException{
        System.out.println("POST./api/hotelManager/addRoomType:" + roomTypeForm.toString());
        boolean result = hotelManagerService.addRoomType(roomTypeForm);
        return result ? SimpleResponse.ok("新建房间类型成功") :
                SimpleResponse.error("新建房间类型失败");
    }


    @GetMapping("/checkEdit")
    public SimpleResponse checkEdit(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/checkEdit?token=" + token);
        return SimpleResponse.ok(hotelManagerService.findByManager(token));
    }

    @GetMapping("/getHotelOrders")
    public SimpleResponse getHotelOrders(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/getHotelOrders?token=" + token);
        return SimpleResponse.ok(hotelManagerService.findHotelOrders(token));
    }

    @GetMapping("/hotelSituation")
    public SimpleResponse getHotelSituation(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/hotelSituation?token=" + token);
        return SimpleResponse.ok(hotelManagerService.hotelSituation(token));
    }

    @GetMapping("/hotelAllCoupon")
    public SimpleResponse getHotelAllCoupon(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/hotelAllCoupon?token=" + token);
        return SimpleResponse.ok(hotelManagerService.getMyCoupon(token));
    }

    @GetMapping("/getAllRoomType")
    public SimpleResponse getAllRoomType(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/getAllRoomType?token=" + token);
        return SimpleResponse.ok(hotelManagerService.getAllRoomType(token));
    }

    @GetMapping("/getHotelInfo")
    public SimpleResponse getHotelInfo(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/getHotelInfo?token=" + token);
        return SimpleResponse.ok(hotelManagerService.getHotelInfo(token));
    }

}
