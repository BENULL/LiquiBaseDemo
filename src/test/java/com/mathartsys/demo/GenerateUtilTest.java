package com.mathartsys.demo;

import com.mathartsys.demo.dao.GoodOrderMapper;
import com.mathartsys.demo.util.GenerateData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateUtilTest {

    @Autowired
    private GoodOrderMapper goodorderMapper;

    @Test
    public void testGenerateData(){
        GenerateData generateData=new GenerateData();
        for(int i=0;i<1000;i++){
            List data = generateData.generateDataList(i);
            goodorderMapper.insertByBatch(data);
        }
    }

    @Test
    public void testThread(){
        //int nThreads = 4;
        GenerateData generateData=new GenerateData();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            List data = generateData.generateDataList(i);
            executorService.execute(() -> goodorderMapper.insertByBatch(data));
        }
    }
}
