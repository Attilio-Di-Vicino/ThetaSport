package com.ecommerce.thetasport.controller.user;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * RegistrationServlet viene utilizzata per la registrazione di un utente, nell'login.jsp è presente un riferimento a questa servlet
 * invocando quindi il metodo get che inoltra la richiesta alla pagina dedicata alla registrazione.
 * Quindi nella pagina dedicata alla registrazione è presente un form che invoca il metodo post della seguente servlet.
 */
@WebServlet( name = "RegistrationServlet", value = "/RegistrationServlet" )
public class RegistrationServlet extends HttpServlet {

    /**
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/registration.jsp" ).forward( request, response );
    }

    /**
     * Il metodo post inizialmete recupera i paramentri inoltrati tramite protocollo http dal client tramite un form,
     * il form di riferimento gia esegue vari controllo sfruttando HTML5 e javascript, è necessario però effettuare un controllo
     * anche lato server, infatti andiamo a controllare se la email inserita dal client è presente nel database,
     * essendo un'attributo unico ed identificativo, nel caso è presente nel database la registrazione fallisce
     * altrimenti la registrazione avrà successso.
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String name = request.getParameter( "name" );
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        try {
            if ( !ManagerLogin.userMailExist( email ) ) {
                ManagerLogin.registrationUser( name, email, password );
                // Se si sta registrando è sicuramente un User e non un admin
                HelperController.loggedOrRegistrationSuccessful( request, response, email, 1, "jsp/index.jsp" );
            } else {
                request.setAttribute( "errorMessage", "Attention, the email entered already exists!" );
                request.getRequestDispatcher( "jsp/registration.jsp" ).forward( request, response );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in RegistrationServlet/doPost" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in RegistrationServlet/doPost" + ce );
        }
    }
}