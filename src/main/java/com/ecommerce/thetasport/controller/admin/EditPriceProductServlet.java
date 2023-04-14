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
 */
@WebServlet(name = "EditPriceProductServlet", value = "/EditPriceProductServlet")
public class EditPriceProductServlet extends HttpServlet {
    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        double price = Double.parseDouble(request.getParameter("priceSingleProduct"));
        int code = Integer.parseInt(request.getParameter("codeSingleProduct"));
        ProductDAO.editPriceProduct(price,code);
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, true, false, false,
                false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
