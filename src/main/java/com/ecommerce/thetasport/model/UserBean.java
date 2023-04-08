package com.ecommerce.thetasport.model;

import com.ecommerce.thetasport.service.cartvisitor.Cart;

import java.beans.JavaBean;
import java.io.Serializable;

@JavaBean
public class UserBean implements Serializable {
    private String name;
    private String email;
    private String password;

    private Cart myCart;

    public UserBean() {
        this.name = "";
        this.email = "";
        this.password = "";
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

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Cart getMyCart() {
        return myCart;
    }

    public void setMyCart(Cart myCart) {
        this.myCart = myCart;
    }
}