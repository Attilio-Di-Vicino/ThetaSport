package com.ecommerce.thetasport.service.productABF;

public class TennisConcreteFactory implements TennisShoesAbstractFactory, TennisTShirtAbstractFactory{
    @Override
    public AbstractProductTennis createTennisShoes(){
        return new TennisShoes();
    }

    @Override
    public AbstractProductTennis createTennisTShirt(){
        return new TennisTShirt();
    }
}
