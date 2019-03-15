package com.mathartsys.demo;

import com.mathartsys.demo.dao.GoodOrderMapper;
import com.mathartsys.demo.dao.TestOrderMapper;
import com.mathartsys.demo.po.GoodOrder;
import com.mathartsys.demo.util.GenerateData;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenerateUtilTest {

    @Autowired
    private GoodOrderMapper goodorderMapper;

    @Autowired
    private TestOrderMapper testOrderMapper;

    @Test
    public void testGenerateData(){
        GenerateData generateData=new GenerateData();
        for(int i=0;i<2000;i++){
            List data = generateData.generateDataList(i);
            goodorderMapper.insertByBatch(data);
        }
    }

    @Test
    public void testGenerateDataAutoincrement(){
        GenerateData generateData=new GenerateData();
        for(int i=0;i<2000;i++){
            List data = generateData.generateDataListTestOrder(i);
            testOrderMapper.insertByBatch(data);
        }
    }

    @Test
    public void testJDBC(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        GenerateData generateData=new GenerateData();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC&allowMultiQueries=true&useSSL=false",
                    "root", "lsbssg");
            String sql = "INSERT INTO goodorder (order_id,product_id,price,order_time) VALUES(?,?,?,?)";
            conn.setAutoCommit(false);
            pstm = conn.prepareStatement(sql);
            for(int j=0;j<2000;j++){
                List<GoodOrder> data = generateData.generateDataList(j);
                for (GoodOrder g : data) {
                    pstm.setString(1,g.getOrderId() );
                    pstm.setString(2, g.getProductId());
                    pstm.setLong(3,g.getPrice());
                    pstm.setDate(4, (Date) g.getOrderTime());
                    pstm.addBatch();
                }
                pstm.executeBatch();
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void testThread(){
        int nThreads = 4;
        GenerateData generateData=new GenerateData();
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
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
