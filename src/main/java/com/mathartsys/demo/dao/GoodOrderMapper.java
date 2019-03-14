package com.mathartsys.demo.dao;

import com.mathartsys.demo.po.GoodOrder;

import java.util.List;
import java.util.Map;

public interface GoodOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(GoodOrder record);

    int insertSelective(GoodOrder record);

    GoodOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(GoodOrder record);

    int updateByPrimaryKey(GoodOrder record);

    int insertByBatch(List list);

    Long selectMaxPriceBt(String productId,String start,String end);

    Long selectMinPriceBt(String productId,String start,String end);

    Long selectAvgPriceBt(String productId,String start,String end);

    List<Map<String,Object>> selectMonthPrice(String productId);

    Map<String,Object> selectMaxMonth(String productId);

    Map<String,Object> selectMinMonth(String productId);
}