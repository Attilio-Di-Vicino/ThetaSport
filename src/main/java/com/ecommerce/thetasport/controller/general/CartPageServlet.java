package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * La classe CartPageServlet rappresenta la servlet utilizzata per visualizzare la pagina del carrello.
 * La classe estende la classe HttpServlet e implementa il metodo doGet, che viene invocato quando si richiede
 * la pagina del carrello attraverso una richiesta HTTP GET. Il metodo doGet invoca il metodo cartPage della classe
 * HelperController, che si occupa di generare la pagina HTML del carrello.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "CartPageServlet", value = "/CartPageServlet" )
public class CartPageServlet extends HttpServlet {

    /**
     * Metodo GET che visualizza la pagina del carrello.
     *
     * @param request richiesta HTTP
     * @param response risposta HTTP
     * @throws ServletException in caso di errore nel Servlet
     * @throws IOException in caso di errore di Input/Output
     */
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.cartPage( request,response );
    }
}