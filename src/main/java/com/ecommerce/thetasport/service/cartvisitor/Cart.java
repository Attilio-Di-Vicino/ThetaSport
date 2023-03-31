package com.ecommerce.thetasport.service.cartvisitor;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<ItemElement, Integer> myCart;

    public Cart(){
        this.myCart = new HashMap<>();
    }

    public Map<ItemElement, Integer> getMyCart(){return this.myCart;}

    public void add( ItemElement itemElement, int quantity ){
        if ( !this.myCart.isEmpty() ) {
            for ( ItemElement item : myCart.keySet() ){
                if ( item.equals(itemElement) ) {
                    this.myCart.put(item, this.myCart.get(item) + quantity );
                    break;
                } else {
                    this.myCart.put( itemElement, quantity );
                    break;
                }
            }
        } else {
            this.myCart.put( itemElement, quantity );
        }
    }

    public void add( ItemElement itemElement ) {
        this.add(itemElement,1);
    }

    public void remove( ItemElement itemElement ) {
        this.myCart.remove(itemElement);
    }

    public void removeAll() {
        this.myCart.clear();
    }

    public void decreaseQuantity( ItemElement itemElement ) {
        this.myCart.put( itemElement, this.myCart.get(itemElement) - 1 );
    }

    /**
     * Restituisce una stringa che rappresenta il carrello degli acquisti,
     * con l'elenco degli oggetti e delle relative quantità.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (ItemElement item : this.myCart.keySet()){
            out.append( item.toString() ).append(" Quantity: ").append( this.myCart.get(item) ).append("\n");
        }
        return out.toString();
    }
}
