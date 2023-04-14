package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe rappresenta il servlet per la rimozione del carrello.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "RemoveCartServlet", value = "/RemoveCartServlet" )
public class RemoveCartServlet extends HttpServlet {

    /**
     * Metodo che gestisce la richiesta GET per la rimozione del carrello.
     *
     * @param request l'oggetto HttpServletRequest contenente la richiesta del client.
     * @param response l'oggetto HttpServletResponse che viene utilizzato per inviare la risposta al client.
     *
     * @throws ServletException se si verifica un errore nella gestione della richiesta.
     * @throws IOException se si verifica un errore nell'invio della risposta al client.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione dei metodi removeCartCaseIndexOrSingleProduct
     *                           o removeCartCaseCart della classe HelperController.
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String landingPage = request.getParameter( "landingPage" );
        try {
            if ( !landingPage.equals( "cart" ) ) {
                HelperController.removeCartCaseIndexOrSingleProduct( request, response );
            } else {
                HelperController.removeCartCaseCart( request, response );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in RemoveCartServlet/doGet" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in RemoveCartServlet/doGet" + ce );
        }
    }
}