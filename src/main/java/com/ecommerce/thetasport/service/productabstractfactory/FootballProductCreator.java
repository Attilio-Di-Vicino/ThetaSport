package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class FootballProductCreator implements ProductCreator {

    @Override
    public Product createProduct( ProductBean productBean ) {
        // Implementazione per la creazione di un prodotto di calcio
        return DirectorFootball.createProduct( productBean );
    }
}
