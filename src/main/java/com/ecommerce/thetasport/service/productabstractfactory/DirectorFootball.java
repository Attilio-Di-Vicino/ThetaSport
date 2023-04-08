package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * Classe che rappresenta il direttore per la creazione di prodotti del tipo "Football".
 *
 * @author Theta Sport
 * @version 1.0
 * @see Director
 * @see FootballConcreteFactory
 */
public class DirectorFootball {

    /**
     * Metodo statico che crea un prodotto del tipo "Football" sulla base delle informazioni contenute in un oggetto
     * di tipo {@link ProductBean}.
     *
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link Product} rappresentante il prodotto creato
     */
    @SuppressWarnings( value = "Duplicated code fragment (11 lines long)" )
    public static Product createProduct( ProductBean productBean ) {
        if ( productBean == null ) {
            throw new NullPointerException( "ProductBean in DirectorFootball/createProduct is null." );
        }
        // Viene istanziata la factory specifica per i prodotti di tipo "Football"
        FootballConcreteFactory footballConcreteFactory = new FootballConcreteFactory();
        Product newProductFootball;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch ( productBean.getSubCategory() ) {
            case SHOES: newProductFootball = footballConcreteFactory.createShoes();
                break;
            case TSHIRT: newProductFootball = footballConcreteFactory.createTShirt();
                break;
            default: newProductFootball = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct( newProductFootball, productBean );
        return newProductFootball;
    }
}
