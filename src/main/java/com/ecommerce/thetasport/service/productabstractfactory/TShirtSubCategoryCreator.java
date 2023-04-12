package com.ecommerce.thetasport.service.productabstractfactory;

public class TShirtSubCategoryCreator implements SubCategoryCreator {
    public Product createProduct( AbstractFactoryTShirt concreteFactory ) {
        return concreteFactory.createTShirt();
    }
}
