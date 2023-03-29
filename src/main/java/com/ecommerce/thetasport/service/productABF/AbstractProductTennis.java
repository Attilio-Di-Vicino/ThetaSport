package com.ecommerce.thetasport.service.productABF;

public abstract class AbstractProductTennis extends AbstractProduct{
    @Override
    public AbstractProduct build(){
        this.setCategory(Category.TENNIS);
        return this;
    }
}
