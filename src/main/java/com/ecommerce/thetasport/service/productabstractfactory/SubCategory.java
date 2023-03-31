package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Enumerazione che rappresenta le possibili sottocategorie di prodotti.
 */
public enum SubCategory {
    SHOES,TSHIRT,SHORTS,ACCESSORIES;

    /**
     * Ritorna il nome della sottocategoria in formato testuale.
     * @return il nome della sottocategoria come stringa.
     */
    @SuppressWarnings("duplicate")
    @Override
    public String toString() {
        String name;
        switch ( this ){
            case SHOES: name = "Shoes";
                break;
            case TSHIRT: name = "TShirt";
                break;
            case SHORTS: name = "Shorts";
                break;
            case ACCESSORIES: name = "Accessories";
                break;
            default: name = null;
                break;
        }
        return name;
    }
}
