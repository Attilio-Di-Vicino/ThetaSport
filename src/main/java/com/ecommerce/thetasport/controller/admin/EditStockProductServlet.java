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
 * Questa classe rappresenta un servlet che si occupa di modificare il valore del campo stock di un prodotto
 * nel database, ricevendo i dati dalla richiesta POST. In seguito, il servlet effettua un reindirizzamento alla
 * lista dei prodotti, sempre attraverso il controller di supporto HelperController.
 *
 *  @author Theta Sport
 *  @version 1.0
 */
@WebServlet( name = "EditStockProductServlet", value = "/EditStockProductServlet" )
public class EditStockProductServlet extends HttpServlet {

    /**
     * Questo metodo viene invocato dal container Servlet quando arriva una richiesta HTTP POST.
     *
     * @param request  la richiesta HTTP ricevuta dal client
     * @param response la risposta HTTP da inviare al client
     * @throws ServletException se si verifica un'eccezione durante l'elaborazione della richiesta
     * @throws IOException      se si verifica un'eccezione durante l'invio della risposta
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        int stock = Integer.parseInt( request.getParameter( "stockSingleProduct" ) );
        int code = Integer.parseInt( request.getParameter( "codeSingleProduct" ) );
        ProductDAO.editStockProduct( stock, code );
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, true, false, false,
                false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
