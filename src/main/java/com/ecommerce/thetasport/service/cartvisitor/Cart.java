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

    private final Map<ItemElement, Integer> myCart;


    /**
     * Costruttore senza argomenti che inizializza la mappa del carrello.
     */
    public Cart(){
        this.myCart = new HashMap<>();
    }

    /**
     * Metodo che restituisce la mappa del carrello.
     * @return mappa del carrello
     */
    public Map<ItemElement, Integer> getMyCart() { return this.myCart; }

    /**
     * Metodo per aggiungere un elemento al carrello con una quantità specificata.<br>
     * Se l'elemento è già presente nel carrello, la quantità viene incrementata.
     * @param itemElement elemento da aggiungere
     * @param quantity quantità dell'elemento da aggiungere
     */
    public void add( ItemElement itemElement, int quantity ) {
        if ( this.myCart.containsKey( itemElement ) ) {
            this.myCart.put( itemElement, this.myCart.get( itemElement ) + quantity );
        } else {
            this.myCart.put( itemElement, quantity );
        }
    }

    /**
     * Metodo per aggiungere un elemento al carrello con una quantità di default di 1.
     * @param itemElement elemento da aggiungere
     */
    public void add( ItemElement itemElement ) {
        this.add( itemElement, 1 );
    }

    /**
     * Metodo per rimuovere un elemento dal carrello.
     * @param itemElement elemento da rimuovere
     */
    public void remove( ItemElement itemElement ) {
        this.myCart.remove( itemElement );
    }

    /**
     * Metodo per rimuovere tutti gli elementi dal carrello.
     */
    public void removeAll() {
        this.myCart.clear();
    }

    /**
     * Metodo per decrementare la quantità di un elemento nel carrello di 1.
     * @param itemElement elemento di cui decrementare la quantità
     */
    public void decreaseQuantity( ItemElement itemElement ) {
        this.myCart.put( itemElement, this.myCart.get( itemElement ) - 1 );
    }

    /**
     * viene utilizzato un oggeto di tipo {@link StringBuilder} <br>
     * che ci permette di construire output tramite il pattern builder
     * @return stringa elenco degli oggetti e delle relative quantità
     */
    @Override
    public String toString() {
        if ( this.myCart.isEmpty() ) {
            return "Cart is empty";
        }
        StringBuilder out = new StringBuilder();
        for ( ItemElement item : this.myCart.keySet() ){
            out.append( item.toString() ).append(" Quantity: ").append( this.myCart.get(item) ).append("\n");
        }
        return out.toString();
    }
}
