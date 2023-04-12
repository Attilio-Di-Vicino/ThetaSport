package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet( name = "CheckoutServlet", value = "/CheckoutServlet" )
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperController.checkoutPage( request, response );
        request.setAttribute( "creditCart", true );
        request.setAttribute( "bancomat", false );
        request.setAttribute( "cash", false );
        request.getRequestDispatcher( "jsp/checkout.jsp" ).forward( request, response );
    }
}