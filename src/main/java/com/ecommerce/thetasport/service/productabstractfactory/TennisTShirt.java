package com.ecommerce.thetasport.service.productabstractfactory;

public class TennisTShirt extends TShirt {

    @Override
    public Product build() {
        super.build();
        this.category = Category.TENNIS;
        return this;
    }
}
