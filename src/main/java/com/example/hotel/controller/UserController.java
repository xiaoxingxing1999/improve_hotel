package com.example.hotel.controller;

import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.Form.User.ChangeInfoForm;
import com.example.hotel.Form.User.ChangePwdForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.UserService;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.Response.SimpleResponse;
import com.example.hotel.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public SimpleResponse login(@RequestBody LoginForm loginForm) throws ServiceException {
        System.out.println("POST./api/user/login:" + loginForm.toString());
        LoginVO loginVO = userService.login(loginForm);
        return loginVO == null ? SimpleResponse.error("密码或者用户名错误。"):
                SimpleResponse.ok(loginVO);
    }

    @PostMapping("/changePwd")
    public SimpleResponse changePwd(@RequestBody ChangePwdForm changePwdForm) throws ServiceException{
        System.out.println("POST./api/user/changePwd:" + changePwdForm.toString());
        boolean result = userService.changePwd(changePwdForm);
        return result ? SimpleResponse.ok("修改密码成功，请重新登录。"):
                SimpleResponse.error("修改密码失败。");
    }

    @PostMapping("/update")
    public SimpleResponse updateUserInfo(@RequestBody ChangeInfoForm changeInfoForm) throws ServiceException{
        System.out.println("POST./api/user/update:" + changeInfoForm.toString());
        boolean result = userService.updateInfo(changeInfoForm);
        return result ? SimpleResponse.ok("更新用户信息成功。"):
                SimpleResponse.error("更新用户信息失败。");
    }

    @PostMapping("/add")
    public SimpleResponse addReply(@RequestBody ReplyForm replyForm) throws ServiceException{
        System.out.println("POST./api/user/add:" + replyForm.toString());
        boolean result = userService.addReply(replyForm);
        return result ? SimpleResponse.ok("发表回复成功") :
                SimpleResponse.error("发表回复失败");
    }

    @GetMapping("/getAllReplies")
    public SimpleResponse getAllReplies(@RequestParam Integer commentId,
                                        @RequestParam Integer page) throws ServiceException{
        System.out.println("GET./api/user/getAllReplies?commentId=" + commentId + "&page=" + page);
        return SimpleResponse.ok(userService.findByComment(commentId,page));
    }

    @GetMapping("/getUserInfo")
    public SimpleResponse getUserInfo(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/user/getUserInfo?token=" + token);
        return SimpleResponse.ok(userService.getUserInfo(token));
    }

    @GetMapping("/getBizRegions")
    public SimpleResponse getBizRegions() throws ServiceException{
        System.out.println("GET./api/user/getBizRegions");
        return SimpleResponse.ok(userService.getBizRegions());
    }

    @GetMapping("/getOrderComments")
    public SimpleResponse getOrderComments(@RequestParam String token,
                                           @RequestParam Integer orderId) throws ServiceException{
        System.out.println("GET./api/user/getOrderComments?token=" + token + "&orderId=" + orderId);
        return SimpleResponse.ok(userService.getOrderComments(token, orderId));
    }

    @GetMapping("/getHotelComments")
    public SimpleResponse getHotelComments(@RequestParam String token,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Integer page) throws ServiceException{
        System.out.println("GET./api/user/getHotelComments?token=" + token + "&hotelId=" + hotelId + "&page=" + page);
        return SimpleResponse.ok(userService.getHotelComments(token, hotelId, page));
    }


    @GetMapping("/getReplyReply")
    public SimpleResponse getReplyReply(@RequestParam String token,
                                        @RequestParam Integer replyId,
                                        @RequestParam Integer page) throws ServiceException{
        System.out.println("GET./api/user/getReplyReply?token=" + token + "&replyId=" + replyId + "&page=" + page);
        return SimpleResponse.ok(userService.getReplyReply(token, replyId, page));
    }

    @GetMapping("/getMyReply")
    public SimpleResponse getMyReply(@RequestParam String token,
                                     @RequestParam Integer page) throws ServiceException{
        System.out.println("GET./api/user/getMyReply?token=" + token +"&page=" + page);
        return SimpleResponse.ok(userService.getMyReply(token, page));
    }

    @GetMapping("/getAllServe")
    public SimpleResponse getAllServe(@RequestParam String token) throws ServiceException{
        System.out.println("GET./api/hotelManager/getAllServe?token=" + token);
        return SimpleResponse.ok(userService.getAllServe(token));
    }

}
