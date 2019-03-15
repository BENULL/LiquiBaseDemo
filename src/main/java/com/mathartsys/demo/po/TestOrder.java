package com.mathartsys.demo.po;

import java.math.BigDecimal;
import java.util.Date;

public class TestOrder {
    private Integer orderId;

    private String productId;

    private BigDecimal price;

    private Date orderTime;

    public TestOrder(Integer orderId, String productId, BigDecimal price, Date orderTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.orderTime = orderTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}