package com.example.hotel.blImpl;

import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.bl.CommentService;
import com.example.hotel.bl.ReplyService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.order.ReplyMapper;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.Order.reply;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
import com.example.hotel.vo.ReplyVO;
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
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @Override
    public boolean addReply(ReplyForm replyForm)
            throws ServiceException{
        user user = UserHelper.getByToken(replyForm.getToken());
        commentService.findById(replyForm.getCid());
        reply reply = new reply(user.getId(), user.getUserName(), replyForm);
        try{
            replyMapper.save(reply);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("新建Reply数据库失败");
        }
        return true;
    }

    @Override
    public reply findById(Integer replyId)
            throws ServiceException{
        reply reply = null;
        try{
            reply = replyMapper.selectById(replyId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据主键查找Reply数据库失败");
        }
        if(reply == null){
            throw new ServiceException("没有找到这个回复");
        }
        return reply;
    }

    @Override
    public List<ReplyVO> findByUser(Integer userId, Integer page)
            throws ServiceException{
        List<reply> replies = null;
        try{
            replies = replyMapper.selectByUid(userId,10 * page);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据用户查找回复数据库失败");
        }
        return toReplyVO(replies);
    }

    @Override
    public List<ReplyVO> findByComment(Integer commentId, Integer page)
            throws ServiceException{
        //检查对应Comment是否存在
        commentService.findById(commentId);
        List<reply> replies = null;
        try{
            replies = replyMapper.selectByCid(commentId,10 * page);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据Comment查找Reply数据库失败");
        }
        return toReplyVO(replies);
    }

    @Override
    public List<ReplyVO> findByReply(Integer replyId, Integer page)
            throws ServiceException{
        //检查一级评论是否真的存在
        findById(replyId);
        List<reply> replies = null;
        try{
            replies = replyMapper.selectByRid(replyId,10 * page);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找二级评论数据库失败");
        }
        return toReplyVO(replies);
    }

    /**
     * 返回订单评价回复的总数
     * @param commentId
     * @return
     * @throws ServiceException
     */
    @Override
    public Integer getCommentRepliesNum(Integer commentId) throws ServiceException{
        Integer repliesNum = 0;
        try{
            repliesNum = replyMapper.getCommentRepliesNum(commentId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查询订单评价的回复总数失败");
        }
        return repliesNum;
    }

    /**
     * 获取所有的回复输出格式
     * @param replies
     * @return
     */
    private List<ReplyVO> toReplyVO(List<reply> replies){
        if(replies == null){
            return null;
        }
        List<ReplyVO> replyVOS = new ArrayList<>();
        for(reply Reply: replies){
            ReplyVO replyVO = null;
            try{
                replyVO = toVO(Reply);
            }catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            replyVOS.add(replyVO);
        }
        return replyVOS;
    }

    /**
     * Reply -> ReplyVO
     * @param Reply
     * @return
     * @throws ServiceException
     */
    private ReplyVO toVO(reply Reply) throws ServiceException{
        user User = userService.findById(Reply.getUid());
        Reply.setUsername(User.getUserName());
        Integer repliesNum = 0;
        try{
            repliesNum = getRepliesNum(Reply.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        comment Comment = commentService.findById(Reply.getCid());
        ReplyVO replyVO = new ReplyVO(Reply, User.getUrl(),repliesNum,Comment.getContent());
        return replyVO;
    }

    /**
     * 获取评论的数量
     * @param replyId
     * @return
     * @throws ServiceException
     */
    private Integer getRepliesNum(Integer replyId) throws ServiceException{
        Integer repliesNum = 0;
        try{
            repliesNum = replyMapper.getRepliesNum(replyId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查询一级帖子的回复失败");
        }
        return repliesNum;
    }
}
