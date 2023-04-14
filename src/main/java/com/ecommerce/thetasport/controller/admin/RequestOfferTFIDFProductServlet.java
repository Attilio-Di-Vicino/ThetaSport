package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import com.ecommerce.thetasport.service.tfidf.ManagerTFIDF;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet( name = "RequestOfferTFIDFProductServlet", value = "/RequestOfferTFIDFProductServlet" )
public class RequestOfferTFIDFProductServlet extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, true, false, false, false );
        try {
            request.setAttribute( "userList", UserDAO.getUsersMailMinusOne( "admin" ) );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in RequestOfferTFIDFProductServlet" + e );
        }
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        String email = request.getParameter( "email" );
        Map< String, List<List<String>> > offerUserMap = ManagerTFIDF.getAllOffersMoreRelated( ManagerTFIDF.TFIDFSingleUsers( email ) );
        request.setAttribute( "email", email );
        request.setAttribute( "offerListProduct", offerUserMap.get( email ) );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, false, false, true, false );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
