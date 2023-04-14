package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * Classe che rappresenta il direttore per la creazione di prodotti del tipo "Tennis".
 *
 * @author Theta Sport
 * @version 1.0
 * @see Director
 * @see TennisConcreteFactory
 */
public class DirectorTennis {

    /**
     * Metodo statico che crea un prodotto del tipo "Football" sulla base delle informazioni contenute in un oggetto
     * di tipo {@link ProductBean}.
     *
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link Product} rappresentante il prodotto creato
     */
    @SuppressWarnings( value = "Duplicated code fragment" )
    public static Product createProduct( ProductBean productBean ) {
        if ( productBean == null ) {
            throw new NullPointerException( "ProductBean in DirectorTennis/createProduct is null." );
        }
        // Viene istanziata la factory specifica per i prodotti di tipo "Football"
        TennisConcreteFactory tennisConcreteFactory = new TennisConcreteFactory();
        Product newProductTennis;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch ( productBean.getSubCategory() ) {
            case SHOES: newProductTennis = tennisConcreteFactory.createShoes();
                break;
            case TSHIRT: newProductTennis = tennisConcreteFactory.createTShirt();
                break;
            default: newProductTennis = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct( newProductTennis, productBean );
        return newProductTennis;
    }
}
