package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * La classe EditDescriptionProductServlet è una servlet che gestisce la modifica della descrizione di un singolo prodotto.
 * Riceve una richiesta HTTP POST contenente la nuova descrizione del prodotto e il suo codice identificativo.
 * Dopodiché, utilizzando la classe ProductDAO, modifica la descrizione del prodotto nel database.
 * Infine, reindirizza l'utente alla lista dei prodotti e visualizza la pagina di amministrazione.
 *
 *  @author Theta Sport
 *  @version 1.0
 */
@WebServlet(name = "EditDescriptionProductServlet", value = "/EditDescriptionProductServlet")
public class EditDescriptionProductServlet extends HttpServlet {

    /**
     * Gestisce una richiesta HTTP POST per modificare la descrizione di un singolo prodotto.
     * Riceve una richiesta HTTP POST contenente la nuova descrizione del prodotto e il suo codice identificativo.
     * Utilizzando la classe ProductDAO, modifica la descrizione del prodotto nel database.
     * Infine, reindirizza l'utente alla lista dei prodotti e visualizza la pagina di amministrazione.
     *
     * @param request l'oggetto HttpServletRequest contenente la richiesta HTTP
     * @param response l'oggetto HttpServletResponse contenente la risposta HTTP
     * @throws ServletException se si verifica un errore durante la gestione della richiesta
     * @throws IOException se si verifica un errore di input o output durante la gestione della richiesta
     */
    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String description = request.getParameter("descriptionSingleProduct");
        int code = Integer.parseInt(request.getParameter("codeSingleProduct"));
        ProductDAO.editDescriptionProduct(description,code);
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, true, false, false,
                false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
