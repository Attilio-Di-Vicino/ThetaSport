package com.ecommerce.thetasport.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Servlet implementation class SendOfferServlet
 * Questa classe rappresenta la servlet per inviare offerte da parte dell'amministratore.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "SendOfferServlet", value = "/SendOfferServlet" )
public class SendOfferServlet extends HttpServlet {

    /**
     * Questo metodo gestisce le richieste POST e invia l'offerta all'utente.
     *
     * @param request  L'oggetto HttpServletRequest che contiene la richiesta del client.
     * @param response L'oggetto HttpServletResponse che contiene la risposta del server.
     * @throws ServletException se si verifica un errore nella servlet.
     * @throws IOException      se si verifica un errore di input/output.
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String email = request.getParameter( "email" );
        String offerListProduct = request.getParameter( "offerListProduct" );
        System.out.println( "Offer for " + email + " success" + offerListProduct );
        request.setAttribute( "email", email );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, false, false, false, true );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}