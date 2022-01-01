package com.example.hotel.controller.Role;

import com.example.hotel.Form.HotelManager.CheckHotelEditForm;
import com.example.hotel.Form.HotelManager.HotelManagerForm;
import com.example.hotel.Form.Manager.HotelForm;
import com.example.hotel.bl.Role.ManagerService;
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
@RequestMapping("/api/admin")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @PostMapping("/addManager")
    public SimpleResponse addManager(@RequestBody HotelManagerForm hotelManagerForm) throws ServiceException {
        System.out.println("POST./api/admin/addManager:" + hotelManagerForm.toString());
        boolean result = managerService.addHotelManager(hotelManagerForm);
        return result ? SimpleResponse.ok("新建酒店工作人员成功") :
                SimpleResponse.error("新建酒店工作人员失败");
    }

    @PostMapping("/addHotel")
    public SimpleResponse addHotel(@RequestBody HotelForm hotelForm) throws ServiceException{
        System.out.println("POST./api/admin/addHotel:" + hotelForm.toString());
        boolean result = managerService.addHotel(hotelForm);
        return result ? SimpleResponse.ok("新建酒店成功"):
                SimpleResponse.error("新建酒店失败");
    }

    @PostMapping("/checkHotelEdit")
    public SimpleResponse checkHotelEdit(@RequestBody CheckHotelEditForm checkHotelEditForm) throws ServiceException{
        System.out.println("POST./api/admin/checkHotelEdit:" + checkHotelEditForm.toString());
        boolean result = managerService.reply(checkHotelEditForm);
        return result ? SimpleResponse.ok("审核酒店修改申请成功"):
                SimpleResponse.error("审核酒店修改申请失败");
    }

    @GetMapping("/getAllManagers")
    public SimpleResponse getAllManagers(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/admin/getAllManagers?token=" + token);
        return SimpleResponse.ok(managerService.getAllHotelManager(token));
    }

    @GetMapping("/getAllHotelEdit")
    public SimpleResponse getAllHotelEdit(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/admin/getAllHotelEdit?token=" + token);
        return SimpleResponse.ok(managerService.getAllHotelEdit(token));
    }
}
