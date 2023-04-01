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

    /**
     * viene utilizzato un oggeto di tipo {@link StringBuilder}
     * che ci permette di construire output tramite il pattern builder
     * @return stringa elenco degli attributi del singolo prodotto
     */
    @SuppressWarnings("'StringBuilder out' can be replaced with 'String'")
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
            out.append( " Code: " ).append( this.code ).append( "\n" )
                    .append(" Name: ").append( this.name ).append( "\n" )
                    .append( " Description: " ).append( this.description ).append( "\n" )
                    .append( " Stock: " ).append( this.stock ).append( "\n" )
                    .append( " Price: " ).append( this.price ).append( "\n" )
                    .append( " Category: " ).append( this.category ).append( "\n" )
                    .append( " SubCategory: " ).append( this.subCategory ).append( "\n" )
                    .append( " Image: " ).append( this.image ).append( "\n" );
        return out.toString();
    }
}
