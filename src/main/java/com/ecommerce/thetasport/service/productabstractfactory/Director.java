package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class Director {
    private static ProductCreator creator;

    public static Product createProduct( ProductCreator categoryProductCreator, ProductBean productBean ) {
        creator = categoryProductCreator;
        if ( creator == null ) {
            throw new IllegalArgumentException( "Invalid product category" );
        }
        return creator.createProduct( productBean );
    }
}
