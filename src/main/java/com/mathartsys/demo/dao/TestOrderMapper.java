package com.mathartsys.demo.dao;

import com.mathartsys.demo.po.TestOrder;

import java.util.List;
import java.util.Map;

public interface TestOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    TestOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TestOrder record);

    int updateByPrimaryKey(TestOrder record);

    int insertByBatch(List list);

    List<String> selectProductYear(String productId);

    List<Map<String,Long>> selectMonthSalesByYear(String productId, String y);
}