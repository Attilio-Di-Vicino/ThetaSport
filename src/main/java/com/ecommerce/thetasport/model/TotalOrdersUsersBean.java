package com.ecommerce.thetasport.model;

import java.beans.JavaBean;
import java.io.Serializable;

@JavaBean
public class TotalOrdersUsersBean implements Serializable {
    private int orderId;
    private String name;
    private String email;
    private String date;
    private double total;
    private int quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId( int orderId ) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal( double total ) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }
}
