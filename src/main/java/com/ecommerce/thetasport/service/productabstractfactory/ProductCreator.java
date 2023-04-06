package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

@FunctionalInterface
public interface ProductCreator {
    Product createProduct( ProductBean productBean );
}
