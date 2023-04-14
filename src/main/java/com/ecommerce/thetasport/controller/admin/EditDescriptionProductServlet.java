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
 *  Questa servlet gestisce la modifica della descrizione di un prodotto da parte dell'amministratore.
 *  Riceve la descrizione e il codice del prodotto tramite una richiesta POST e utilizza il metodo
 *  editDescriptionProduct della classe ProductDAO per effettuare la modifica nel database.
 *  Infine, reindirizza l'amministratore alla lista dei prodotti e imposta la pagina di amministrazione.
 */
@WebServlet(name = "EditDescriptionProductServlet", value = "/EditDescriptionProductServlet")
public class EditDescriptionProductServlet extends HttpServlet {

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
