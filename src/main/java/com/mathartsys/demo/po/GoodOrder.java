package com.mathartsys.demo.po;

import java.util.Date;

public class GoodOrder {
    private String orderId;

    private String productId;

    private Long price;

    private Date orderTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public GoodOrder(String orderId, String productId, Long price, Date orderTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.orderTime = orderTime;
    }
}