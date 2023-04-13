package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * AdminProtectedServlet viene richiamata ogni qualvolta si tenta di accedere
 * alla pagina riservata all'admin, semplicente controlla la sessione attuale,
 * e quindi ne verifica l'esistenza, se non esiste di conseguenza l'utente non può essere registarto,
 * rimandando cosi alla pagina dedicata al login, altrimenti verifica lo stato del login precedentemete settato
 * nei casi in cui il login è diverso da "2", significa che non è loggato come admin, quindi questo include
 * i casi in il login sia "null", "0", "1", di conseguenza riporta alla pagina dedicata al login, altrimenti prosegue.
 */

/**
 * Quando un servlet viene chiamato per la prima volta, l'oggetto risposta è fresco e nuovo:
 * le sue intestazioni non sono state impostate, i suoi buffer sono vuoti e nessun dato è stato scritto sul client.
 * Tuttavia, non appena il codice di stato o una qualsiasi delle intestazioni è stato scritto,
 * o potenzialmente scritto, nel client, o quando i dati sono stati, o potrebbero essere stati,
 * scritti nel body stream, è possibile essere suscettibile all'errore IllegalStateException.
 * Il problema che questa eccezione sta segnalando è che i nuovi dati che stai (o potresti scrivere)
 * non sono coerenti con i dati che sono già stati impostati e quindi irrevocabilmente inviati ("commissionati")
 * al client.
 * Due varianti comuni di questa eccezione sono "java.lang.IllegalStateException:
 * intestazione già inviata" e "java.lang.IllegalStateException: impossibile inoltrare
 * come Output Stream o Writer è già stato ottenuto".
 * "Intestazione già inviata" significa che una o più intestazioni sono state inviate
 * al client, quindi non puoi reimpostare quell'intestazione.
 * "Output Stream o Writer è già stato ottenuto" significa che poiché il servlet chiamante
 * ha già chiamato response.getWriter() o response.getOutputStream(), ciò contamina il flusso di dati,
 * poiché la risposta è stata (o potrebbe essere stata) scritta su già, rendendolo inadatto per l'inoltro.
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
            System.out.println( "Non hai effettuato il login" );
        } else {
            int isLogged = (int) session.getAttribute( "isLogged" );
            if ( isLogged != 2 ){
                request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
                System.out.println( "Non sei loggato, oppure sei loggato ma non sei admin" );
            } else {
                HelperControllerAdmin.setAdminPage( request, false );
                /*request.setAttribute( "addproduct", false );
                request.setAttribute( "editproduct", false );
                request.setAttribute( "editsingleproduct", false );
                request.setAttribute( "salesupdates", false );
                request.setAttribute( "sendoffers", false );
                request.setAttribute( "queydone", false );
                request.setAttribute( "categoryList", ManagerProduct.getCategoryList() );
                try {
                    request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
                    request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
                    request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
                    request.setAttribute( "orderList", OrderDAO.getTotalOrdersUsersBeanList() );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }*/
                request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
                System.out.println("Sei Admin");
            }
        }
    }
}
