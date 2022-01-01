package com.example.hotel.bl;

import com.example.hotel.Form.Client.ClientVIPForm;
import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.Form.User.ChangeInfoForm;
import com.example.hotel.Form.User.ChangePwdForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.po.Hotel.bizregion;
import com.example.hotel.po.Hotel.hotelserve;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.Order.reply;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.LoginVO;
import com.example.hotel.vo.ReplyVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface UserService {
    /**
     * 新建用户
     * @param user
     * @return
     * @throws ServiceException
     */
    boolean addUser(user user) throws ServiceException;

    /**
     * 更新用户密码
     * @param changePwdForm
     * @return
     * @throws ServiceException
     */
    boolean changePwd(ChangePwdForm changePwdForm) throws ServiceException;

    /**
     * 更新用户信息
     * @param changeInfoForm
     * @return
     * @throws ServiceException
     */
    boolean updateInfo(ChangeInfoForm changeInfoForm) throws ServiceException;

    /**
     * 回复
     * @param replyForm
     * @return
     * @throws ServiceException
     */
    boolean addReply(ReplyForm replyForm) throws ServiceException;

    /**
     * 更新信用值
     * @param userId
     * @param credit
     * @return
     * @throws ServiceException
     */
    boolean updateCredit(Integer userId, Double credit) throws ServiceException;

    /**
     * 更新VIP信息
     * @param clientVIPForm
     * @return
     * @throws ServiceException
     */
    boolean updateVIP(ClientVIPForm clientVIPForm) throws ServiceException;

    /**
     * 获取用户信息
     * @param token
     * @return
     * @throws ServiceException
     */
    user getUserInfo(String token) throws ServiceException;

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     * @throws ServiceException
     */
    user findByEmail(String email) throws ServiceException;

    /**
     * 根据ID获取用户
     * @param userId
     * @return
     * @throws ServiceException
     */
    user findById(Integer userId) throws ServiceException;

    /**
     * 用户登录
     * @param loginForm
     * @return
     * @throws ServiceException
     */
    LoginVO login(LoginForm loginForm) throws ServiceException;

    /**
     * 获取所有的商圈
     * @return
     * @throws ServiceException
     */
    List<bizregion> getBizRegions() throws ServiceException;

    /**
     * 获取订单评价
     * @param token
     * @param orderId
     * @return
     * @throws ServiceException
     */
    CommentVO getOrderComments(String token, Integer orderId) throws ServiceException;

    /**
     * 获取二级回复
     * @param token
     * @param replyId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> getReplyReply(String token, Integer replyId, Integer page) throws ServiceException;

    /**
     * 获取订单回复
     * @param token
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> getMyReply(String token, Integer page) throws ServiceException;

    /**
     * 获取评价的所有一级回复
     * @param commentId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> findByComment(Integer commentId, Integer page) throws ServiceException;

    /**
     * 获取酒店工作人员
     * @return
     * @throws ServiceException
     */
    List<user> getHotelManager() throws ServiceException;

    /**
     * 分页获取酒店的回复信息
     * @param token
     * @param hotelId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<CommentVO> getHotelComments(String token, Integer hotelId, Integer page) throws ServiceException;

    /**
     * 获取所有的服务
     * @param token
     * @return
     * @throws ServiceException
     */
    List<hotelserve> getAllServe(String token) throws ServiceException;
}
