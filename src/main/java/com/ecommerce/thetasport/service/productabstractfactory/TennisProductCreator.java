package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class TennisProductCreator implements ProductCreator {

    @Override
    public Product createProduct( ProductBean productBean ) {
        // Implementazione per la creazione di un prodotto di calcio
        return DirectorTennis.createProduct( productBean );
    }
}
