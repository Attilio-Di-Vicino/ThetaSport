package com.ecommerce.thetasport.service.cartvisitor;

@FunctionalInterface
public interface ItemElement {
    double accept(ShoppingCartVisitor visitor);
}
