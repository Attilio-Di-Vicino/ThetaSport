package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Questa classe rappresenta un servlet che si occupa di recuperare le informazioni di un prodotto specifico dal
 * database, utilizzando il suo codice identificativo, e di visualizzarle nella pagina di amministrazione del sito.
 * Il servlet riceve una richiesta HTTP GET con il parametro "codeProduct" contenente il codice del prodotto da
 * visualizzare. Successivamente, il servlet recupera le informazioni del prodotto dal database utilizzando il DAO,
 * imposta l'attributo "singleProduct" della richiesta con queste informazioni e inoltra la richiesta e la risposta
 * alla pagina JSP per la visualizzazione della pagina di amministrazione.
 *
 *  @author Theta Sport
 *  @version 1.0
 */
@WebServlet( name = "EditProductServlet", value = "/EditProductServlet" )
public class EditProductServlet extends HttpServlet {

    /**
     * Questo metodo viene invocato dal container Servlet quando arriva una richiesta HTTP GET.
     *
     * @param request  la richiesta HTTP ricevuta dal client
     * @param response la risposta HTTP da inviare al client
     * @throws ServletException se si verifica un'eccezione durante l'elaborazione della richiesta
     * @throws IOException      se si verifica un'eccezione durante l'invio della risposta
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperControllerAdmin.setAdminPage( request, false, false,
                false, true,
                false, false, false, false, false );
        int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
        try {
            request.setAttribute( "singleProduct", ProductDAO.getSingleProduct( code ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in EditProductServlet" + e );
        }
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
