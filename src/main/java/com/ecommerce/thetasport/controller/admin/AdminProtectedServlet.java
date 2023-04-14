package com.ecommerce.thetasport.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Questa classe è un servlet che viene utilizzato per proteggere l'area amministrativa del sito
 * web. Se un utente che non ha l'autorizzazione di amministratore tenta di accedere all'area
 * amministrativa, viene reindirizzato alla pagina di login. In caso contrario, l'utente viene
 * reindirizzato all'area amministrativa e viene stampato a console un messaggio di conferma
 * che indica che l'utente ha l'autorizzazione di amministratore. Questo servlet risponde solo
 * alle richieste HTTP GET e non riceve parametri da URL. Le eccezioni ServletException e
 * IOException possono essere sollevate durante l'elaborazione delle richieste HTTP.
 *
 *  @author Theta Sport
 *  @version 1.0
 */

@WebServlet( name = "AdminProtectedServlet", value = "/AdminProtectedServlet" )
public class AdminProtectedServlet extends HttpServlet {

    /**
     * @param request richiesta effettuata tramite un browser
     * @param response risposta
     * @throws ServletException Definisce un'eccezione generale che un servlet può generare quando incontra difficoltà
     * @throws IOException Segnala che si è verificata un'eccezione I/O di qualche tipo
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HttpSession session = request.getSession( false );
        if ( session == null ) {
            request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
        } else {
            int isLogged = (int) session.getAttribute( "isLogged" );
            if ( isLogged != 2 ) {
                request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
            } else {
                HelperControllerAdmin.setAdminPage( request, false );
                request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
                System.out.println("Sei Admin");
            }
        }
    }
}