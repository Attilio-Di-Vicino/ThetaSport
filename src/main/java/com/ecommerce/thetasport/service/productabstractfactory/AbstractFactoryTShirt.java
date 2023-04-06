package com.ecommerce.thetasport.service.productabstractfactory;


/**
 * L'interfaccia funzionale {@code AbstractFactoryTShirt} definisce un'astratta factory per la creazione
 * di prodotti di tipo T-Shirt. <br>
 * Questa interfaccia definisce un singolo metodo che restituisce un oggetto di tipo {@link Product}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Product
 */
@FunctionalInterface
public interface AbstractFactoryTShirt {

    /**
     * Restituisce un nuovo oggetto {@link Product} che rappresenta una T-Shirt.
     *
     * @return un oggetto di tipo {@link Product} che rappresenta una T-Shirt.
     */
    Product createTShirt();
}
