package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe rappresenta il servlet per la rimozione di un oggetto dal carrello.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "RemoveObjectCartServlet", value = "/RemoveObjectCartServlet" )
public class RemoveObjectCartServlet extends HttpServlet {


    /**
     * Metodo che gestisce la richiesta GET per la rimozione di un oggetto dal carrello.
     *
     * @param request l'oggetto HttpServletRequest contenente la richiesta del client.
     * @param response l'oggetto HttpServletResponse che viene utilizzato per inviare la risposta al client.
     *
     * @throws ServletException se si verifica un errore nella gestione della richiesta.
     * @throws IOException se si verifica un errore nell'invio della risposta al client.
     * @throws RuntimeException se si verifica un errore durante l'esecuzione del metodo removeObjectCart
     *                           della classe HelperController.
     */
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            HelperController.removeObjectCart( request, response );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in RemoveObjectCartServlet/doGet" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in RemoveObjectCartServlet/doGet" + ce );
        }
    }
}