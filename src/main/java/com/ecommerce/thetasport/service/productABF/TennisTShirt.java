package com.ecommerce.thetasport.service.productABF;

public class TennisTShirt extends AbstractProductTennis{
    @Override
    public AbstractProductTennis build(){
        super.build();
        this.setSubCategory(SubCategory.TSHIRT);
        return this;
    }
}
