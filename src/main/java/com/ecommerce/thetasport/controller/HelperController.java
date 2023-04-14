package com.ecommerce.thetasport.controller;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.model.UserBean;
import com.ecommerce.thetasport.service.admin.ManagerAdmin;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import com.ecommerce.thetasport.service.productabstractfactory.Director;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import com.ecommerce.thetasport.service.productabstractfactory.Product;
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
        }
    }

    public static void ForwardProductList( @NotNull HttpServletRequest request ) {
        try {
            request.setAttribute( "productBeanList", ProductDAO.getProductBeanList() );
        } catch ( SQLException e ) {
            throw new RuntimeException( "Error in ForwardProductList SQLException" + e );
        }
    }

    public static void nullSession( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession();
        Cart myCart = new Cart();
        // variables are set for the session
        session.setAttribute( "isLogged", 0 );
        // variables are set for the session
        session.setAttribute( "login", 0 );
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart",  myCart.sizeCart() );
        // variables are set for the request
        request.setAttribute( "login", 0);
    }

    /**
     * loggedError is invoked if there were errors in the login.
     *
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    public static void loggedError( @NotNull HttpServletRequest request, HttpServletResponse response, String message ) throws ServletException, IOException {
        request.setAttribute( "errorMessage", message );
        request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
    }

    /**
     * loggedOrRegistrationSuccessful is invoked if the login, or the registration, was successful<br>
     * then the current session is retrieved or, if it exists it is invalidated.<br>
     * Then a new session is created by setting the maximum time (in this case 30 minutes), then retrieve<br>
     * the data inherent to the newly registered user/admin, you set the login field of the session and landing page,<br>
     * and finally redirect to the landig page which is conditioned by type of result obtained from the login.
     *
     * @param request Request made via a browser
     * @param response Response
     * @param email mail from the logged user
     * @param isLogged log status: 1 from user, 2 from admin
     * @param landingPage the respective jsp target
     * @throws ServletException Defines a general exception that a servlet can generate when it encounters difficulties
     * @throws IOException Reports that an I/O exception has occurred
     */
    public static void loggedOrRegistrationSuccessful( @NotNull HttpServletRequest request, HttpServletResponse response, String email, int isLogged, String landingPage ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        // the current session is retrieved and if it exists it is invalidated
        HttpSession oldSession = request.getSession( false );
        if ( oldSession != null ) {
            oldSession.invalidate(); //  the session is invalidated
        }
        // the new current session is taken
        HttpSession currentSession = request.getSession();
        currentSession.setMaxInactiveInterval( 30 * 60 ); // the new session will last 30 minutes
        UserBean userBean = new UserDAO().getUser( email );
        currentSession.setAttribute( "userBean", userBean );
        currentSession.setAttribute( "isLogged", isLogged );
        currentSession.setAttribute( "login", isLogged );
        request.setAttribute( "login", isLogged );
        Cart myCart = new Cart();
        currentSession.setAttribute( "itemsCart", myCart );
        currentSession.setAttribute( "numItemCart", myCart.sizeCart() );
        if ( landingPage.equals("jsp/index.jsp") ) {
            // home page
            HelperController.ForwardProductList( request );
        } else {
            // admin page
            request.setAttribute( "addproduct", false );
            request.setAttribute( "editproduct", false );
            request.setAttribute( "editsingleproduct", false );
            request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
            request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
            request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
            request.setAttribute( "orderList", OrderDAO.getSumPriceOrderTotal() );
        }
        request.getRequestDispatcher( landingPage ).forward( request, response );
    }

    public static void getCode( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
        Product product;
        try {
            product = Director.createProduct( ProductDAO.getSingleProduct( code ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in HelperController/getCode" + e );
        }
        HelperController.verifyLoginAndCart( request );
        request.setAttribute( "singleProduct", product );
        request.getRequestDispatcher( "jsp/single_product.jsp" ).forward( request, response );
    }

    public static void statusLog( @NotNull HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession( false );
        // variables are retrieved from the session
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
                myCart.add( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            } catch ( SQLException e ) {
                throw new RuntimeException( "SQL Exception in HelperController/addCart" + e );
            }
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
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
            myCart.decreaseQuantity( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
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
            Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
            ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
            double total = shoppingCartVisitor.getTotal();
            session.setAttribute( "itemsCart", myCart );
            request.setAttribute("totalPrice", total );
            request.getRequestDispatcher( "jsp/cart.jsp" ).forward( request, response );
        }
    }

    public static void addCartCaseIndexOrSingleProduct(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request,response );
    }

    public static void addCartCaseCart( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        addCart( request, response );
        cartPage( request, response );
    }

    public static void removeCartCaseIndexOrSingleProduct(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart( request, response );
    }

    public static void removeCartCaseCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        removeCart(request,response);
        cartPage(request,response);
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
            myCart.remove( Director.createProduct( ProductDAO.getSingleProduct( code ) ) );
            session.setAttribute( "itemsCart", myCart );
            session.setAttribute( "numItemCart", myCart.sizeCart() );
        }
        cartPage( request, response );
    }

    public static void checkoutPage( @NotNull HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        // recupero variabili dalla sessione
        int isLogged = ( int ) session.getAttribute( "isLogged" );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        // setto variabili per la request
        session.setAttribute( "login", isLogged );
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart", myCart.sizeCart() );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        request.setAttribute( "totalPrice", total );
    }

    public static void setVarThankYouPage( @NotNull HttpServletRequest request, HttpSession session, Cart myCart,
                                           String country, String firstName, String lastName, String address,
                                           String shippingAddress, String stateCountry, String postalCode, String email,
                                           String phone, String orderNotes ) {
        request.setAttribute( "country", country );
        request.setAttribute( "name", firstName + " " + lastName );
        request.setAttribute( "address", address );
        request.setAttribute( "shippingAddress", shippingAddress );
        request.setAttribute( "stateCountry", stateCountry );
        request.setAttribute( "postalCode", postalCode );
        request.setAttribute( "email", email );
        request.setAttribute( "phone", phone );
        request.setAttribute( "orderNotes", orderNotes );
        myCart.removeAll();
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart", myCart.sizeCart() );
    }
}