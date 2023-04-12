package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
/**
 * Logout Servlet fetches the actual session and invalidates it, redirecting to the home page. <br>
 * Is superfluously checked if there is a session, because if this servlet is invoked, <br>
 * then a session exists.
 */
@WebServlet( name = "LogoutServlet", value = "/LogoutServlet" )
public class LogoutServlet extends HttpServlet {

    /**
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            session.invalidate(); // the session is invalidated by making all attributes in the session null and void
        }
        HelperController.nullSession( request );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }
}