package com.ecommerce.thetasport.controller.user;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.dao.UserDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * RegistrationServlet is used to register a user, in the login.jsp there is a reference to this servlet<br>
 * that invokes the get method which forwards the request to the registration page.<br>
 * Then on the registration page there is a form that invokes the post method of the following servlet.<br>
 */
@WebServlet( name = "RegistrationServlet", value = "/RegistrationServlet" )
public class RegistrationServlet extends HttpServlet {

    /**
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/registration.jsp" ).forward( request, response );
    }

    /**
     * The post method initially retrieves the parameters forwarded via http protocol from the client via a form,<br>
     * the reference form already performs various check by exploiting HTML5 and javascript, it is necessary however to perform a check<br>
     * also server sided, in fact we go to check if the email entered by the client is present in the database,<br>
     * being a unique and identifying attribute, in case it is present in the database the registration will fail<br>
     * otherwise the registration will succeed.
     *
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String name = request.getParameter( "name" );
        String email = request.getParameter( "email" );
        String password = request.getParameter( "password" );
        try {
            if ( !UserDAO.userMailExist( email ) ) {
                UserDAO.registration( name, email, password );
                // If he is registering he is definitely a User and not an admin
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