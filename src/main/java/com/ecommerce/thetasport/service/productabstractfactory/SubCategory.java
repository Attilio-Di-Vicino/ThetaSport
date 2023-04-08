package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * L'enumerazione {@code SubCategory} rappresenta le sotto-categorie dei prodotti.
 * Ogni costante dell'enumerazione rappresenta una sotto-categoria e ha un nome associato.
 *
 * @author Theta Sport
 * @version 1.0
 */
public enum SubCategory {
    SHOES( "Shoes" ),
    TSHIRT( "T-Shirt" );

    private final String NAME;

    /**
     * Costruttore dell'enumerazione che imposta il nome della sotto-categoria.
     *
     * @param name il nome della categoria
     */
    SubCategory( String name ) {
        this.NAME = name;
    }

    /**
     * Restituisce il nome della sotto-categoria.
     *
     * @return il nome della sotto-categoria come stringa
     */
    @Override
    public String toString() {
        return NAME;
    }
}
