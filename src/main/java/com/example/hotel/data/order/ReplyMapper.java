package com.example.hotel.data.order;

import com.example.hotel.po.Order.reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

public interface ReplyMapper extends JpaRepository<reply, Integer> {

    @Query(value = "Select * from reply_new4 where id = :id",nativeQuery = true)
    reply selectById(Integer id);

    @Query(value = "Select * from" +
            "(Select * from reply_new4 where cid = :cid and rid = -1 order by create_time DESC) as c " +
            "limit :page , 10",nativeQuery = true)
    List<reply> selectByCid(Integer cid, Integer page);
    @Query(value = "Select * from" +
            "(Select * from reply_new4 where rid = :rid order by create_time DESC) as c " +
            "limit :page , 10",nativeQuery = true)
    List<reply> selectByRid(Integer rid, Integer page);
    @Query(value = "Select * from" +
            "(Select * from reply_new4 where uid = :uid order by create_time DESC) as c " +
            "limit :page , 10",nativeQuery = true)
    List<reply> selectByUid(Integer uid, Integer page);

    @Query(value = "Select count(id) from reply_new4 where rid = :replyId",
            nativeQuery = true)
    Integer getRepliesNum(Integer replyId);

    @Query(value = "Select count(id) from reply_new4 where cid = :commentId"
            , nativeQuery = true)
    Integer getCommentRepliesNum(Integer commentId);
}
