package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * RequestEditProductServlet è una servlet che gestisce la richiesta di modifica di un prodotto <br>
 * da parte di un amministratore. Questa servlet viene invocata quando un utente amministratore <br>
 * accede all'area riservata del sito e sceglie di modificare un prodotto.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet(name = "RequestEditProductServlet", value = "/RequestEditProductServlet")
public class RequestEditProductServlet extends HttpServlet {

    /**
     * Questo metodo gestisce la richiesta HTTP GET. Viene invocato quando un utente amministratore <br>
     * accede all'area riservata del sito e sceglie di modificare un prodotto. <br>
     * Il metodo ottiene la lista di tutti i prodotti e la visualizza nella pagina protetta dell'area amministrativa.
     *
     * @param request  l'oggetto HttpServletRequest che contiene le informazioni sulla richiesta HTTP
     * @param response l'oggetto HttpServletResponse che contiene le informazioni sulla risposta HTTP
     * @throws ServletException se si verifica un errore durante l'elaborazione della richiesta
     * @throws IOException      se si verifica un errore di I/O durante l'elaborazione della richiesta
     */
    @Override
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, false, true, false,
                false, false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
