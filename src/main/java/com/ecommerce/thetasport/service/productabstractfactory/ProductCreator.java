package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * Un'interfaccia funzionale che definisce un metodo per creare un prodotto a partire da un oggetto ProductBean.
 *
 * @author Theta Sport
 * @version 1.0
 */
@FunctionalInterface
public interface ProductCreator {
    Product createProduct( ProductBean productBean );
}
