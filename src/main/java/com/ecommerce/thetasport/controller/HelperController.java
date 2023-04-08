package com.ecommerce.thetasport.controller;

import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelperController {

    public static void verifyLoginAndCart( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
        } else {
            sessionExists( request );
        }
    }

    public static void ForwardProductList( @NotNull HttpServletRequest request ) {
        try {
            request.setAttribute( "productBeanList", ManagerProduct.getProductList() );
        } catch ( SQLException e ) {
            throw new RuntimeException( "Error in ForwardProductList SQLException" + e );
        }
    }

    public static void nullSession( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Cart myCart = new Cart();
        // setto variabili per la sessione
        session.setAttribute( "isLogged", 0 );
        // setto variabili per la sessione
        session.setAttribute( "login", 0 );
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart",  myCart.sizeCart() );
        // setto variabili per la request
        request.setAttribute( "login", 0);
        request.setAttribute( "itemsCart", myCart );
        request.setAttribute( "numItemCart", myCart.sizeCart() );
    }

    public static void sessionExists( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        // recupero variabili dalla sessione
        int isLogged = ( int ) session.getAttribute( "isLogged" );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        // setto variabili per la request
        request.setAttribute( "login", isLogged );
        request.setAttribute( "itemsCart", myCart );
        request.setAttribute( "numItemCart", myCart.sizeCart() );
    }
}
