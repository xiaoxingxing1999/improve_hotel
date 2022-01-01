package com.example.hotel.controller.Role;

import com.example.hotel.Form.Client.*;
import com.example.hotel.bl.Role.ClientService;
import com.example.hotel.po.Order.hotelorder;
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
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/register")
    public SimpleResponse register(@RequestBody ClientForm clientForm) throws ServiceException {
        System.out.println("POST./api/client/register:" + clientForm.toString());
        clientService.addClient(clientForm);
        return SimpleResponse.ok("注册客户成功");
    }

    @PostMapping("/addOrder")
    public SimpleResponse addOrder(@RequestBody OrderForm orderForm) throws ServiceException{
        System.out.println("POST./api/client/addOrder:" + orderForm.toString());
        clientService.addOrder(orderForm);
        return SimpleResponse.ok("添加订单成功");
    }

    //@PostMapping("/editOrder") 暂时放弃
    public SimpleResponse editOrder(@RequestBody OrderEditForm orderEditForm) throws ServiceException{
        System.out.println("POST./api/client/editOrder:" + orderEditForm.toString());
        boolean result = clientService.editOrder(orderEditForm);
        return result ? SimpleResponse.ok("修改订单成功"):
                SimpleResponse.error("修改订单失败");
    }

    @PostMapping("/annualOrder")
    public SimpleResponse annualOrder(@RequestParam String token,
                                      @RequestParam Integer orderId) throws ServiceException{
        System.out.println("POST./api/client/annualOrder?token=" + token + "&orderId=" + orderId);
        boolean result = clientService.annualOrder(orderId, token);
        return result ? SimpleResponse.ok("撤销订单成功") :
                SimpleResponse.error("撤销订单失败");
    }

    @PostMapping("/checkIn")
    public SimpleResponse checkIn(@RequestBody ClientVIPForm clientVIPForm) throws ServiceException{
        System.out.println("POST./api/client/checkIn:" + clientVIPForm.toString());
        boolean result = clientService.updateVIP(clientVIPForm);
        return result ? SimpleResponse.ok("登记VIP信息成功") :
                SimpleResponse.error("登记VIP信息失败");
    }

    @PostMapping("/comment")
    public SimpleResponse comment(@RequestBody CommentForm commentForm) throws ServiceException{
        System.out.println("POST./api/client/comment:" + commentForm.toString());
        boolean result = clientService.comment(commentForm);
        return result ? SimpleResponse.ok("评价订单成功") :
                SimpleResponse.error("评价订单失败");
    }

    @GetMapping("/orderMatchCoupons")
    public SimpleResponse orderMatchCoupons(@RequestParam Integer hotelId,
                                            @RequestParam Double orderPrice,
                                            @RequestParam Integer roomNum,
                                            @RequestParam long checkIn,
                                            @RequestParam long checkOut,
                                            @RequestParam String token) throws ServiceException{
        System.out.println("GET./api/client/orderMatchCoupons?hotelId=" + hotelId + "&orderPrice=" + orderPrice
                + "&roomNum=" + roomNum + "&checkIn=" + checkIn + "&checkOut=" + checkOut  + "&token=" + token);
        hotelorder hotelorder = new hotelorder(hotelId, checkIn, checkOut, roomNum, orderPrice);
        return SimpleResponse.ok(clientService.matchCoupons(token, hotelorder));
    }

    @GetMapping("/getMyBills")
    public SimpleResponse getMyBills(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/client/getMyBills?token=" + token);
        return SimpleResponse.ok(clientService.getMyBills(token));
    }

    @GetMapping("/getUserOrders")
    public SimpleResponse getUserOrder(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/client/getUserOrders?token=" + token);
        return SimpleResponse.ok(clientService.getMyOrders(token));
    }

}
