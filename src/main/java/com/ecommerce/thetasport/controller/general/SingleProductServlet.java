package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Questa classe rappresenta il servlet per la visualizzazione di un singolo prodotto.
 * Riceve una richiesta GET e mostra la pagina HTML corrispondente al prodotto selezionato.
 * Verifica inoltre se l'utente è loggato e se ha un carrello, utilizzando il metodo verifyLoginAndCart
 * della classe HelperController.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "SingleProductServlet", value = "/SingleProductServlet" )
public class SingleProductServlet extends HttpServlet {

    /**
     * Metodo che gestisce la richiesta GET per la visualizzazione del prodotto selezionato.
     *
     * @param request l'oggetto HttpServletRequest contenente la richiesta del client.
     * @param response l'oggetto HttpServletResponse che viene utilizzato per inviare la risposta al client.
     *
     * @throws ServletException se si verifica un errore nella gestione della richiesta.
     * @throws IOException se si verifica un errore nell'invio della risposta al client.
     */
    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.verifyLoginAndCart( request );
        HelperController.singleProduct( request, response );
    }
}