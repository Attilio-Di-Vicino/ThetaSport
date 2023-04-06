package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.service.productabstractfactory.Shoes;
import com.ecommerce.thetasport.service.productabstractfactory.TShirt;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Il compito di quella classe è quello di visita dei prodotti del carrello.<br>
 * Quindi implementa l'interfaccia {@link ShoppingCartVisitor}<br>
 * la quale contiene i vari metodi visit da implementare<br>
 * {@link ShoppingCartVisitor#visit( Shoes )} ed {@link ShoppingCartVisitor#visit( TShirt )}<br>
 *
 * @author Theta Sport
 * @version 1.0
 * @see ShoppingCartVisitor
 * @see Shoes
 * @see TShirt
 */
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    private final Cart cart;
    private double total;
    private final DecimalFormat decimalFormat;

    /**
     * Crea un nuovo oggetto ShoppingCartVisitorImpl per il carrello specificato.
     *
     * @param cart il carrello da visitare
     */
    public ShoppingCartVisitorImpl( Cart cart ) {
        this.decimalFormat = new DecimalFormat( "#.##" );
        this.cart = cart;
        this.total = 0.0;
    }

    /**
     * Visita un prodotto {@link Shoes} nel carrello.
     *
     * @param product il prodotto {@link Shoes} da visitare
     * @return il prezzo del prodotto Shoes visitato per la quantità presente nel carrello
     */
    @Override
    public double visit( @NotNull Shoes product ) {
        int quantity = cart.getMyCart().get( product );
        System.out.println( "Name: " + product.getName() + " price: " + product.getPrice() + " quantity: " + quantity );
        return product.getPrice() * quantity;
    }

    /**
     * Visita un prodotto {@link TShirt} nel carrello.
     *
     * @param product il prodotto {@link TShirt} da visitare
     * @return il prezzo del prodotto Shoes visitato per la quantità presente nel carrello
     */
    @Override
    public double visit( @NotNull TShirt product ) {
        int quantity = cart.getMyCart().get( product );
        System.out.println( "Name: " + product.getName() + " price: " + product.getPrice() + " quantity: " + quantity );
        return product.getPrice() * quantity;
    }

    /**
     * Restituisce il prezzo totale dei prodotti del carrello.
     *
     * @return il prezzo totale dei prodotti del carrello
     */
    public double getTotal() {
        total = 0.0;
        Map<ItemElement, Integer> myCart = cart.getMyCart();
        for ( ItemElement item : myCart.keySet() ) {
            total += item.accept( this );
        }
        String formattedTotal = decimalFormat.format( total );
        return Double.parseDouble( formattedTotal.replace( ',', '.' ) );
    }
}
