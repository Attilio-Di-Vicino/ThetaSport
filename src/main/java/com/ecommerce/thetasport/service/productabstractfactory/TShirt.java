package com.ecommerce.thetasport.service.productabstractfactory;
public class TShirt extends Product {
    @Override
    public Product build(){
        this.subCategory = SubCategory.TSHIRT;
        return this;
    }
}
