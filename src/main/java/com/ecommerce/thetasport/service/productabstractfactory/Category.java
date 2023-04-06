package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * L'enumerazione {@code Category} rappresenta le categorie dei prodotti.
 * Ogni costante dell'enumerazione rappresenta una categoria e ha un nome e un creatore di prodotti associati.
 *
 * @author Theta Sport
 * @version 1.0
 * @see FootballProductCreator
 * @see TennisProductCreator
 */
public enum Category {
    FOOTBALL( "Football" , new FootballProductCreator() ),
    TENNIS( "Tennis" , new TennisProductCreator() );

    private final String NAME;
    private final ProductCreator CREATOR;

    /**
     * Costruttore dell'enumerazione che imposta il nome e il creatore di prodotti della categoria.
     *
     * @param name il nome della categoria
     * @param creator il creatore di prodotti per la categoria
     */
    Category( String name, ProductCreator creator ) {
        this.NAME = name;
        this.CREATOR = creator;
    }

    /**
     * Restituisce il creatore di prodotti associato alla categoria.
     *
     * @return il creatore di prodotti per la categoria
     */
    public ProductCreator getCreator() {
        return CREATOR;
    }

    /**
     * Restituisce il nome della categoria.
     *
     * @return il nome della categoria come stringa
     */
    @Override
    public String toString() {
        return NAME;
    }
}
