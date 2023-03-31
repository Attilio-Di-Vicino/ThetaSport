package com.ecommerce.thetasport.service.productabstractfactory;
public class Shoes extends Product {
    @Override
    public Product build(){
        this.subCategory = SubCategory.SHOES;
        return this;
    }
}
