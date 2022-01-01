package com.example.hotel.controller.Role;

import com.example.hotel.Form.SiteMarket.CreditForm;
import com.example.hotel.bl.Role.SiteMarketService;
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
@RequestMapping("/api/siteMarket")
public class SiteMarketController {
    @Autowired
    SiteMarketService siteMarketService;
    @PostMapping("/addCredit")
    public SimpleResponse addCredit(@RequestBody CreditForm creditForm) throws ServiceException {
        System.out.println("POST./api/siteMarket/addCredit:" + creditForm.toString());
        boolean result = siteMarketService.updateCredit(creditForm);
        return result? SimpleResponse.ok("更新用户信用值成功") :
                SimpleResponse.error("更新用户信用值失败");
    }

    @PostMapping("/bizRegion")
    public SimpleResponse addBizRegion(@RequestParam String token,
                                       @RequestParam String name) throws ServiceException{
        System.out.println("POST./api/siteMarket/bizRegion?token=" + token + "&name=" + name);
        boolean result = siteMarketService.addBizRegion(token, name);
        return result ? SimpleResponse.ok("新建商圈成功") :
                SimpleResponse.error("新建商圈失败");
    }

    @GetMapping("/situation")
    public SimpleResponse siteSituation(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/siteMarket/situation?token=" + token);
        return SimpleResponse.ok(siteMarketService.situation(token));
    }

    @GetMapping("/getAllBills")
    public SimpleResponse getAllBills(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/siteMarket/getAllBills?token="+ token);
        return SimpleResponse.ok(siteMarketService.getAllBills(token));
    }

    @GetMapping("/getAllCoupons")
    public SimpleResponse getAllCoupons(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/siteMarket/getAllCoupons?token="+ token);
        return SimpleResponse.ok(siteMarketService.getAllCoupons(token));
    }
}
