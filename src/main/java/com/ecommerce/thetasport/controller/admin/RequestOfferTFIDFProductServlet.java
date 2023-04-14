package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.service.tfidf.CustomPriorityQueue;
import com.ecommerce.thetasport.service.tfidf.ManagerTFIDF;
import com.ecommerce.thetasport.service.tfidf.TFIDFCalculator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * La servlet per richiedere un offerta personalizzata ad un utente.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "RequestOfferTFIDFProductServlet", value = "/RequestOfferTFIDFProductServlet" )
public class RequestOfferTFIDFProductServlet extends HttpServlet {

    /**
     * Metodo che gestisce le richieste GET. setta i vari flag per la visualizzazione a schermo per inviare offerte
     *
     * @param request  l'oggetto HttpServletRequest che contiene le informazioni sulla richiesta HTTP
     * @param response l'oggetto HttpServletResponse che contiene le informazioni sulla risposta HTTP
     * @throws ServletException se si verifica un errore durante la gestione della richiesta
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta
     */
    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, true, false, false, false );
        try {
            request.setAttribute( "userList", UserDAO.getUsersMailMinusOne( "admin" ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in RequestOfferTFIDFProductServlet" + e );
        }
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }

    /**
     * Metodo che gestisce le richieste POST. Esso recupre dalla request una mail dell'utente al quale <br>
     * si vuole inviare l'offerta, quindi si utilizza l'algoritmo {@link TFIDFCalculator} <br>
     * Viene richiamato il metodo {@link ManagerTFIDF#TFIDFSingleUsers(String)} il quale restituisce <br>
     * una map con chiave la mail dell'utente in questione e valore una coda di massima priorità la quale conterrà <br>
     * una map con chiave la mail di un utente e il corrispetivo valore TF-IDF calcolato <br>
     * Dopodiche si genera un offerta utilizzando il metodo {@link ManagerTFIDF#getAllOffersMoreRelated(Map)} <br>
     * considerando l'utente con TF-IDF maggiore se quest'ultima è vuota allora si prosegue in ordine descrescente <br>
     * sul valore TF-IDF associato ad ogni utente.
     *
     * @param request  l'oggetto HttpServletRequest che contiene le informazioni sulla richiesta HTTP
     * @param response l'oggetto HttpServletResponse che contiene le informazioni sulla risposta HTTP
     * @throws ServletException se si verifica un errore durante la gestione della richiesta
     * @throws IOException      se si verifica un errore di I/O durante la gestione della richiesta
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String email = request.getParameter( "email" );
        Map< String, CustomPriorityQueue< String, Double > > mapResult = ManagerTFIDF.TFIDFSingleUsers( email );
        Map< String, List<List<String>> > offerUserMap = ManagerTFIDF.getAllOffersMoreRelated( mapResult );
        request.setAttribute( "email", email );
        List< List< String > > productList = offerUserMap.get( email );
        // nel caso il cui la differenza insiemistica è minore di 0, quindi la lista dei prodotti
        // da offrire risulta essere nulla allora procediamo con il secondo utente più simile ad esso
        // considerando che all'interno del metodo getAllOffersMoreRelated viene eseguito una poll sulla coda di massima priorità
        if ( productList.isEmpty() ) {
            while ( productList.isEmpty() && !mapResult.get( email ).isEmpty() ) {
                productList = ManagerTFIDF.getAllOffersMoreRelated( mapResult ).get( email );
            }
        }
        request.setAttribute( "offerListProduct", productList );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, false, false, true, false );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
