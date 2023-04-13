package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "EditProductServlet", value = "/EditProductServlet" )
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperControllerAdmin.setAdminPage( request, false, true, true,
                false, false, false );
        int code = Integer.parseInt( request.getParameter( "codeProduct" ) );
        try {
            request.setAttribute( "singleProduct", ProductDAO.getSingleProduct( code ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in EditProductServlet" + e );
        }
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
