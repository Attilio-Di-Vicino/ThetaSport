package com.ecommerce.thetasport.service.productFM;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productABF.HelperProduct;
import com.ecommerce.thetasport.service.productABF.Product;
import org.jetbrains.annotations.NotNull;

public class Director {
    public static Product createProduct(@NotNull ProductBean productBean) {
        // Viene istanziata la factory specifica per i prodotti di tipo "Football"
        ConcreteFactory concreteFactory = new ConcreteFactory(productBean.getCategory());
        Product newProduct;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch (productBean.getSubCategory()){
            case SHOES: newProduct = concreteFactory.createShoes();
                break;
            case TSHIRT: newProduct = concreteFactory.createTShirt();
                break;
            case SHORTS: newProduct = concreteFactory.createShorts();
                break;
            case ACCESSORIES: newProduct = concreteFactory.createAccessories();
                break;
            default: newProduct = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct(newProduct,productBean);
        return newProduct;
    }
}
