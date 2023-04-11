package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "RemoveObjectCartServlet", value = "/RemoveObjectCartServlet" )
public class RemoveObjectCartServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            HelperController.removeObjectCart( request, response );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Exception in RemoveObjectCartServlet/doGet" + e );
        } catch ( ClassNotFoundException ce ) {
            throw new RuntimeException( "ClassNotFound Exception in RemoveObjectCartServlet/doGet" + ce );
        }
    }
}