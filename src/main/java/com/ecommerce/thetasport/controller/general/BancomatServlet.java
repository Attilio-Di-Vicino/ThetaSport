package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.paymentstrategy.BancomatStrategy;
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

@WebServlet(name = "BancomatServlet", value = "/BancomatServlet")
public class BancomatServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request,response );
        request.setAttribute( "creditCart", false );
        request.setAttribute( "bancomat", true );
        request.setAttribute( "cash", false );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request, response );
    }

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html");
        @SuppressWarnings( value = "Variable 'country' is never used" )
        String country = request.getParameter( "b_country" );
        String firstName = request.getParameter( "b_fname" );
        @SuppressWarnings( value = "Variable 'lastName' is never used" )
        String lastName = request.getParameter( "b_lname" );
        String cartNumber = request.getParameter( "b_cart_number" );
        String cvv = request.getParameter( "b_cvv" );
        String dateOfExpiry = request.getParameter( "b_date_expiry" );
        @SuppressWarnings( value = "Variable 'address' is never used" )
        String address = request.getParameter( "b_address" );
        @SuppressWarnings( value = "Variable 'shippingAddress' is never used" )
        String shippingAddress = request.getParameter( "b_s_address" );
        @SuppressWarnings( value = "Variable 'stateCountry' is never used" )
        String stateCountry = request.getParameter( "b_state_country" );
        @SuppressWarnings( value = "Variable 'postalCode' is never used" )
        String postalCode = request.getParameter( "b_postal_zip" );
        String email = request.getParameter( "b_email_address" );
        @SuppressWarnings( value = "Variable 'phone' is never used" )
        String phone = request.getParameter( "b_phone" );
        @SuppressWarnings( value = "Variable 'orderNotes' is never used" )
        String orderNotes = request.getParameter( "b_order_notes" );

        HttpSession session = request.getSession( false );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        ManagerPayments.pay( new BancomatStrategy( firstName, cartNumber, cvv, dateOfExpiry), total);
        // inserire gli ordini nel DB
        try {
            ManagerPayments.insertOrder( myCart, email, total );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in BancomatServlet/doPost" + e);
        }
        // svuotare il carrello
        myCart.removeAll();
        session.setAttribute( "itemsCart", myCart );
        request.getRequestDispatcher( "jsp/thank_you.jsp" ).forward( request,response );
    }
}
