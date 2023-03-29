package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

public class DirectorTennis {

    public static AbstractProductTennis createProduct(@NotNull ProductBean productBean){
        TennisConcreteFactory tennisConcreteFactory = new TennisConcreteFactory();
        AbstractProductTennis newProductTennis;
        switch (productBean.getSubCategory()){
            case SHOES: newProductTennis = tennisConcreteFactory.createTennisShoes();
                break;
            case TSHIRT: newProductTennis = tennisConcreteFactory.createTennisTShirt();
                break;
            default: newProductTennis = null;
                break;
        }
        HelperProduct.initPorduct(newProductTennis,productBean);
        return newProductTennis;
    }
}
