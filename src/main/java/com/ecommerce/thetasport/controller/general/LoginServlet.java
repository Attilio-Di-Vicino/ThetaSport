package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import com.ecommerce.thetasport.service.loginCor.ToHandle;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * LoginServlet viene utilizzata per la gestione del login, nell'index.jsp è presente un riferimento a questa servlet
 * invocando quindi il metodo get che inoltra quindi la richiesta alla pagina dedicata al login.
 * Quindi nella pagina dedicata al login è presente un form che invoca il metodo post della seguente servlet,
 * quindi vengono eseguiti i controlli per il login (CoR), ed viene gestito l'accesso.
 */
@WebServlet( name = "LoginServlet", value = "/LoginServlet" )
public class LoginServlet extends HttpServlet {

    /**
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

    /**
     * doPost viene invocato tramite il form presente nella pagina dedicata del login, inizialmente memorizza localmente
     * i parametri passati attraverso protocollo http, richiama la procedura dedicata alla verifica del login, e ne
     * gestisce il risultato tramite uno switch.
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        // Effettuo la verifica del login tramite il CoR
        ToHandle result = ManagerLogin.login( email, password, "admin" );
        switch (result) {
            case UNREGISTERED: HelperController.loggedError( request, response, "Please check your email or register!" );
                break;
            case WRONG_PASSWORD: HelperController.loggedError( request, response, "Please check your password" );
                break;
            case USER_ACCESS:
                try {
                    HelperController.loggedOrRegistrationSuccessful( request, response, email, 1, "jsp/index.jsp" );
                } catch ( SQLException e ) {
                    throw new RuntimeException( "SQL Error in LoginServlet/UserAccess" + e );
                } catch ( ClassNotFoundException ce) {
                    throw new RuntimeException( "Error in LoginServlet/UserAccess" + ce );
                }
                break;
            case ADMIN_ACCESS:
                try {
                    HelperController.loggedOrRegistrationSuccessful( request, response, email, 2, "jsp/protected_admin_area.jsp" );
                } catch ( SQLException e ) {
                    throw new RuntimeException( "SQL Error in LoginServlet/AdminAccess" + e );
                } catch ( ClassNotFoundException ce) {
                    throw new RuntimeException( "Error in LoginServlet/AdminAccess" + ce );
                }
                break;
            default: HelperController.loggedError( request, response, "Abbiamo riscontrato un errore, riprova." );
                break;
        }
    }
}