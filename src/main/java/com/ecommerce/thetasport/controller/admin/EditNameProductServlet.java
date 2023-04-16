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
 * La classe EditPriceProductServlet gestisce la modifica del prezzo di un prodotto.
 * Riceve i parametri "priceSingleProduct" e "codeSingleProduct" tramite una richiesta HTTP POST, li converte rispettivamente
 * in un double e in un int, utilizzandoli per chiamare il metodo editPriceProduct della classe ProductDAO.
 * Inoltre, richiama i metodi ForwardProductList e setAdminPage della classe HelperController e HelperControllerAdmin rispettivamente,
 * e infine, inoltra la richiesta e la risposta all'area amministrativa protetta jsp/protected_admin_area.jsp.
 *
 *  @author Theta Sport
 *  @version 1.0
 */

@WebServlet(name = "EditNameProductServlet", value = "/EditNameProductServlet")
public class EditNameProductServlet extends HttpServlet {

    /**
     * Gestisce le richieste GET per la modifica del nome del prodotto.
     * @param request La richiesta HTTP ricevuta dal client.
     * @param response La risposta HTTP da restituire al client.
     * @throws ServletException Se si verifica un errore durante la gestione della richiesta.
     * @throws IOException Se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Gestisce le richieste POST per modificare il nome di un prodotto.
     * @param request La richiesta HTTP ricevuta dal client.
     * @param response La risposta HTTP da restituire al client.
     * @throws ServletException Se si verifica un errore durante la gestione della richiesta.
     * @throws IOException Se si verifica un errore di I/O durante la gestione della richiesta.
     */
    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("nameSingleProduct");
        int code = Integer.parseInt(request.getParameter("codeSingleProduct"));
        ProductDAO.editNameProduct(name,code);
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, false,
                true, false, false, false, false,
                false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
