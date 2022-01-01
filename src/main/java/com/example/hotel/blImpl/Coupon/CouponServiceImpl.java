package com.example.hotel.blImpl.Coupon;

import com.example.hotel.Form.HotelManager.Coupon.BirthdayCoupon;
import com.example.hotel.Form.HotelManager.Coupon.RoomCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TargetMoneyCoupon;
import com.example.hotel.Form.HotelManager.Coupon.TimeCoupon;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.enums.Coupon.CouponStatus;
import com.example.hotel.enums.Coupon.Type;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Order.hotelorder;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CouponServiceImpl implements CouponService {

    private final CouponMapper couponMapper;
    private final HotelService hotelService;
    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final TimeCouponStrategyImpl timeCouponStrategy;
    private final BirthDayCouponStrategyImpl birthDayCouponStrategy;
    private final RoomCouponStrategyImpl roomCouponStrategy;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             BirthDayCouponStrategyImpl birthDayCouponStrategy,
                             RoomCouponStrategyImpl roomCouponStrategy,
                             CouponMapper couponMapper,HotelService hotelService) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.birthDayCouponStrategy = birthDayCouponStrategy;
        this.roomCouponStrategy = roomCouponStrategy;
        this.hotelService = hotelService;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(birthDayCouponStrategy);
        strategyList.add(roomCouponStrategy);
    }

    @Override
    public List<coupon> getMatchOrderCoupon(hotelorder hotelorder) throws ServiceException{

        hotelService.findById(hotelorder.getHotelId());
        if(hotelorder.getPrice() < 0){
            throw new ServiceException("订单金额不能为负");
        }
        if(hotelorder.getRoomNum() < 0){
            throw new ServiceException("订单房间数不能为负");
        }
        if(!hotelorder.getCheckInDate().isBefore(hotelorder.getCheckOutDate())){
            throw new ServiceException("入住时间应该早于退房时间");
        }
        //本酒店的和全网站的
        List<coupon> coupons = getHotelAllCoupon(hotelorder.getHotelId());
        coupons.addAll(getSiteAllCoupon());

        //筛选VALID的优惠券
        coupons = coupons.stream().filter(
                coupon -> coupon.getStatus().equals(CouponStatus.VALID))
                .collect(Collectors.toList());

        List<coupon> availAbleCoupons = new ArrayList<>();

        //具体匹配订单使用的优惠券
        for (int i = 0; i < coupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(hotelorder, coupons.get(i))) {
                    availAbleCoupons.add(coupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<coupon> getHotelAllCoupon(Integer hotelId) throws ServiceException{
        List<coupon> hotelCoupons = null;
        try{
            hotelCoupons = couponMapper.selectByHotelId(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查看酒店优惠券信息数据库错误");
        }
        return hotelCoupons;
    }

    @Override
    public List<coupon> getSiteAllCoupon() throws ServiceException {
        List<coupon> hotelCoupons = null;
        try{
            hotelCoupons = couponMapper.selectBySite();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查看酒店优惠券信息数据库错误");
        }
        return hotelCoupons;
    }

    @Override
    public boolean addHotelTargetMoneyCoupon(TargetMoneyCoupon targetMoneyCoupon) throws ServiceException{
        if(!identity(targetMoneyCoupon.getToken(), targetMoneyCoupon.getHotelId())){
            throw new ServiceException("您没有权限操作");
        }
        coupon coupon = new coupon(targetMoneyCoupon);
        return addCoupon(coupon);
    }

    @Override
    public boolean addTimeCoupon(TimeCoupon timeCoupon) throws ServiceException {
        if(!identity(timeCoupon.getToken(),timeCoupon.getHotelId())){
            throw new ServiceException("您没有权限操作");
        }
        coupon coupon = new coupon(timeCoupon);
        return addCoupon(coupon);
    }

    @Override
    public boolean addBirthdayCoupon(BirthdayCoupon birthdayCoupon) throws ServiceException{
        if(!identity(birthdayCoupon.getToken(),birthdayCoupon.getHotelId())){
            throw new ServiceException("您没有权限操作");
        }
        coupon coupon = new coupon(birthdayCoupon);
        return addCoupon(coupon);
    }

    @Override
    public boolean addRoomCoupon(RoomCoupon roomCoupon) throws ServiceException{
        if(!identity(roomCoupon.getToken(), roomCoupon.getHotelId())){
            throw new ServiceException("您没有权限操作");
        }
        coupon coupon = new coupon(roomCoupon);
        return addCoupon(coupon);
    }

    @Override
    public boolean unableCoupon(String token, Integer couponId) throws ServiceException{
        user user = UserHelper.getByToken(token);
        coupon coupon = findById(couponId);
        if(coupon == null){
            throw new ServiceException("没有找到优惠券");
        }
        if(user.getUserType().equals(UserType.HotelManager)){
            Integer hotelId = hotelService.findByManagerId(user.getId());
            if(!hotelId.equals(coupon.getHotelId())){
                throw new ServiceException("您没有权限操作这个优惠券");
            }
        }else if(user.getUserType().equals(UserType.SiteMarket)){
            if(!coupon.getHotelId().equals(-1)){
                throw new ServiceException("您没有权限操作这个优惠券");
            }
        }else{
            throw new ServiceException("您没有权限进行操作");
        }
        if(coupon.getStatus().equals(CouponStatus.INVALID)){
            throw new ServiceException("酒店优惠券已经失效");
        }

        try{
            couponMapper.updateStatus(couponId, String.valueOf(CouponStatus.INVALID.ordinal()));
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新优惠券数据库错误");
        }
        return true;
    }

    @Override
    public List<coupon> getAllCoupon() throws ServiceException {
        List<coupon> coupons = null;
        try{
            coupons = couponMapper.findAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查看所有优惠券数据库错误");
        }
        return coupons;
    }

    /**
     * 新建优惠券
     * @param coupon
     * @return
     * @throws ServiceException
     */
    private boolean addCoupon(coupon coupon) throws ServiceException{
        isValid(coupon);
        try{
            couponMapper.save(coupon);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加优惠券数据库错误");
        }
        return true;
    }

    /**
     * 检查优惠券信息是否合格
     * @param coupon
     * @throws ServiceException
     */
    private void isValid(coupon coupon) throws ServiceException{
        long start = TimeHelper.getTimeStamp(coupon.getStartTime());
        long end = TimeHelper.getTimeStamp(coupon.getEndTime());
        if(start > end){
            throw new ServiceException("优惠券生效时间必须早于失效时间");
        }

        if(coupon.getTargetMoney() == null){
            throw new ServiceException("优惠卷应该有满足的条件");
        }
        if(coupon.getTargetMoney() <= 0){
            throw new ServiceException("优惠券满足条件不应该小于等于0");
        }
        if(coupon.getType().equals(Type.MANJIAN)){
            if(coupon.getDiscount() != 0.0){
                throw new ServiceException("满减策略不应该有折扣");
            }
            if(coupon.getDiscountMoney() == null){
                throw new ServiceException("满减策略的减免金额不能为空");
            }
            if(coupon.getDiscountMoney() <= 0){
                throw new ServiceException("满减优惠的减免金额不能小于等于0");
            }
            if(coupon.getTargetMoney() < coupon.getDiscountMoney()){
                throw new ServiceException("优惠券满减的金额不能小于满减需要满足的金额");
            }
        }else if(coupon.getType().equals(Type.ZHEKOU)){
            if(coupon.getDiscountMoney() != 0.0){
                throw new ServiceException("折扣策略的不应该有减免金额");
            }
            if(coupon.getDiscount() == null){
                throw new ServiceException("折扣策略的折扣不能为空");
            }
            if(!(0 < coupon.getDiscount() && coupon.getDiscount() < 1)){
                throw new ServiceException("优惠券折扣不正确");
            }

        }else{
            throw new ServiceException("优惠券的优惠格式错误");
        }

    }

    /**
     * 根据ID获取优惠券
     * @param couponId
     * @return
     * @throws ServiceException
     */
    private coupon findById(Integer couponId) throws ServiceException{
        coupon coupon = null;
        try{
            coupon = couponMapper.selectById(couponId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找订单信息错误");
        }
        if(coupon == null){
            System.out.println("没有找到对应的优惠券");
        }
        return coupon;
    }

    /**
     * 检查用户有无权限操作优惠券
     * @param token
     * @param hotelID
     * @return
     * @throws ServiceException
     */
    private boolean identity(String token, Integer hotelID) throws ServiceException{
        user user = UserHelper.getByToken(token);
        if(user.getUserType().equals(UserType.HotelManager)){
            Integer hotelId = hotelService.findByManagerId(user.getId());
            if(!hotelID.equals(hotelId)){
                throw new ServiceException("酒店管理人员没有权限为其他酒店制定优惠");
            }
            return true;
        }else if(user.getUserType().equals(UserType.SiteMarket)){
            if(!hotelID.equals(-1)){
                throw new ServiceException("网站营销人员没有权限为特定酒店制定优惠");
            }
            return true;
        }else{
            throw new ServiceException("您没有权限");
        }
    }
}
