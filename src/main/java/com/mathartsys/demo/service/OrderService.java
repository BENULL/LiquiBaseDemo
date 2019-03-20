package com.mathartsys.demo.service;

import com.mathartsys.demo.common.ServerResponse;
import com.mathartsys.demo.po.GoodOrder;

import java.util.Date;
import java.util.List;

public interface OrderService {
    ServerResponse generateData(List list);

    ServerResponse findMaxPriceBt(String productId, String start, String end);

    ServerResponse findMinPriceBt(String productId, String start, String end);

    ServerResponse findAvgPriceBt(String productId, String start, String end);

    ServerResponse findMaxMonth(String productId);

    ServerResponse findMinMonth(String productId);

    ServerResponse findMonthPrice(String productId);

    void insertDataByBatchMode(List<GoodOrder> data);


    ServerResponse findMonthBounce(String productId);
}
