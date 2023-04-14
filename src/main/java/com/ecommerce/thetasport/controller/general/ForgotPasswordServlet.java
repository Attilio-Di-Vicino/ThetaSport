package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ForgotPasswordServlet viene utilizzato se l'utente non ricorda la password, <br>
 * c'è un riferimento a questo servlet nel file login.jsp <br>
 * che invoca il metodo get che inoltra la richiesta alla pagina dedicata per richiedere la password dimenticata. <br>
 * Nel file ForgotPassword.jsp c'è un form che invoca il metodo post del seguente servlet <br>
 *
 * @author Theta Sport
 * @version 1.0
 */
@WebServlet( name = "ForgotPasswordServlet", value = "/ForgotPasswordServlet" )
public class ForgotPasswordServlet extends HttpServlet {

    /**
     * Il metodo doGet inoltra la richiesta alla pagina jsp/forgot_password.jsp.
     *
     * @param request Richiesta fatta tramite un browser
     * @param response Risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione di I/O
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/forgot_password.jsp" ).forward( request, response );
    }


    /**
     * Il metodo doPost recupera inizialmente i parametri inoltrati tramite protocollo http dal client tramite un form, <br>
     * il form di riferimento già effettua vari controlli utilizzando HTML5, tuttavia, è necessario eseguire un controllo lato server, <br>
     * infatti controlliamo se l'email inserita dal client esiste nel database, essendo l'attributo univoco e identificativo. <br>
     * Se l'email è presente, viene inoltrata una email fake con la rispettiva password, <br>
     * altrimenti comparirà un messaggio di errore.
     *
     * @param request Richiesta fatta tramite un browser
     * @param response Risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione di I/O
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        response.setContentType( "text/html" );
        String to = request.getParameter( "email" );
        try {
            if ( UserDAO.userMailExist( to ) ) {
                // sendMail
                System.out.println( "Email send to:" + to );
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