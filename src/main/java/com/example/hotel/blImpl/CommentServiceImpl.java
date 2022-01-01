package com.example.hotel.blImpl;

import com.example.hotel.bl.CommentService;
import com.example.hotel.bl.ReplyService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.order.CommentMapper;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;

    @Override
    public boolean addComment(comment comment)
            throws ServiceException{
        try{
            commentMapper.save(comment);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加订单回复数据库失败");
        }
        return true;
    }

    @Override
    public comment findById(Integer commentId)
            throws ServiceException{
        comment comment = null;
        try{
            comment = commentMapper.findById(commentId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据主键查找订单回复数据库失败");
        }
        if(comment == null){
            throw new ServiceException("没有找到这个订单回复");
        }
        return comment;
    }

    @Override
    public comment findByUidAndOid(Integer orderId, Integer userId) throws ServiceException {
        comment Comment = null;
        try{
            Comment = commentMapper.findByUidAndOid(orderId, userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据用户和订单查找评价数据库失败");
        }
        return Comment;
    }

    @Override
    public CommentVO findByOrder(Integer orderId)
            throws ServiceException{
        comment Comment = null;
        try{
            Comment = commentMapper.selectByOid(orderId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找订单全部回复数据库失败");
        }
        if(Comment == null){
            throw new ServiceException("没有找到对应订单的评价");
        }
        return commentToVO(Comment);
    }

    @Override
    public List<CommentVO> findAllComments(Integer page)
            throws ServiceException{
        List<comment> comments = null;
        try{
            comments = commentMapper.selectAll(10 * page);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有回复数据库失败");
        }
        return invertComment(comments);
    }

    @Override
    public Double getAvgRate(Integer hotelId)
            throws ServiceException{
        Double result = 0.0;
        try{
            result = commentMapper.getAvgRate(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("获取酒店评分失败");
        }
        return result;
    }

    @Override
    public List<CommentVO> findByHotelId(Integer hotelId, Integer page) throws ServiceException {
        List<comment> comments = null;
        try{
            comments = commentMapper.findByHotelId(hotelId, 10 * page);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店信息数据库错误");
        }
        return invertComment(comments);
    }

    /**
     * 将评价装载成输出的CommentVO
     * @param comments
     * @return
     */
    private List<CommentVO> invertComment(List<comment> comments){
        ArrayList<CommentVO> commentVOS = new ArrayList<>();
        for(comment Comment: comments){
            CommentVO commentVO = null;
            try{
                commentVO = commentToVO(Comment);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }


    /**
     * 将一个comment装载成CommentVO
     * @param Comment
     * @return
     * @throws ServiceException
     */
    private CommentVO commentToVO(comment Comment) throws ServiceException{
        user User = userService.findById(Comment.getUid());
        Integer commentRepliesNum = 0;
        try{
            commentRepliesNum = replyService.getCommentRepliesNum(Comment.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        CommentVO commentVO = new CommentVO(Comment, User.getUrl()
                , User.getUserName(), commentRepliesNum);
        return commentVO;
    }
}
