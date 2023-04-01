package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

public class DirectorCategory {

    public static Product createProduct( @NotNull ProductBean productBean ) {
        Product newProduct;
        switch ( productBean.getCategory() ) {
            case FOOTBALL: newProduct = DirectorSubCategory.createProduct( productBean, new FootballConcreteFactory() );
                break;
            case TENNIS: newProduct = DirectorSubCategory.createProduct( productBean, new TennisConcreteFactory() );
                break;
            default: newProduct = null;
                break;
        }
        return newProduct;
    }
}
