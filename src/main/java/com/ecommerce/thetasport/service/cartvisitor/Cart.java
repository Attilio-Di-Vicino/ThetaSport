package com.ecommerce.thetasport.service.cartvisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Il compito di quella classe è quello di aggiungere e rimuovere prodotti dal carrello.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class Cart {

    private final Map<ItemElement, Integer> MY_CART;

    /**
     * Costruttore senza argomenti che inizializza la mappa del carrello.
     */
    public Cart(){
        this.MY_CART = new HashMap<>();
    }

    /**
     * Metodo che restituisce la mappa del carrello.
     *
     * @return mappa del carrello
     */
    public Map<ItemElement, Integer> getMyCart() { return this.MY_CART; }

    /**
     * Metodo per aggiungere un elemento al carrello con una quantità specificata.<br>
     * Se l'elemento è già presente nel carrello, la quantità viene incrementata.
     *
     * @param itemElement elemento da aggiungere
     * @param quantity quantità dell'elemento da aggiungere
     */
    public void add( ItemElement itemElement, int quantity ) {
        if ( this.MY_CART.containsKey( itemElement ) ) {
            this.MY_CART.put( itemElement, this.MY_CART.get( itemElement ) + quantity );
        } else {
            this.MY_CART.put( itemElement, quantity );
        }
    }

    /**
     * Metodo per aggiungere un elemento al carrello con una quantità di default di 1.
     *
     * @param itemElement elemento da aggiungere
     */
    public void add( ItemElement itemElement ) {
        this.add( itemElement, 1 );
    }

    /**
     * Metodo per rimuovere un elemento dal carrello.
     *
     * @param itemElement elemento da rimuovere
     */
    public void remove( ItemElement itemElement ) {
        if ( MY_CART.containsKey( itemElement ) ) {
            MY_CART.remove( itemElement );
        }
    }

    /**
     * Metodo per rimuovere tutti gli elementi dal carrello.
     */
    public void removeAll() {
        this.MY_CART.clear();
    }

    /**
     * Metodo per decrementare la quantità di un elemento nel carrello di 1.
     *
     * @param itemElement elemento di cui decrementare la quantità
     */
    public void decreaseQuantity(ItemElement itemElement ) {
        if ( MY_CART.containsKey( itemElement ) ) {
            int number = MY_CART.get( itemElement );
            if ( number <= 1 ) {
                this.remove( itemElement );
            } else {
                MY_CART.put( itemElement, MY_CART.get( itemElement ) - 1 );
            }
        }
    }

    /**
     * viene utilizzato un oggeto di tipo {@link StringBuilder} <br>
     * che ci permette di construire output tramite il pattern builder
     *
     * @return stringa elenco degli oggetti e delle relative quantità
     */
    @Override
    public String toString() {
        if ( this.MY_CART.isEmpty() ) {
            return "Cart is empty.";
        }
        StringBuilder out = new StringBuilder();
        for ( ItemElement item : this.MY_CART.keySet() ) {
            out.append( item.toString() ).append( " Quantity: " ).append( this.MY_CART.get(item) ).append("\n");
        }
        return out.toString();
    }

    public int sizeCart() {
        int i = 0;
        for ( ItemElement item : MY_CART.keySet() ) {
            i += MY_CART.get( item );
        }
        return i;
    }
}
