package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.service.loginCor.ManagerLogin;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ForgotPasswordServlet is used if the user does not remember the password, <br>
 * there is a reference to this servlet in the login.jsp <br>
 * which invokes the get method that forwards the request to the dedicated page to request the forgotten password. <br>
 * In the ForgotPassword.jsp file there is a form which invokes the post method of the following servlet <br>
 */
@WebServlet( name = "ForgotPasswordServlet", value = "/ForgotPasswordServlet" )
public class ForgotPasswordServlet extends HttpServlet {

     /**
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
     */
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        request.getRequestDispatcher( "jsp/forgot_password.jsp" ).forward( request, response );
    }


    /**
     * The doPost method initially retrieves the parameters forwarded via http protocol from the client via a form, <br>
     * the reference form already perform various checks using HTML5, however, it's necessary to perform a server-sided <br>
     * check, in fact we check if the email inserted by the client exists in the database, being the unique and <br>
     * identifying attribute.If the email it's present a fake email is forwarded with the respective password <br>
     * otherwise an error message will appear
     *
     * @param request Request made via a browser
     * @param response Response
     * @throws ServletException Define a general exception that a servlet may generate when it encounters difficulties
     * @throws IOException Report thar an I/O exception has occurred
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