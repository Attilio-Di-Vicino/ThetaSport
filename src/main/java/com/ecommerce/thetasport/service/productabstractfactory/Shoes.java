package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitor;
import org.jetbrains.annotations.NotNull;

public class Shoes extends Product {

    @Override
    public Product build(){
        this.subCategory = SubCategory.SHOES;
        return this;
    }

    @Override
    public double accept(@NotNull ShoppingCartVisitor visitor){
        return visitor.visit(this);
    }
}
