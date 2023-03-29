package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

public class DirectorFootball {

    public static AbstractProductFootball createProduct(@NotNull ProductBean productBean){
        FootballConcreteFactory footballConcreteFactory = new FootballConcreteFactory();
        AbstractProductFootball newProductFootball;
        switch (productBean.getSubCategory()){
            case SHOES: newProductFootball = footballConcreteFactory.createFootballShoes();
                break;
            case TSHIRT: newProductFootball = footballConcreteFactory.createFootballTShirt();
                break;
            default: newProductFootball = null;
                break;
        }
        HelperProduct.initPorduct(newProductFootball,productBean);
        return newProductFootball;
    }
}
