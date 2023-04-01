package com.ecommerce.thetasport.service.cartvisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Il compito di quella classe è quello di aggiungere e rimuovere prodotti dal carrello.
 */
public class Cart {

    private final Map<ItemElement, Integer> myCart;

    public Cart(){
        this.myCart = new HashMap<>();
    }

    public Map<ItemElement, Integer> getMyCart() { return this.myCart; }

    public void add( ItemElement itemElement, int quantity ) {
        if ( this.myCart.containsKey( itemElement ) ) {
            this.myCart.put( itemElement, this.myCart.get( itemElement ) + quantity );
        } else {
            this.myCart.put( itemElement, quantity );
        }
    }

    public void add( ItemElement itemElement ) {
        this.add( itemElement, 1 );
    }

    public void remove( ItemElement itemElement ) {
        this.myCart.remove( itemElement );
    }

    public void removeAll() {
        this.myCart.clear();
    }

    public void decreaseQuantity( ItemElement itemElement ) {
        this.myCart.put( itemElement, this.myCart.get( itemElement ) - 1 );
    }

    /**
     * viene utilizzato un oggeto di tipo {@link StringBuilder}
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
