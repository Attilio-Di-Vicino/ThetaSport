package com.ecommerce.thetasport.service.productabstractfactory;

public class TennisShoes extends Shoes {

    @Override
    public Product build(){
        super.build();
        this.category = Category.TENNIS;
        return this;
    }
}
