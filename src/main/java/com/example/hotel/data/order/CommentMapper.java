package com.example.hotel.data.order;

import com.example.hotel.po.Order.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @Author stormbroken
 * Create by 2020/05/19
 * @Version 1.0
 **/

public interface CommentMapper extends JpaRepository<comment,Integer> {

    @Query(value = "Select * from comment_new4 where oid = :oid order by create_time DESC " +
            "limit 1", nativeQuery = true)
    comment selectByOid(Integer oid);

    @Query(value = "Select * from" +
            "(Select * from comment_new4 order by create_time DESC) as c " +
            "limit :page , 10", nativeQuery = true)
    List<comment> selectAll(Integer page);

    @Query(value = "Select avg(rate) from comment_new4 , hotelorder_new4 " +
            "where comment_new4.oid = hotelorder_new4.id and hotelorder_new4.hotel_id = :hotelId",nativeQuery = true)
    Double getAvgRate(Integer hotelId);

    @Query(value = "Select * from comment_new4 " +
            "where oid = :orderId and uid = :userId limit 1", nativeQuery = true)
    comment findByUidAndOid(Integer orderId, Integer userId);

    @Query(value = "Select * from comment_new4, hotelorder_new4 " +
            "where comment_new4.oid = hotelorder_new4.id and hotelorder_new4.hotel_id = :hotelId " +
            "order by comment_new4.create_time DESC limit :page, 10", nativeQuery = true)
    List<comment> findByHotelId(Integer hotelId, Integer page);
}
