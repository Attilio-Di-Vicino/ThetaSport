package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ForgotPasswordServlet viene utilizzata nel caso in cui l'utente non ricorda la password, nell'login.jsp è presente un riferimento a questa servlet
 * invocando quindi il metodo get che inoltra la richiesta alla pagina dedicata alla richiesta della password dimenticata.
 * Quindi nella pagina dedicata è presente un form che invoca il metodo post della seguente servlet.
 */
@WebServlet( name = "ForgotPasswordServlet", value = "/ForgotPasswordServlet" )
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/forgot_password.jsp" ).forward( request, response );
    }

    /**
     * Il metodo post inizialmete recupera i paramentri inoltrati tramite protocollo http dal client tramite un form,
     * il form di riferimento gia esegui vari controllo sfruttando HTML5, è necessario però effettuare un controllo
     * anche lato server, infatti andiamo a controllare se la email inserita dal client è presente nel database,
     * essendo un'attributo unico ed identificativo, nel caso è presente nel database viene inoltrata una fake mail
     * con la rispettiva password, altrimenti apparirà un messaggio di errore.
     *
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        response.setContentType( "text/html" );
        String to = request.getParameter( "email" );
        try {
            if ( ManagerLogin.userMailExist( to ) ) {
                // sendMail da implementare
                request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
            } else {
                request.setAttribute( "errorMessage", "Warning, the email entered does not exist!" );
                request.getRequestDispatcher( "jsp/forgot_password.jsp" ).forward( request, response );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in ForgotPasswordServlet/doPost" + e );
        }
    }
}