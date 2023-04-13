package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "RemoveCartServlet", value = "/RemoveCartServlet" )
public class RemoveCartServlet extends HttpServlet {

    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String landingPage = request.getParameter( "landingPage" );
        try {
            if ( !landingPage.equals( "cart" ) ) {
                HelperController.removeCartCaseIndexOrSingleProduct( request, response );
            } else {
                HelperController.removeCartCaseCart( request, response );
            }
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in RemoveCartServlet/doGet" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in RemoveCartServlet/doGet" + ce );
        }
    }
}