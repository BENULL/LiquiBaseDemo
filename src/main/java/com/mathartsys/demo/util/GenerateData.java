package com.mathartsys.demo.util;

import com.mathartsys.demo.po.GoodOrder;
import com.mathartsys.demo.po.TestOrder;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


public class GenerateData {

    private Random random=new Random();

    private Date date=new Date(114,1,1);

    public List<GoodOrder> generateDataList(int step){
        date.setTime(date.getTime() + step*6000*2);
        ArrayList<GoodOrder> list=new ArrayList<GoodOrder>();
        for(int i=0;i<50;i++){
            String productId = String.valueOf(random.nextInt(999)+1);
            long price = random.nextInt(50) + 100;
            for(int j=0;j<10;j++) {
                String orderId = UUID.randomUUID().toString();
                long time = random.nextInt(20) * 6000;
                date=new Date(date.getTime() + time);
                list.add(new GoodOrder(orderId, productId, price, date));
            }
        }
        return list;
    }

    public List<TestOrder> generateDataListTestOrder(int step){
        date.setTime(date.getTime() + step*6000*2);
        ArrayList<TestOrder> list=new ArrayList<TestOrder>();
        for(int i=0;i<50;i++){
            String productId = String.valueOf(random.nextInt(999)+1);
            long price = random.nextInt(50) + 100;
            for(int j=0;j<10;j++) {
                int orderId = 0;
                long time = random.nextInt(20) * 6000;
                date=new Date(date.getTime() + time);
                list.add(new TestOrder(orderId, productId, price, date));
            }
        }
        return list;
    }
}
