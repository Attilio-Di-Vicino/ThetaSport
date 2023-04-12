package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.paymentstrategy.CreditCardStrategy;
import com.ecommerce.thetasport.service.paymentstrategy.ManagerPayments;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "CreditCartServlet", value = "/CreditCartServlet" )
public class CreditCartServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request, response );
        request.setAttribute( "creditCart", true );
        request.setAttribute( "bancomat", false );
        request.setAttribute( "cash", false );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request, response );
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType( "text/html" );
        @SuppressWarnings( value = "Variable 'country' is never used" )
        String country = request.getParameter( "c_country" );
        String firstName = request.getParameter( "c_fname" );
        @SuppressWarnings( value = "Variable 'lastName' is never used" )
        String lastName = request.getParameter( "c_lname" );
        String cartNumber = request.getParameter( "c_cart_number" );
        String cvv = request.getParameter( "c_cvv" );
        String dateOfExpiry = request.getParameter( "c_date_expiry" );
        @SuppressWarnings( value = "Variable 'address' is never used" )
        String address = request.getParameter( "c_address" );
        @SuppressWarnings( value = "Variable 'shippingAddress' is never used" )
        String shippingAddress = request.getParameter( "c_s_address" );
        @SuppressWarnings( value = "Variable 'stateCountry' is never used" )
        String stateCountry = request.getParameter( "c_state_country" );
        @SuppressWarnings( value = "Variable 'postalCode' is never used" )
        String postalCode = request.getParameter( "c_postal_zip" );
        String email = request.getParameter( "c_email_address" );
        @SuppressWarnings( value = "Variable 'phone' is never used" )
        String phone = request.getParameter( "c_phone" );
        @SuppressWarnings( value = "Variable 'orderNotes' is never used" )
        String orderNotes = request.getParameter( "c_order_notes" );

        HttpSession session = request.getSession( false );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        ManagerPayments.pay( new CreditCardStrategy( firstName, cartNumber, cvv, dateOfExpiry ), total );
        // inserire gli ordini nel DB
        try {
            ManagerPayments.insertOrder( myCart, email, total );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in BancomatServlet/doPost" + e );
        }
        // svuotare il carrello
        myCart.removeAll();
        session.setAttribute( "itemsCart", myCart );
        request.getRequestDispatcher( "jsp/thank_you.jsp" ).forward( request,response );
    }
}
