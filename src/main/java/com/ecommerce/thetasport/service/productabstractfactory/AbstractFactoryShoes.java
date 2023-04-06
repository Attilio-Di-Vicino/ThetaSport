package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * L'interfaccia funzionale {@code AbstractFactoryShoes} definisce un'astratta factory per la creazione
 * di prodotti di tipo Shoes. <br>
 * Questa interfaccia definisce un singolo metodo che restituisce un oggetto di tipo {@link Product}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Product
 */
@FunctionalInterface
public interface AbstractFactoryShoes {

    /**
     * Restituisce un nuovo oggetto {@link Product} che rappresenta un paio di scarpe.
     *
     * @return un oggetto di tipo {@link Product} che rappresenta un paio di scarpe.
     */
    Product createShoes();
}
