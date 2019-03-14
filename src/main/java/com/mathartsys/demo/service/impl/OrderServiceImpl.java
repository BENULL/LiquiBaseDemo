package com.mathartsys.demo.service.impl;

import com.mathartsys.demo.common.ServerResponse;
import com.mathartsys.demo.dao.GoodOrderMapper;
import com.mathartsys.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodOrderMapper goodorderMapper;

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

}
