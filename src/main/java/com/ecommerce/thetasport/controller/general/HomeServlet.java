package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * HomeServlet risponde alla root della web application di conseguenza verifica l'esistenza di una sessione,
 * questo controllo viene eseguito per gestire il caso in cui il client durante la navigazione torna alla pagina home,
 * ma ha gia eseguito il login, e quindi esiste una sessione aperta.
 * Nel caso in cui la sessione non esista allora la crea ed setta il login a 0, quindi non loggato.
 */
@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {

    /**
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.verifyLoginAndCart(request);
        HelperController.ForwardProductList(request);
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }
}
