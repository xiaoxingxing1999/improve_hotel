package com.example.hotel.bl;

import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.po.Order.reply;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.ReplyVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface ReplyService {
    /**
     * 回复评价
     * @param replyForm
     * @return
     * @throws ServiceException
     */
    boolean addReply(ReplyForm replyForm) throws ServiceException;

    /**
     * 根据ID查找回复
     * @param replyId
     * @return
     * @throws ServiceException
     */
    reply findById(Integer replyId) throws ServiceException;

    /**
     * 查找用户的所有回复
     * @param userId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> findByUser(Integer userId, Integer page) throws ServiceException;

    /**
     * 查找所有的回复
     * @param commentId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> findByComment(Integer commentId, Integer page) throws ServiceException;

    /**
     * 查找二级回复
     * @param replyId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<ReplyVO> findByReply(Integer replyId, Integer page) throws ServiceException;//查看二级评论

    /**
     * 查找评价的回复数
     * @param commentId
     * @return
     * @throws ServiceException
     */
    Integer getCommentRepliesNum(Integer commentId) throws ServiceException;
}
