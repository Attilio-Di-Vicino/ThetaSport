package com.ecommerce.thetasport.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * La servlet per richiedere l'aggiunta di un nuovo prodotto.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "RequestAddProductServlet", value = "/RequestAddProductServlet" )
public class RequestAddProductServlet extends HttpServlet {

    /**
     * Processa la richiesta GET per l'aggiunta di un nuovo prodotto.
     *
     * @param request  l'oggetto HttpServletRequest che contiene la richiesta del client.
     * @param response l'oggetto HttpServletResponse che contiene la risposta da inviare al client.
     * @throws ServletException se si verifica un errore nella gestione della richiesta.
     * @throws IOException      se si verifica un errore di input/output durante la gestione della richiesta.
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html");
        HelperControllerAdmin.setAdminPage( request, true, false, false, false,
                false, false, false, false, false );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
