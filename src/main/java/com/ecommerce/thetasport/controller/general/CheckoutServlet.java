package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * La classe CheckoutServlet si occupa di mostrare la pagina di checkout e gestire le operazioni di checkout.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "CheckoutServlet", value = "/CheckoutServlet" )
public class CheckoutServlet extends HttpServlet {

    /**
     * Metodo che gestisce la richiesta GET per la visualizzazione della pagina di checkout.
     *
     * @param request  HttpServletRequest contenente la richiesta HTTP
     * @param response HttpServletResponse contenente la risposta HTTP
     * @throws ServletException se si verifica un errore durante l'esecuzione del metodo doGet
     * @throws IOException      se si verifica un errore di I/O durante l'esecuzione del metodo doGet
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
}