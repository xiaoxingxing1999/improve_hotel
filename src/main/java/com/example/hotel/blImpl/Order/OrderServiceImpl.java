package com.example.hotel.blImpl.Order;

import com.example.hotel.Form.Client.OrderEditForm;
import com.example.hotel.bl.BillService;
import com.example.hotel.bl.Hotel.HotelRoomService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.enums.OrderState;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.User.user;
import com.example.hotel.util.DayHelper;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    HotelRoomService hotelRoomService;
    @Autowired
    BillService billService;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelSituationService hotelSituationService;

    @Override
    public boolean addOrder(hotelorder hotelorder)
            throws ServiceException{
        orderCheck(hotelorder.getCheckInDate(), hotelorder.getCheckOutDate(),hotelorder.getPrice(), hotelorder.getRoomNum());
        Integer validNum = hotelRoomService.findValidRooms(hotelorder.getHotelId(), hotelorder.getRoomType(), hotelorder.getCheckInDate(), hotelorder.getCheckOutDate());
        if(validNum < hotelorder.getRoomNum()){
            throw new ServiceException("该时间范围内剩余房间数量不足");
        }
        //检查房间是否够住
        hotelroom HotelRoom = hotelRoomService.findByHotelAndType(hotelorder.getHotelId(),hotelorder.getRoomType());
        if(hotelorder.getPeopleNum() > (HotelRoom.getValidPeople() * hotelorder.getRoomNum())){
            throw new ServiceException("您的订单中的人数超过房间所能容纳的人数");
        }

        user User = userService.findById(hotelorder.getUserId());
        if(User.getCredit() < hotelorder.getPrice()){
            throw new ServiceException("用户的账户余额不足");
        }

        try{
            orderMapper.save(hotelorder);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("新建订单数据库失败");
        }

        //进行用户预付款
        userService.updateCredit(hotelorder.getUserId(),-hotelorder.getPrice());
        bill bill = new bill(hotelorder.getUserId(), -1 , -hotelorder.getPrice(),
                "您成功支付订单，共计" + hotelorder.getPrice() + "信用值。");
        billService.addBill(bill);
        return true;
    }

    @Override
    public boolean updateOrderState(Integer orderId, OrderState orderState)
            throws ServiceException{
        hotelorder hotelorder = findById(orderId);
        bill bill = null;
        //检查订单的合法状态
        checkTargetStatusValid(hotelorder.getOrderState(),orderState, hotelorder.getCheckOutDate());

        hotel Hotel = hotelService.findById(hotelorder.getHotelId());

        String today = TimeHelper.dateToString(LocalDateTime.now());//今天的日期
        user User = userService.findById(hotelorder.getUserId());
        boolean isVIP = VIPType.isVip(User.getVipType());
        switch (orderState){
            case PASS:
                //查找酒店对应房间
                hotelroom hotelRoom = hotelRoomService.findByHotelAndType(hotelorder.getHotelId(), hotelorder.getRoomType());
                //计算之后的房间结果
                String AfterNum = DayHelper.calculate(hotelRoom.getCurNum(), hotelorder.getCheckInDate()
                        , hotelorder.getCheckOutDate(), hotelorder.getRoomNum());
                //更新酒店房间数量
                hotelRoomService.updateNumber(hotelorder.getHotelId(), hotelorder.getRoomType(), AfterNum);
                //更新订单预定时间
                try{
                    orderMapper.updatePassDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新实际接受订单时间数据库错误");
                }
                break;
            case REFUSE:
                //退还信用值
                userService.updateCredit(hotelorder.getUserId(), hotelorder.getPrice());
                bill = new bill(hotelorder.getUserId(), Hotel.getManagerId() , hotelorder.getPrice(),
                        "酒店拒绝了您的订单，共归还" + hotelorder.getPrice() + "信用值。");
                billService.addBill(bill);
                //更新订单被拒绝时间
                try{
                    orderMapper.updateRefuseDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新实际拒绝订单时间数据库错误");
                }
                break;
            case CANCEL:
                //退还信用值
                Double backCredit = 0.0;
                String content = null;
                if(isValid(hotelorder.getCheckInDate())){
                    backCredit = 0.5 * hotelorder.getPrice();
                    content = "您在订单开始6小时内取消订单，共扣除" + (hotelorder.getPrice() - backCredit)
                            + "信用值，共退还" + backCredit +"信用值。";
                }else{
                    backCredit = hotelorder.getPrice();
                    content = "您的订单已经成功取消，共退还" + backCredit + "信用值。";
                }
                userService.updateCredit(hotelorder.getUserId(), backCredit);
                //更新记录
                bill = new bill(hotelorder.getUserId(), Hotel.getManagerId() , backCredit,
                        content);
                billService.addBill(bill);
                //更新取消订单时间
                try{
                    orderMapper.updateCancelDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新实际入住时间数据库错误");
                }
                //更新酒店当日状态
                hotelSituationService.updateOrderBadNum(hotelorder.getHotelId(), today, 1);
                if(hotelorder.getOrderState().equals(OrderState.PASS)){
                    //退还酒店房间
                    hotelRoom = hotelRoomService.findByHotelAndType(hotelorder.getHotelId(), hotelorder.getRoomType());
                    //计算之后的房间结果
                    AfterNum = DayHelper.calculate(hotelRoom.getCurNum(), hotelorder.getCheckInDate()
                            , hotelorder.getCheckOutDate(), -hotelorder.getRoomNum());
                    //更新酒店房间数量
                    hotelRoomService.updateNumber(hotelorder.getHotelId(), hotelorder.getRoomType(), AfterNum);
                }
                break;
            case CHECKIN:
                try{
                    orderMapper.updateCheckInDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新实际入住时间数据库错误");
                }
                hotelSituationService.updateClientNum(hotelorder.getHotelId(), today, hotelorder.getPeopleNum());
                hotelSituationService.updateTotalClientNum(hotelorder.getHotelId(), today, hotelorder.getPeopleNum());
                if(isVIP){
                    hotelSituationService.updateVIPNum(hotelorder.getHotelId(), today, hotelorder.getPeopleNum());
                }
                break;
            case CHECKOUT:
                try{
                    orderMapper.updateCheckOutDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新实际退房时间数据库错误");
                }
                hotelSituationService.updateOrderDoneNum(hotelorder.getHotelId(), today, 1);
                //退房要减去在住人数
                hotelSituationService.updateTotalClientNum(hotelorder.getHotelId(), today, -hotelorder.getPeopleNum());
                //退还酒店房间
                hotelRoom = hotelRoomService.findByHotelAndType(hotelorder.getHotelId(), hotelorder.getRoomType());
                //计算之后的房间结果
                AfterNum = DayHelper.calculate(hotelRoom.getCurNum(), hotelorder.getCheckInDate()
                        , hotelorder.getCheckOutDate(), -hotelorder.getRoomNum());
                //更新酒店房间数量
                hotelRoomService.updateNumber(hotelorder.getHotelId(), hotelorder.getRoomType(), AfterNum);
                break;
            case ERROR:
                try{
                    orderMapper.updateErrorDate(hotelorder.getId(), LocalDateTime.now());
                }catch (Exception e){
                    e.printStackTrace();
                    throw new ServiceException("更新异常订单时间数据库错误");
                }
                hotelSituationService.updateOrderBadNum(hotelorder.getHotelId(), today, 1);
                break;
            default:
                break;
        }
        //避免出现别的问题
        try{
            orderMapper.updateOrderState(orderId, orderState.ordinal());
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新订单状态数据库失败");
        }
        return true;
    }

    @Override
    public boolean editOrder(OrderEditForm orderEditForm)
            throws ServiceException{
        try{
            orderMapper.updateOrder(TimeHelper.getLocalDateTime(orderEditForm.getCheckInDate()),
                    TimeHelper.getLocalDateTime(orderEditForm.getCheckOutDate()),orderEditForm.isHaveChild(),
                    orderEditForm.getPeopleNum(),orderEditForm.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新订单信息数据库失败");
        }
        return true;
    }

    @Override
    public boolean annualOrder(Integer orderId, Integer userId)
            throws ServiceException{
        hotelorder HotelOrder = orderMapper.findById(orderId).orElse(null);
        if(HotelOrder == null){
            throw new ServiceException("没有找到订单信息");
        }
        if(!HotelOrder.getUserId().equals(userId)){
            throw new ServiceException("无权取消他人订单");
        }
        return updateOrderState(orderId, OrderState.CANCEL);
    }

    @Override
    public hotelorder findById(Integer orderId)
            throws ServiceException{
        hotelorder hotelorder = null;
        try{
            hotelorder = orderMapper.findById(orderId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据主键查找订单数据库失败");
        }
        if(hotelorder == null){
            throw new ServiceException("没有找到这个订单");
        }
        return hotelorder;
    }

    @Override
    public boolean isOrdered(Integer userId, Integer hotelId)
            throws ServiceException {
        if(userId == null){
            return false;
        }
        List<hotelorder> hotelorders;
        try{
            hotelorders = orderMapper.findByUserAndHotel(userId, hotelId);
        }catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("查找是否订阅过数据库错误");
        }
        if(hotelorders == null){
            return false;
        }
        return hotelorders.size() != 0;
    }

    @Override
    public List<hotelorder> findByUser(Integer userId)
            throws ServiceException{
        List<hotelorder> hotelorders = null;
        try{
            hotelorders = orderMapper.findByUser(userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据用户查找订单信息数据库失败");
        }
        return hotelorders;
    }

    @Override
    public List<hotelorder> findByHotel(Integer hotelId)
            throws ServiceException{
        List<hotelorder> hotelorders = null;
        try{
            hotelorders = orderMapper.findByHotel(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据酒店查找订单信息数据库失败");
        }
        return hotelorders;
    }

    @Override
    public List<hotelorder> findAllOrders()
            throws ServiceException{
        List<hotelorder> hotelorders = null;
        try{
            hotelorders = orderMapper.findAllOrders();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有订单信息数据库失败");
        }
        return hotelorders;
    }

    @Override
    public List<hotelorder> findByRoomValid(Integer hotelId, String roomType) throws ServiceException {
        List<hotelorder> hotelorders = null;
        try{
            hotelorders = orderMapper.findByRoom(hotelId, roomType);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据房间信息查找订单数据库失败");
        }
        for(hotelorder HotelOrder: hotelorders){
            if(isOrderOn(HotelOrder)){
                throw new ServiceException("该房间有正在进行中的订单，无法删除房间");
            }
        }
        return null;
    }

    /**
     * 检查订单是否合法
     * @param startTime
     * @param endTime
     * @param price
     * @param roomNum
     * @throws ServiceException
     */
    private void orderCheck(LocalDateTime startTime, LocalDateTime endTime, Double price, Integer roomNum)
            throws ServiceException{
        if(price <= 0 ){
            throw new ServiceException("订单金额应该大于0");
        }
        if(roomNum <= 0){
            throw new ServiceException("房间数量应该大于0");
        }
        //检查时间
        String[] date = TimeHelper.dateToString(LocalDateTime.now()).split("-");
        long today = TimeHelper.getTimeStamp(LocalDateTime.of(Integer.valueOf(date[0])
                ,Integer.valueOf(date[1]), Integer.valueOf(date[2]),0,0));
        long start = TimeHelper.getTimeStamp(startTime);
        long end = TimeHelper.getTimeStamp(endTime);
        if(start < today || start >= end){
            throw new ServiceException("订单时间格式错误");
        }
    }

    /**
     * 检查订单是否在进行中
     * @param HotelOrder
     * @return
     */
    private boolean isOrderOn(hotelorder HotelOrder){
        if(HotelOrder.getOrderState().equals(OrderState.PASS)
                || HotelOrder.getOrderState().equals(OrderState.CHECKIN)){
            return true;
        }
        return false;
    }

    /**
     * 检查转换为目标情况是否合法
     * @param originState
     * @param targetState
     * @param checkOutDate
     * @throws ServiceException
     */
    private void checkTargetStatusValid(OrderState originState, OrderState targetState, LocalDateTime checkOutDate)
            throws ServiceException{
        //一般异常情况判读
        if(originState.equals(OrderState.REFUSE)){
            throw new ServiceException("订单已经被拒绝，无法继续处理");
        }
        if(originState.equals(OrderState.CANCEL)){
            throw new ServiceException("订单已经被用户取消，无法继续处理");
        }
        if(originState.equals(OrderState.ERROR)){
            throw new ServiceException("订单状态异常，无法继续处理");
        }

        if(targetState.equals(OrderState.ERROR)){
            long checkOut = TimeHelper.getTimeStamp(checkOutDate);
            if(System.currentTimeMillis() < checkOut){
                throw new ServiceException("订单未到预计退房时间，无法设置为异常");
            }
        }
        //流程检查
        if(targetState.equals(OrderState.PASS) && !originState.equals(OrderState.WAIT)){
            throw new ServiceException("订单已经处理，无法再次通过");
        }
        if(targetState.equals(OrderState.REFUSE) && !originState.equals(OrderState.WAIT)){
            throw new ServiceException("订单已经处理，无法再次拒绝");
        }
        if(targetState.equals(OrderState.CHECKIN) && !originState.equals(OrderState.PASS)){
            throw new ServiceException("订单状态不是PASS状态");
        }
        if(targetState.equals(OrderState.CHECKOUT) && !originState.equals(OrderState.CHECKIN)){
            throw new ServiceException("订单状态不是CHECKIN状态");
        }
    }

    /**
     * 检查时间，如果在六个小时内则为true，否则为false
     * @param startTime
     * @return
     */
    private static boolean isValid(LocalDateTime startTime){
        long now = System.currentTimeMillis();
        long start = TimeHelper.getTimeStamp(startTime);
        return (start - now <= (6 * 60 * 60 * 1000));
    }

    public static void main(String[] args){
        System.out.println(isValid(LocalDateTime.of(2020,5,28,18,30,0)));
        System.out.println(isValid(LocalDateTime.of(2020,5,28,18,23,0)));
        System.out.println(isValid(LocalDateTime.of(2020,5,28,14,30,0)));
    }
}
