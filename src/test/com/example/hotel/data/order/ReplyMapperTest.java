package com.example.hotel.data.order;

import com.example.hotel.po.Order.reply;
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
public class ReplyMapperTest {
    @Autowired
    ReplyMapper replyMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        reply Reply = new reply(1,1,1,1,"测试用户","测试回复", LocalDateTime.now());
        replyMapper.save(Reply);
        Reply = replyMapper.findById(1).orElse(null);
        Assert.assertNotNull(Reply);
    }

    @Test
    @Transactional
    @Rollback
    public void selectById() {
        Assert.assertNotNull(replyMapper.selectById(1));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByCid() {
        Assert.assertNotNull(replyMapper.selectByCid(1,0));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByRid() {
        Assert.assertNotNull(replyMapper.selectByRid(1,0));
    }

    @Test
    @Transactional
    @Rollback
    public void selectByUid() {
        Assert.assertNotNull(replyMapper.selectByUid(1,0));
    }

    @Test
    @Transactional
    @Rollback
    public void getRepliesNum() {
        Assert.assertNotNull(replyMapper.getRepliesNum(1));
    }

    @Test
    @Transactional
    @Rollback
    public void getCommentRepliesNum() {
        Assert.assertNotNull(replyMapper.getCommentRepliesNum(1));
    }
}