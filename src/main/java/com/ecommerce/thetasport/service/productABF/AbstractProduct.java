package com.ecommerce.thetasport.service.productABF;

public abstract class AbstractProduct {
    private int code;
    private String name;
    private String description;
    private int stock;
    private double price;
    private Category category;
    private SubCategory subCategory;
    private String image;

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStock() {
        return this.stock;
    }

    public double getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getImage(){
        return this.image;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setCode(int code){this.code = code;}

    public void setCategory(Category category){this.category = category;}

    public AbstractProduct setName(String name){
        this.name = name;
        return this;
    }

    public AbstractProduct setDescription(String description){
        this.description = description;
        return this;
    }

    public AbstractProduct setStock(int stock){
        this.stock = stock;
        return this;
    }

    public AbstractProduct setPrice(double price){
        this.price = price;
        return this;
    }

    public AbstractProduct setImage(String image){
        this.image = image;
        return this;
    }

    public AbstractProduct setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public abstract AbstractProduct build();
}
