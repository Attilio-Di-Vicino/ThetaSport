package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


/**
 * Questa classe rappresenta un servlet che gestisce l'aggiunta di un prodotto al carrello.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "AddCartServlet", value = "/AddCartServlet" )
public class AddCartServlet extends HttpServlet {

    /**
     * Questo metodo gestisce la richiesta HTTP GET per l'aggiunta di un prodotto al carrello.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client.
     * @param response l'oggetto HttpServletResponse che contiene la risposta del server.
     * @throws ServletException se si verifica un'eccezione Servlet.
     * @throws IOException      se si verifica un'eccezione IO.
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String landingPage = request.getParameter("landingPage");
        if ( landingPage == null ) {
            throw new NullPointerException( "landing page is null." );
        }
        if ( !landingPage.equals( "cart" ) ) {
            HelperController.addCartCaseIndexOrSingleProduct( request,response );
        } else {
            HelperController.addCartCaseCart( request, response );
        }
    }
}