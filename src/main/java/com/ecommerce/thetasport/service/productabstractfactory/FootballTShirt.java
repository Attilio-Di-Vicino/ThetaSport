package com.ecommerce.thetasport.service.productabstractfactory;

public class FootballTShirt extends TShirt {

    @Override
    public Product build(){
        super.build();
        this.category = Category.FOOTBALL;
        return this;
    }
}
