package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.dao.OrderDAO;
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

/**
 * Questa classe rappresenta il servlet per la pagina di pagamento con Bancomat.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet(name = "BancomatServlet", value = "/BancomatServlet")
public class BancomatServlet extends HttpServlet {

    /**
     * Metodo che gestisce la richiesta GET per la pagina di pagamento con Bancomat.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client
     * @param response l'oggetto HttpServletResponse che contiene la risposta del server
     * @throws ServletException se si verifica un errore durante la gestione della richiesta
     * @throws IOException      se si verifica un errore durante la gestione della richiesta
     */
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request );
        request.setAttribute( "creditCart", false );
        request.setAttribute( "bancomat", true );
        request.setAttribute( "cash", false );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request, response );
    }

    /**
     * Metodo che gestisce la richiesta POST per la pagina di pagamento con Bancomat.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client
     * @param response l'oggetto HttpServletResponse che contiene la risposta del server
     * @throws ServletException se si verifica un errore durante la gestione della richiesta
     * @throws IOException      se si verifica un errore durante la gestione della richiesta
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html");
        String country = request.getParameter( "b_country" );
        String firstName = request.getParameter( "b_fname" );
        String lastName = request.getParameter( "b_lname" );
        String cartNumber = request.getParameter( "b_cart_number" );
        String cvv = request.getParameter( "b_cvv" );
        String dateOfExpiry = request.getParameter( "b_date_expiry" );
        String address = request.getParameter( "b_address" );
        String shippingAddress = request.getParameter( "b_s_address" );
        String stateCountry = request.getParameter( "b_state_country" );
        String postalCode = request.getParameter( "b_postal_zip" );
        String email = request.getParameter( "b_email_address" );
        String phone = request.getParameter( "b_phone" );
        String orderNotes = request.getParameter( "b_order_notes" );
        // Manager Pay
        HttpSession session = request.getSession( false );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        ManagerPayments.pay( new BancomatStrategy( firstName, cartNumber, cvv, dateOfExpiry), total);
        // inserire gli ordini nel DB
        try {
            OrderDAO.insertOrder( myCart.getMyCart(), email, total);
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in BancomatServlet/doPost" + e);
        }
        HelperController.setVarThankYouPage( request, session, myCart, country, firstName, lastName, address,
                shippingAddress, stateCountry, postalCode, email, phone, orderNotes );
        request.getRequestDispatcher( "jsp/thank_you.jsp" ).forward( request,response );
    }
}
