package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class ShoesSubCategoryCreator implements SubCategoryCreator {
    public Product createProduct( AbstractFactoryShoes concreteFactory ) {
        return concreteFactory.createShoes();
    }
}
