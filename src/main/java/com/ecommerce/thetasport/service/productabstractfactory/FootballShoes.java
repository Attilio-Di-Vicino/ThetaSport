package com.ecommerce.thetasport.service.productabstractfactory;

public class FootballShoes extends Shoes {

    @Override
    public Product build(){
        super.build();
        this.category = Category.FOOTBALL;
        return this;
    }
}
