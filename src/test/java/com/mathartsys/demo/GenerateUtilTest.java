package com.mathartsys.demo;

import com.mathartsys.demo.dao.GoodOrderMapper;
import com.mathartsys.demo.po.GoodOrder;
import com.mathartsys.demo.util.GenerateData;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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
        for(int i=0;i<10000;i++){
            List data = generateData.generateDataList(i);
            goodorderMapper.insertByBatch(data);
        }
    }

    @Test
    public void testThread(){
        //int nThreads = 4;
        GenerateData generateData=new GenerateData();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            List data = generateData.generateDataList(i);
            executorService.execute(() -> goodorderMapper.insertByBatch(data));
        }
    }

    @Test
    //can not run
    public void testUseBatchMode() throws Exception {
        //创建连接池
        DataSource dataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/shopping", "root", "lsbssg");
        //事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //创建配置
        Configuration configuration = new Configuration();
        //加入资源（Mapper接口）
        configuration.addMapper(GoodOrderMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            GoodOrderMapper mapper = session.getMapper(GoodOrderMapper.class);
            GenerateData generateData=new GenerateData();
            for (int i = 0; i < 1; i++) {
                List<GoodOrder> records = generateData.generateDataList(i);
                mapper.insertByBatch(records);
            }
            session.commit();
        } finally {
            session.close();
        }
    }
}
