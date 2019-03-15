package com.mathartsys.demo.dao;

import com.mathartsys.demo.po.TestOrder;

import java.util.List;

public interface TestOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    TestOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TestOrder record);

    int updateByPrimaryKey(TestOrder record);

    int insertByBatch(List list);

}