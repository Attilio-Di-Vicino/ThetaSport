package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.paymentstrategy.CashStrategy;
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

@WebServlet(name = "CashServlet", value = "/CashServlet")
public class CashServlet extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request );
        request.setAttribute( "creditCart", false );
        request.setAttribute( "bancomat", false );
        request.setAttribute( "cash", true );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request,response );
    }

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String country = request.getParameter( "cc_country" );
        String firstName = request.getParameter( "cc_fname" );
        String lastName = request.getParameter( "cc_lname" );
        String address = request.getParameter( "cc_address" );
        String shippingAddress = request.getParameter( "cc_s_address" );
        String stateCountry = request.getParameter( "cc_state_country" );
        String postalCode = request.getParameter( "cc_postal_zip" );
        String email = request.getParameter( "cc_email_address" );
        String phone = request.getParameter( "cc_phone" );
        String orderNotes = request.getParameter( "cc_order_notes" );
        // Manager payment
        HttpSession session = request.getSession(false);
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        ManagerPayments.pay( new CashStrategy( firstName, phone, shippingAddress ), total);
        // inserire gli ordini nel DB
        try {
            ManagerPayments.insertOrder( myCart, email, total );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in CashServlet/doPost" + e);
        }
        // svuotare il carrello
        myCart.removeAll();
        session.setAttribute( "itemsCart", myCart );
        session.setAttribute( "numItemCart", myCart.sizeCart() );
        HelperController.setVarThankYouPage( request, country, firstName, lastName, address,
                                             shippingAddress, stateCountry, postalCode, email, phone, orderNotes );
        request.getRequestDispatcher( "jsp/thank_you.jsp" ).forward( request,response );
    }
}
