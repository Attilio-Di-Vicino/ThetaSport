package com.ecommerce.thetasport.service.productABF;

public class FootballShoes extends AbstractProductFootball{
    @Override
    public AbstractProductFootball build(){
        super.build();
        this.setSubCategory(SubCategory.SHOES);
        return this;
    }
}
