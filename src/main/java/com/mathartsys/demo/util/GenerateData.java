package com.mathartsys.demo.util;

import com.mathartsys.demo.po.GoodOrder;

import java.util.*;

public class GenerateData {

    private Random random=new Random();

    private Date date=new Date(114,1,1);

    public List<GoodOrder> generateDataList(int step){
        date.setTime(date.getTime() + step*6000*2);
        ArrayList<GoodOrder> list=new ArrayList<GoodOrder>();
        for(int i=0;i<10;i++){
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
}
