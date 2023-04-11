package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet( name = "AddCartServlet", value = "/AddCartServlet" )
public class AddCartServlet extends HttpServlet {

    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String landingPage = request.getParameter("landingPage");
        if ( landingPage == null ) {
            throw new NullPointerException( "landing page is null." );
        }
        if ( landingPage.equals( "index" ) ) {
            HelperController.addCartCaseIndex( request,response );
        } else if (landingPage.equals("cart")) {
            // HelperController.addCartCaseCart(request,response);
        } else {
            // HelperController.addCartCaseSingleProduct(request,response);
        }
    }
}