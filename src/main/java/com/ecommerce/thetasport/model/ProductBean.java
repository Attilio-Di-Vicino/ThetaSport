package com.ecommerce.thetasport.model;

import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;

import java.beans.JavaBean;
import java.io.Serializable;

@JavaBean
public class ProductBean implements Serializable {
    private int code;
    private String name;
    private String description;
    private int stock;
    private double price;
    private Category category;
    private SubCategory subCategory;
    private String image;
    public ProductBean(){
        this.code = - 1;
        this.name = "";
        this.description = "";
        this.stock = 0;
        this.price = - 1;
        this.category = null;
        this.subCategory = null;
        this.image = "";
    }

    public ProductBean getProductBean(){return this;}
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image = image;
    }
    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
