package com.ecommerce.thetasport.service.productABF;

public class FootballConcreteFactory implements FootballShoesAbstractFactory, FootballTShirtAbstractFactory{
    @Override
    public AbstractProductFootball createFootballShoes(){
        return new FootballShoes();
    }

    @Override
    public AbstractProductFootball createFootballTShirt(){
        return new FootballTShirt();
    }
}
