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
 * LoginServlet è utilizzato per gestire il login, c'è un riferimento a questo servlet nella index.jsp. <br>
 * Quindi viene invocato il metodo get che poi inoltra la richiesta alla pagina di login dedicata. <br>
 * Nella pagina di login c'è un form che invoca il metodo post del servlet seguente, <br>
 * quindi vengono eseguiti i controlli di login (tramite una catena di responsabilità), e il login è gestito.
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "LoginServlet", value = "/LoginServlet" )
public class LoginServlet extends HttpServlet {

    /**
     * @param request  Richiesta effettuata tramite un browser
     * @param response Risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException      Segnala che si è verificata un'eccezione di I/O
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
    }

    /**
     * doPost viene invocato tramite il form sulla pagina di login dedicata, inizialmente memorizza localmente <br>
     * i parametri passati tramite protocollo http, invoca le procedure dedicate alla verifica del login, <br>
     * e gestisce il risultato tramite uno switch.
     *
     * @param request  Richiesta effettuata tramite un browser
     * @param response Risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException      Segnala che si è verificata un'eccezione di I/O
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        // Login verification through the CoR
        ToHandle result = ManagerLogin.login( email, password, "admin" );
        switch ( result ) {
            case UNREGISTERED: HelperController.loggedError( request, response, "Please check your email or register!" );
                break;
            case WRONG_PASSWORD: HelperController.loggedError( request, response, "Please check your password" );
                break;
            case USER_ACCESS:
                try {
                    HelperController.loggedOrRegistrationSuccessful( request, response, email, 1, "jsp/index.jsp" );
                } catch ( SQLException e ) {
                    throw new RuntimeException( "SQL Error in LoginServlet/UserAccess" + e );
                } catch ( ClassNotFoundException ce ) {
                    throw new RuntimeException( "Error in LoginServlet/UserAccess" + ce );
                }
                break;
            case ADMIN_ACCESS:
                try {
                    HelperController.loggedOrRegistrationSuccessful( request, response, email, 2, "jsp/protected_admin_area.jsp" );
                } catch ( SQLException e ) {
                    throw new RuntimeException( "SQL Error in LoginServlet/AdminAccess" + e );
                } catch ( ClassNotFoundException ce ) {
                    throw new RuntimeException( "Error in LoginServlet/AdminAccess" + ce );
                }
                break;
            default: HelperController.loggedError( request, response, "Abbiamo riscontrato un errore, riprova." );
                break;
        }
    }
}