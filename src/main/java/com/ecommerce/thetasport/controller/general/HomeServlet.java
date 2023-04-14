package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * HomeServlet risponde alla radice dell'applicazione web e verifica se esiste una sessione. <br>
 * Questo controllo viene eseguito per gestire il caso in cui il client torna alla pagina principale durante la navigazione <br>
 * ma ha già effettuato l'accesso, quindi c'è una sessione aperta. <br>
 * Se la sessione non esiste, viene creata e impostato il login su 0, quindi non connesso.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "HomeServlet", value = "/" )
public class HomeServlet extends HttpServlet {

    /**
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.verifyLoginAndCart( request );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }
}
