package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.dao.OrderDAO;
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

/**
 * Questa classe rappresenta il servlet che gestisce il pagamento con carta di credito.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "CreditCartServlet", value = "/CreditCartServlet" )
public class CreditCartServlet extends HttpServlet {

    /**
     * Questo metodo gestisce la richiesta GET al servlet, mostrando la pagina di checkout con l'opzione di pagamento
     * con carta di credito selezionata.
     *
     * @param request  l'oggetto HttpServletRequest che contiene le informazioni sulla richiesta
     * @param response l'oggetto HttpServletResponse che contiene le informazioni sulla risposta
     * @throws ServletException se si verifica un'eccezione di tipo servlet
     * @throws IOException      se si verifica un'errore di input/output
     */
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request );
        request.setAttribute( "creditCart", true );
        request.setAttribute( "bancomat", false );
        request.setAttribute( "cash", false );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request, response );
    }

    /**
     * Questo metodo gestisce la richiesta POST al servlet, processando il pagamento con carta di credito e inserendo
     * l'ordine nel database.
     *
     * @param request  l'oggetto HttpServletRequest che contiene le informazioni sulla richiesta
     * @param response l'oggetto HttpServletResponse che contiene le informazioni sulla risposta
     * @throws ServletException se si verifica un'eccezione di tipo servlet
     * @throws IOException      se si verifica un'errore di input/output
     */
    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String country = request.getParameter( "c_country" );
        String firstName = request.getParameter( "c_fname" );
        String lastName = request.getParameter( "c_lname" );
        String cartNumber = request.getParameter( "c_cart_number" );
        String cvv = request.getParameter( "c_cvv" );
        String dateOfExpiry = request.getParameter( "c_date_expiry" );
        String address = request.getParameter( "c_address" );
        String shippingAddress = request.getParameter( "c_s_address" );
        String stateCountry = request.getParameter( "c_state_country" );
        String postalCode = request.getParameter( "c_postal_zip" );
        String email = request.getParameter( "c_email_address" );
        String phone = request.getParameter( "c_phone" );
        String orderNotes = request.getParameter( "c_order_notes" );

        HttpSession session = request.getSession( false );
        Cart myCart = ( Cart ) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( myCart );
        double total = shoppingCartVisitor.getTotal();
        ManagerPayments.pay( new CreditCardStrategy( firstName, cartNumber, cvv, dateOfExpiry ), total );
        // inserire gli ordini nel DB
        try {
            OrderDAO.insertOrder( myCart.getMyCart(), email, total);
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in CreditCartServlet/doPost" + e );
        }
        HelperController.setVarThankYouPage( request, session, myCart, country, firstName, lastName, address,
                shippingAddress, stateCountry, postalCode, email, phone, orderNotes );
        request.getRequestDispatcher( "jsp/thank_you.jsp" ).forward( request,response );
    }
}
