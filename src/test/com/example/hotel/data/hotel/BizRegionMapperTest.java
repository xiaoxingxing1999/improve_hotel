package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.bizregion;
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
public class BizRegionMapperTest {
    @Autowired
    BizRegionMapper bizRegionMapper;

    @Test
    @Transactional
    @Rollback
    public void save(){
        bizregion BizRegion = new bizregion(1,"BizRegion",1, LocalDateTime.now());
        bizRegionMapper.save(BizRegion);
    }

    @Test
    @Transactional
    @Rollback
    public void findByName() {
        Assert.assertNotNull(bizRegionMapper.findByName("老门东"));
    }

    @Test
    @Transactional
    @Rollback
    public void findAllNames() {
        Assert.assertNotNull(bizRegionMapper.findAllNames());
    }
}