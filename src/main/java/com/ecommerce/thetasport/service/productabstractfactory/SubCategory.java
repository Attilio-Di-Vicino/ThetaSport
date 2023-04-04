package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Enumerazione che rappresenta le possibili sottocategorie di prodotti.
 */
public enum SubCategory {
    SHOES,TSHIRT;

    /**
     * Ritorna il nome della sottocategoria in formato testuale.
     * @return il nome della sottocategoria come stringa.
     */
    @Override
    public String toString() {
        String name;
        switch ( this ){
            case SHOES: name = "Shoes";
                break;
            case TSHIRT: name = "TShirt";
                break;
            default: name = null;
                break;
        }
        return name;
    }
}
