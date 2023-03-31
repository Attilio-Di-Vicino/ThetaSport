package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitor;
import org.jetbrains.annotations.NotNull;

public class TShirt extends Product {
    @Override
    public Product build(){
        this.subCategory = SubCategory.TSHIRT;
        return this;
    }

    @Override
    public double accept(@NotNull ShoppingCartVisitor visitor){
        return visitor.visit(this);
    }
}
