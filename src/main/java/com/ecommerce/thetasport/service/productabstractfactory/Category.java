package com.ecommerce.thetasport.service.productabstractfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerazione che rappresenta le categorie dei prodotti.
 */
public enum Category {
    FOOTBALL( "Football" , new FootballProductCreator() ),
    TENNIS( "Tennis" , new TennisProductCreator() );

    private String name;
    private ProductCreator creator;

    Category( String name, ProductCreator creator ) {
        this.name = name;
        this.creator = creator;
    }

    public ProductCreator getCreator() {
        return creator;
    }

    /**
     * Restituisce il nome della categoria.
     *
     * @return il nome della categoria come stringa
     */
    @Override
    public String toString() {
        return name;
    }
}
