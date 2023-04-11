package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet( name = "CartPageServlet", value = "/CartPageServlet" )
public class CartPageServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.cartPage( request,response );
    }
}