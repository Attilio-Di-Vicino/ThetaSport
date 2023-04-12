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
 * LoginServlet is used to manage the login, there is a reference to this servlet in the index.jsp. <br>
 * Then invoking the get method which then forward the request to the dedicated login page. <br>
 * In the login page there is a form that invoke the post method of the following servlet, <br>
 * then  the login checks are performed (via a Chain of responsibility), and the login is handled
 */
@WebServlet( name = "LoginServlet", value = "/LoginServlet" )
public class LoginServlet extends HttpServlet {

    /**
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/login.jsp" ).forward( request, response );
    }

    /**
     * doPost is invoked via the form on the dedicated login page, initially stores locally <br>
     * the parameters passed through http protocol, invokes the procedures dedicated to the login verification, <br>
     * and it handles the result via a switch.
     *
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
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