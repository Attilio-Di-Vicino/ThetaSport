package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

public class DirectorPlus {

    public static Product createProduct( @NotNull ProductBean productBean ) {
        ProductCreator creator = productBean.getCategory().getCreator();
        if ( creator == null ) {
            throw new IllegalArgumentException( "Invalid product category" );
        }
        return creator.createProduct( productBean );
    }
}
