package com.mathartsys.demo;

import com.mathartsys.demo.dao.GoodOrderMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodOrderMapperTest {

    @Autowired
    private GoodOrderMapper goodOrderMapper;

    @Test
    public void testFindMaxPriceBt(){
        long res = goodOrderMapper.selectMaxPriceBt("23","2014-01-01","2015-01-01");
        Assert.assertEquals(149,res);
    }

    @Test
    public void testFindMaxMonth(){
        Map<String,Object> res  = goodOrderMapper.selectMaxMonth("23");
        Assert.assertEquals(2,res.get("m"));
    }
}
