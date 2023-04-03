package com.ecommerce.thetasport.service.cartvisitor;

/**
 * Questa interfaccia rappresenta un elemento all'interno del carrello ed espone un singolo metodo accept <br>
 * che prende come parametro un oggetto di tipo {@link ShoppingCartVisitor} <br>
 * e restituisce il valore dell'elemento visitato.
 *
 * @author Theta Sport
 * @version 1.0
 * @see ShoppingCartVisitor
 */
@FunctionalInterface
public interface ItemElement {

    /**
     * Metodo per visitare l'elemento del carrello e calcolarne il valore.
     *
     * @param visitor l'oggetto di tipo {@link ShoppingCartVisitor} che viene utilizzato per visitare l'elemento del carrello.
     * @return il valore inerente all'elemento del carrello visitato.
     */
    double accept( ShoppingCartVisitor visitor );
}
