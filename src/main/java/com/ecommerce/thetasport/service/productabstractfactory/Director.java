package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

/**
 * L'IDEA QUI POTREBBE ESSERE QUELLA DI FARE UNA SCELTA A RUNTIME
 * DALLA SERVLET QUINDI ELIMINARE QUESTO DIRECTOR E RIMANERE SOLO
 * I DIRETORI DELLE CATEGORIE
 */
public class Director {

    public static Product createProduct( @NotNull ProductBean productBean ) {
        Product newProduct;
        switch ( productBean.getCategory() ) {
            case FOOTBALL: newProduct = DirectorFootball.createProduct( productBean );
                break;
            case TENNIS: newProduct = DirectorTennis.createProduct( productBean );
                break;
            default: newProduct = null;
                break;
        }
        return newProduct;
    }
}
