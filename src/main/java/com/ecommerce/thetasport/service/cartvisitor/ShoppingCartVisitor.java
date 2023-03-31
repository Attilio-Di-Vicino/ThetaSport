package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.service.productabstractfactory.Shoes;
import com.ecommerce.thetasport.service.productabstractfactory.TShirt;

public interface ShoppingCartVisitor {
    double visit(Shoes product);
    double visit(TShirt product);
}
