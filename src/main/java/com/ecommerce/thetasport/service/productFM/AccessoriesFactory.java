package com.ecommerce.thetasport.service.productFM;
import com.ecommerce.thetasport.service.productABF.Product;
@FunctionalInterface
public interface AccessoriesFactory {
    Product createAccessories();
}
