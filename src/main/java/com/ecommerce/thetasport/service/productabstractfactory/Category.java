package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Enumerazione che rappresenta le categorie dei prodotti.
 */
public enum Category {
    FOOTBALL,TENNIS;

    /**
     * Restituisce il nome della categoria.
     * @return il nome della categoria come stringa
     */
    @Override
    public String toString() {
        String name;
        switch ( this ) {
            case FOOTBALL:
                name = "Football";
                break;
            case TENNIS:
                name = "Tennis";
                break;
            default:
                name = null;
                break;
        }
        return name;
    }
}
