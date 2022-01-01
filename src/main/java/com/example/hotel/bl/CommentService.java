package com.example.hotel.bl;

import com.example.hotel.po.Order.comment;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.CommentVO;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

public interface CommentService {
    /**
     * 新建订单评价
     * @param comment
     * @return
     * @throws ServiceException
     */
    boolean addComment(comment comment) throws ServiceException;

    /**
     * 根据ID查找订单评价
     * @param commentId
     * @return
     * @throws ServiceException
     */
    comment findById(Integer commentId) throws ServiceException;

    /**
     * 根据用户和订单查找订单评价
     * @param orderId
     * @param userId
     * @return
     * @throws ServiceException
     */
    comment findByUidAndOid(Integer orderId, Integer userId) throws ServiceException;

    /**
     * 根据订单ID查找订单评价
     * @param orderId
     * @return
     * @throws ServiceException
     */
    CommentVO findByOrder(Integer orderId) throws ServiceException;

    /**
     * 分页查找订单评价
     * @param page
     * @return
     * @throws ServiceException
     */
    List<CommentVO> findAllComments(Integer page) throws ServiceException;

    /**
     * 获取一个酒店ID的平均评分
     * @param hotelId
     * @return
     * @throws ServiceException
     */
    Double getAvgRate(Integer hotelId) throws ServiceException;

    /**
     * 根据酒店ID查找所有相关的评价
     * @param hotelId
     * @param page
     * @return
     * @throws ServiceException
     */
    List<CommentVO> findByHotelId(Integer hotelId, Integer page) throws ServiceException;
}
