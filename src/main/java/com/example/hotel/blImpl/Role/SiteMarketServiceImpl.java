package com.example.hotel.blImpl.Role;

import com.example.hotel.Form.SiteMarket.CreditForm;
import com.example.hotel.bl.BillService;
import com.example.hotel.bl.Hotel.BizRegionService;
import com.example.hotel.bl.Order.SituationService;
import com.example.hotel.bl.Role.SiteMarketService;
import com.example.hotel.bl.UserService;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Bill.bill;
import com.example.hotel.po.Coupon.coupon;
import com.example.hotel.po.Hotel.bizregion;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
import com.example.hotel.vo.SiteSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class SiteMarketServiceImpl implements SiteMarketService {
    @Autowired
    UserService userService;
    @Autowired
    BizRegionService bizRegionService;
    @Autowired
    BillService billService;
    @Autowired
    SituationService situationService;
    @Autowired
    CouponService couponService;

    @Override
    public boolean addBizRegion(String token, String name) throws ServiceException {
        user user = UserHelper.checkRole(token, UserType.SiteMarket);
        bizregion bizRegion = new bizregion(name, user.getId());
        return bizRegionService.addBizRegion(bizRegion);
    }

    @Override
    public boolean updateCredit(CreditForm creditForm) throws ServiceException {
        user user = UserHelper.checkRole(creditForm.getToken(), UserType.SiteMarket);

        if(creditForm.getCredit() <= 0){
            throw new ServiceException("充值金额应该大于0");
        }
        //检查用户有无在系统中
        user User = userService.findById(creditForm.getUid());
        //被充值身份必须为客户
        if(!User.getUserType().equals(UserType.Client)){
            throw new ServiceException("酒店营销人员不能为其他类型用户充值");
        }

        userService.updateCredit(creditForm.getUid(),creditForm.getCredit());
        bill bill = new bill(creditForm.getUid(), user.getId(), creditForm.getCredit(),
                "网站营销人员" + user.getUserName() + "成功为您充值" + creditForm.getCredit() +"信用值。");
        return billService.addBill(bill);
    }

    @Override
    public List<bill> getAllBills(String token) throws ServiceException {
        user User = UserHelper.checkRole(token, UserType.SiteMarket);
        return billService.findAllBills(User.getId());
    }

    @Override
    public List<SiteSituationVO> situation(String token) throws ServiceException {
        UserHelper.checkRole(token, UserType.SiteMarket);
        return situationService.getSiteSituation();
    }

    @Override
    public List<coupon> getAllCoupons(String token) throws ServiceException {
        UserHelper.checkRole(token, UserType.SiteMarket);
        return couponService.getAllCoupon();
    }
}
