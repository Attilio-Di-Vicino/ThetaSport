package com.ecommerce.thetasport.service.productABF;

public abstract class AbstractProductFootball extends AbstractProduct {
    @Override
    public AbstractProduct build(){
        this.setCategory(Category.FOOTBALL);
        return this;
    }
}
