package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.service.productabstractfactory.Shoes;
import com.ecommerce.thetasport.service.productabstractfactory.TShirt;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Il compito di quella classe è quello di visita e del totale dei prodotti del carrello.
 * Quindi implementa l'interfaccia {@link ShoppingCartVisitor}
 * la quale contiene i vari metodi visit da implementare
 * {@link ShoppingCartVisitor#visit(Shoes)} ed {@link ShoppingCartVisitor#visit(TShirt)}
 */
public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    private final Cart cart;
    private double total;
    private final DecimalFormat decimalFormat;

    public ShoppingCartVisitorImpl( Cart cart ) {
        this.decimalFormat = new DecimalFormat("#.##");
        this.cart = cart;
        this.total = 0.0;
    }

    @Override
    public double visit( @NotNull Shoes product ) {
        int quantity = cart.getMyCart().get( product );
        System.out.println( "Name: " + product.getName() + " price: " + product.getPrice() + " quantity: " + quantity );
        return product.getPrice() * quantity;
    }

    @Override
    public double visit( @NotNull TShirt product ) {
        int quantity = cart.getMyCart().get( product );
        System.out.println( "Name: " + product.getName() + " price: " + product.getPrice() + " quantity: " + quantity );
        return product.getPrice() * quantity;
    }

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
