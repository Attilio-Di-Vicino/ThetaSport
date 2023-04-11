package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
/**
 * HomeServlet respond to web application's root and verify whether a session exists.
 * This check is executed to handle the case where the client returns to the home page while browsing
 * but has already logged in, so there is an open session.
 * If the session does not exist, it creates and sets the log in to 0, so not logged in.
 */


@WebServlet( name = "HomeServlet", value = "/" )
public class HomeServlet extends HttpServlet {

    /**
     *
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */




    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.verifyLoginAndCart( request );
        HelperController.ForwardProductList( request );
        request.getRequestDispatcher( "jsp/index.jsp" ).forward( request, response );
    }
}
