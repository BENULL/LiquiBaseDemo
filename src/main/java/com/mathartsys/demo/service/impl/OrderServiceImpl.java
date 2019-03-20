package com.mathartsys.demo.service.impl;

import com.mathartsys.demo.common.ServerResponse;
import com.mathartsys.demo.dao.GoodOrderMapper;
import com.mathartsys.demo.dao.TestOrderMapper;
import com.mathartsys.demo.po.GoodOrder;
import com.mathartsys.demo.service.OrderService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodOrderMapper goodorderMapper;

    @Autowired
    private TestOrderMapper testOrderMapper;

    @Override
    public ServerResponse generateData(List list) {
        if(goodorderMapper.insertByBatch(list)>0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse findMaxPriceBt(String productId, String start, String end) {
        long res=goodorderMapper.selectMaxPriceBt(productId,start,end);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse findMinPriceBt(String productId, String start, String end) {
        long res=goodorderMapper.selectMinPriceBt(productId,start,end);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse findAvgPriceBt(String productId, String start, String end)  {
        long res=goodorderMapper.selectAvgPriceBt(productId,start,end);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse findMaxMonth(String productId) {
       Map<String,Object> res = goodorderMapper.selectMaxMonth(productId);
       return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse findMinMonth(String productId) {
        Map<String,Object> res = goodorderMapper.selectMinMonth(productId);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public ServerResponse findMonthPrice(String productId) {
        List<Map<String,Object>> res = goodorderMapper.selectMonthPrice(productId);
        return ServerResponse.createBySuccess(res);
    }

    @Override
    public void insertDataByBatchMode(List<GoodOrder> data) {
    }

    @Override
    public ServerResponse findMonthBounce(String productId) {
        Map<String,List<Map<String,Long>>> data = new HashMap<>();
        //查询商品有几年
        List<String> years = testOrderMapper.selectProductYear(productId);
        //查询每年12个月的销量
        for(String y : years){
            List<Map<String,Long>> monthSales = testOrderMapper.selectMonthSalesByYear(productId,y);
            data.put(y,monthSales);
        }
        return ServerResponse.createBySuccess(data);
    }

}
