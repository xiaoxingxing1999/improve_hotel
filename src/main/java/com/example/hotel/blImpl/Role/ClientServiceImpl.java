package com.example.hotel.blImpl.Role;

import com.example.hotel.Form.Client.*;
import com.example.hotel.bl.BillService;
import com.example.hotel.bl.CommentService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.bl.Role.ClientService;
import com.example.hotel.bl.UserService;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.enums.OrderState;
import com.example.hotel.enums.UserType;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.Order.hotelorder;
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
public class ClientServiceImpl implements ClientService {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    CommentService commentService;
    @Autowired
    CouponService couponService;
    @Autowired
    BillService billService;
    @Autowired
    HotelService hotelService;

    @Override
    public boolean addOrder(OrderForm orderForm) throws ServiceException {
        user user = UserHelper.checkRole(orderForm.getToken(), UserType.Client);
        hotelorder hotelorder = new hotelorder(user.getId(), orderForm);
        return orderService.addOrder(hotelorder);
    }

    @Override
    public boolean editOrder(OrderEditForm orderEditForm) throws ServiceException{
        return (UserHelper.checkRole(orderEditForm.getToken(), UserType.Client) != null) &&
                checkOrderStatus(orderEditForm.getId(),OrderState.WAIT)
                && orderService.editOrder(orderEditForm);
    }

    @Override
    public boolean annualOrder(Integer orderId, String token) throws ServiceException{
        user User = UserHelper.checkRole(token, UserType.Client);
        Integer userId = User.getId();
        return orderService.annualOrder(orderId, userId);
    }

    @Override
    public boolean addClient(ClientForm clientForm) throws ServiceException{
        user user = new user(clientForm);
        return userService.addUser(user);
    }

    @Override
    public boolean comment(CommentForm commentForm) throws ServiceException{
        user user = UserHelper.checkRole(commentForm.getToken(), UserType.Client);
        hotelorder hotelorder = orderService.findById(commentForm.getOid());
        if(!hotelorder.getUserId().equals(user.getId())){
            throw new ServiceException("没有权限评价他人的订单");
        }
        if(!hotelorder.getOrderState().equals(OrderState.CHECKOUT)){
            throw new ServiceException("订单还没有完成无法评价");
        }
        comment Comment = commentService.findByUidAndOid(commentForm.getOid(), user.getId());
        if(Comment != null){
            throw new ServiceException("用户已经评论过这个订单，请不要重复评论");
        }

        comment comment = new comment(user.getId(), commentForm);
        if(!(0 <= comment.getRate() && comment.getRate() <= 5)){
            throw new ServiceException("订单评分应当在0到5分之间");
        }
        commentService.addComment(comment);
        return hotelService.updateRate(hotelorder.getHotelId());
    }

    @Override
    public boolean updateVIP(ClientVIPForm clientVIPForm) throws ServiceException{
        return userService.updateVIP(clientVIPForm);
    }

    @Override
    public List<hotelorder> getMyOrders(String token) throws ServiceException{
        user user = UserHelper.checkRole(token, UserType.Client);
        List<hotelorder> hotelorders = orderService.findByUser(user.getId());
        return hotelorders;
    }

    @Override
    public List<coupon> matchCoupons(String token, hotelorder hotelorder) throws ServiceException{
        user user = UserHelper.checkRole(token, UserType.Client);
        hotelorder.setUserId(user.getId());
        return couponService.getMatchOrderCoupon(hotelorder);
    }

    @Override
    public List<bill> getMyBills(String token) throws ServiceException{
        user user = UserHelper.checkRole(token, UserType.Client);
        return billService.findByUser(user.getId());
    }

    /**
     * 检查是否有权处理，和是否合法
     * @param orderId
     * @param orderState
     * @return
     * @throws ServiceException
     */
    private boolean checkOrderStatus(Integer orderId, OrderState orderState) throws ServiceException{
        hotelorder hotelorder = orderService.findById(orderId);
        if(!hotelorder.getUserId().equals(hotelorder.getUserId())){
            throw new ServiceException("没有权限处理订单");
        }
        if(!hotelorder.getOrderState().equals(orderState)) {
            throw new ServiceException("账单状态错误");
        }
        return true;
    }
}
