package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * LogoutServlet in maniera semplice recupera la sessione attuale e la invalida, riderizionando alla home page,
 * viene effettuato un controllo superfluo alla sessione, perchè nel momento in cui viene invocata questa servlet
 * allora esiste una sessione.
 */
@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    /**
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            session.invalidate(); // invalido la sessione rendendo nulli tutti gli attributi presenti nella sessione
        }
        HelperController.nullSession( request );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }
}