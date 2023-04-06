package com.ecommerce.thetasport.service.paymentstrategy;

import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.productabstractfactory.FootballShoes;
import com.ecommerce.thetasport.service.productabstractfactory.FootballTShirt;
import com.ecommerce.thetasport.service.productabstractfactory.TennisShoes;

public class Client {
    public static void main( String[] args ) {
        // add new products to your cart by setting only the category, sub-category, name and price
        Cart cart = new Cart();
        cart.add( new FootballShoes().build()
                .setName( "Product-one" )
                .setDescription( "Product-one" )
                .setImage( "Product-one" )
                .setPrice( 18.99 ) );
        cart.add( new TennisShoes().build()
                .setName( "Product-two" )
                .setDescription( "Product-two" )
                .setImage( "Product-two" )
                .setPrice( 24.99 ), 4 );
        cart.add( new FootballTShirt().build()
                .setName( "Product-three" )
                .setDescription( "Product-three" )
                .setImage( "Product-three" )
                .setPrice( 64.99 ), 2 );
        System.out.println( "\n*** SHOW MY CART ***" );
        System.out.println( cart );
        // new visitor
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( cart );
        // test payment method with bancomat
        System.out.println( "\n*** TEST WITH BANCOMAT ***" );
        PaymentStrategy paymentStrategy = new BancomatStrategy( "attilio", "4456 3245 1234 6345 5567",
                "771", "2030-10-12" );
        ManagerPayments.pay( paymentStrategy, shoppingCartVisitor.getTotal() );
        // test payment method with credit card
        System.out.println( "\n*** TEST WITH CREDIT CARD ***" );
        paymentStrategy = new CreditCardStrategy( "mario", "4456 3245 1234 6345 5567",
                "771", "2030-10-12" );
        ManagerPayments.pay( paymentStrategy, shoppingCartVisitor.getTotal() );
        // test payment method with cash method
        System.out.println( "\n*** TEST WITH CASH METHOD ***" );
        paymentStrategy = new CashStrategy( "lorenzo@gmail.com", "333 333 33 33",
                "Via Napoli 60" );
        ManagerPayments.pay( paymentStrategy, shoppingCartVisitor.getTotal() );
    }
}
