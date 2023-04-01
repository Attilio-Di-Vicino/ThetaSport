package com.ecommerce.thetasport.model;

import java.beans.JavaBean;
import java.io.Serializable;

@JavaBean
public class OrderBean implements Serializable {

    private int orderId;
    private String orderDate;
    private String email;
    private double grossProfit;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId( int orderId ) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( String orderDate ) {
        this.orderDate = orderDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public double getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit( double grossProfit ) {
        this.grossProfit = grossProfit;
    }
}
