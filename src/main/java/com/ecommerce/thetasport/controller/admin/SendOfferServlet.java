package com.ecommerce.thetasport.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "SendOfferServlet", value = "/SendOfferServlet" )
public class SendOfferServlet extends HttpServlet {

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String email = request.getParameter( "email" );
        String offerListProduct = request.getParameter( "offerListProduct" );
        System.out.println( "Offer for " + email + " success" + offerListProduct );
        request.setAttribute( "email", email );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, false, false, false, true );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}