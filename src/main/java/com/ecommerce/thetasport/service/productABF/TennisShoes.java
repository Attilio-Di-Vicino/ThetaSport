package com.ecommerce.thetasport.service.productABF;

public class TennisShoes extends AbstractProductTennis{
    @Override
    public AbstractProductTennis build(){
        super.build();
        this.setSubCategory(SubCategory.SHOES);
        return this;
    }
}
