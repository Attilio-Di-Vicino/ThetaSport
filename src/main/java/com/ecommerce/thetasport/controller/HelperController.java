package com.ecommerce.thetasport.controller;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.model.UserBean;
import com.ecommerce.thetasport.service.admin.ManagerAdmin;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import com.ecommerce.thetasport.service.productabstractfactory.Director;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

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
        // request.setAttribute( "itemsCart", myCart );
        // request.setAttribute( "numItemCart", myCart.sizeCart() );
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

    /**
     * loggedError viene invocato nel caso in cui ci siano stato errori nel login
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @param message messaggio di errore riscontrato
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    public static void loggedError( @NotNull HttpServletRequest request, HttpServletResponse response, String message ) throws ServletException, IOException {
        request.setAttribute( "errorMessage", message );
        request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
    }

    /**
     * loggedoOrRegistrationSuccessful viene invocato nel caso in cui il login, o la registrazione ha avuto successo,
     * quindi si recupera la sessione attuale, e nel caso in cui esista viene inavlidata.
     * Quindi si crea una nuova sessione settando il tempo massimo (in questo caso 30 minuti), si recuperano quindi
     * i dati inerenti all'utente/admin appena registarto si setta il campo login della sessione e della pagina di atterraggio,
     * ed infine si ridereziona alla pagina di atterraggio che è condizionata dal tipo di risultato ottenuto dal login
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @param email mail dell'utente loggato
     * @param isLogged stato di log, quindi 1 se utente, 2 se admin
     * @param landingPage la rispettiva jsp di destinazione
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    public static void loggedOrRegistrationSuccessful( @NotNull HttpServletRequest request, HttpServletResponse response, String email, int isLogged, String landingPage ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        // recupero la sessione corrente e se esiste la invalido
        HttpSession oldSession = request.getSession( false );
        if ( oldSession != null ) {
            oldSession.invalidate(); // invalido la sessione
        }
        // prendo la nouva sessione corrente
        HttpSession currentSession = request.getSession();
        currentSession.setMaxInactiveInterval( 30 * 60 ); // la nuova sessione durerà 30 minuti
        UserBean userBean = ManagerLogin.getSingleUser( email );
        currentSession.setAttribute( "userBean", userBean );
        currentSession.setAttribute( "isLogged", isLogged );
        request.setAttribute( "login", isLogged );
        Cart myCart = new Cart();
        currentSession.setAttribute( "itemsCart", myCart );
        currentSession.setAttribute( "numItemCart", myCart.sizeCart() );
        // setto variabili per la request
        // request.setAttribute( "itemsCart", myCart );
        // request.setAttribute( "numItemCart", myCart.sizeCart() );
        if ( landingPage.equals("jsp/index.jsp") ) {
            // home page
            HelperController.ForwardProductList( request );
        } else {
            // admin page
            request.setAttribute( "addproduct", false );
            request.setAttribute( "editproduct", false );
            request.setAttribute( "editsingleproduct", false );
            request.setAttribute( "earningMonthly", ManagerAdmin.getSumOrderMontly() );
            request.setAttribute( "earningYears", ManagerAdmin.getSumOrderYear() );
            request.setAttribute( "earningTotal", ManagerAdmin.getSumOrderTotal() );
            request.setAttribute( "orderList", ManagerAdmin.getTotalOrderBean() );
        }
        request.getRequestDispatcher( landingPage ).forward( request, response );
    }

    public static void getCode( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
        ProductBean productBean;
        try {
            productBean = ManagerProduct.getSingleProduct( code );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in HelperController/getCode" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in HelperController/getCode" + ce );
        }
        HelperController.verifyLoginAndCart( request );
        request.setAttribute( "singleProduct", productBean );
        request.getRequestDispatcher( "jsp/single_product.jsp" ).forward( request, response );
    }

    public static void statusLog( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        // recupero variabili dalla sessione
        int isLogged = ( int ) session.getAttribute( "isLogged" );
        if ( isLogged == 0 ) {
            request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
        }
    }

    public static void addCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
        } else {
            statusLog( request,response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            try {
                myCart.add( Director.createProduct( ManagerProduct.getSingleProduct( code ) ) );
            } catch ( SQLException e ) {
                throw new RuntimeException( "SQL Exception in HelperController/addCart" + e );
            } catch ( ClassNotFoundException ce ) {
                throw new RuntimeException( "ClassNotFound Exception in HelperController/addCart" + ce );
            }
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
            sessionExists(request);
        }
    }

    public static void removeCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
            request.setAttribute( "errorMessageCart", "do login" );
        } else {
            statusLog( request, response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            myCart.removeQuantityOne( Director.createProduct( ManagerProduct.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            sessionExists( request );
        }
    }

    public static void cartPage( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            request.setAttribute( "errorCart", true );
            request.setAttribute( "errorMessage", "Error" );
            request.getRequestDispatcher( "jsp/cart.jsp" ).forward( request, response );
        } else {
            statusLog( request,response );
            sessionExists( request );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
            double total = shoppingCartVisitor.getTotal();
            session.setAttribute( "itemsCart", myCart );
            request.setAttribute("totalPrice", total );
            request.getRequestDispatcher( "jsp/cart.jsp" ).forward( request, response );
        }
    }

    public static void singleProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getCode(request, response);
    }

    public static void addCartCaseIndex( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request,response );
        // request.getRequestDispatcher( "jsp/component/navbar.jsp" ).include( request, response );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }

    public static void addCartCaseCart( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request, response );
        cartPage( request, response );
    }

    public static void addCartCaseSingleProduct( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request,response );
        singleProduct( request,response );
    }

    public static void removeCartCaseIndex( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart( request, response );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }

    public static void removeCartCaseCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart(request,response);
        cartPage(request,response);
    }

    public static void removeCartCaseSingleProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart(request,response);
        singleProduct(request,response);
    }

    public static void removeObjectCart( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            nullSession( request );
            request.setAttribute( "errorMessageCart", "do login" );
        } else {
            statusLog( request, response );
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
            myCart.remove( Director.createProduct( ManagerProduct.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            sessionExists( request );
        }
        cartPage( request, response );
    }
}
