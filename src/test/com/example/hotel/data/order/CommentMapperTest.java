package com.example.hotel.data.order;

import com.example.hotel.po.Order.comment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest {
    @Autowired
    CommentMapper commentMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        comment Comment = new comment(1,1,1,""
                ,"测试", LocalDateTime.now(),0.0);
        commentMapper.save(Comment);
    }

    @Test
    @Transactional
    @Rollback
    public void selectByOid() {
        Assert.assertNotNull(commentMapper.selectByOid(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectAll() {
        Assert.assertNotNull(commentMapper.selectAll(0));
    }

    @Test
    @Transactional
    @Rollback
    public void getAvgRate() {
        Assert.assertNotNull(commentMapper.getAvgRate(1));
    }

    @Test
    @Transactional
    @Rollback
    public void findByUidAndOid() {
        Assert.assertNotNull(commentMapper.findByUidAndOid(1,1));
    }

    @Test
    @Transactional
    @Rollback
    public void findByHotelId() {
        Assert.assertNotNull(commentMapper.findByHotelId(1, 0));
    }
}