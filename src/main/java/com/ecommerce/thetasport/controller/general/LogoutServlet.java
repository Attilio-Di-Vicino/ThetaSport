package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Questo è il servlet che gestisce il logout dell'utente.
 * Invalida la sessione corrente e reindirizza l'utente alla homepage.
 * Se non esiste una sessione, non fa nulla.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "LogoutServlet", value = "/LogoutServlet" )
public class LogoutServlet extends HttpServlet {

    /**
     * Invalida la sessione corrente e reindirizza l'utente alla homepage.
     * Se non esiste una sessione, non fa nulla.
     *
     * @param request la richiesta fatta dal browser
     * @param response la risposta da restituire
     * @throws ServletException Eccezione generica per segnalare eventuali errori
     * @throws IOException Eccezione sollevata in caso di errore di input/output
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            session.invalidate(); // the session is invalidated by making all attributes in the session null and void
        }
        HelperController.nullSession( request );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }
}