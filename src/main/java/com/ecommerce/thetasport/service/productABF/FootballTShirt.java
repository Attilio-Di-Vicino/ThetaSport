package com.ecommerce.thetasport.service.productABF;

public class FootballTShirt extends AbstractProductFootball{
    @Override
    public AbstractProductFootball build(){
        super.build();
        this.setSubCategory(SubCategory.TSHIRT);
        return this;
    }
}
