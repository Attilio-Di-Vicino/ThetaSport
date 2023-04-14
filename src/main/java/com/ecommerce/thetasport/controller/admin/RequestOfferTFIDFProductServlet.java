package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import com.ecommerce.thetasport.service.tfidf.CustomPriorityQueue;
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
        Map< String, CustomPriorityQueue< String, Double > > mapResult = ManagerTFIDF.TFIDFSingleUsers( email );
        Map< String, List<List<String>> > offerUserMap = ManagerTFIDF.getAllOffersMoreRelated( mapResult );
        request.setAttribute( "email", email );
        List< List< String > > productList = offerUserMap.get( email );
        // nel caso il cui la differenza insiemistica è minore di 0, quindi la lista dei prodotti
        // da offrire risulta essere nulla allora procediamo con il secondo utente più simile ad esso
        // considerando che all'interno del metodo getAllOffersMoreRelated viene eseguito una poll sulla coda di massima priorità
        if ( productList.isEmpty() ) {
            while ( productList.isEmpty() && !mapResult.get( email ).isEmpty() ) {
                productList = ManagerTFIDF.getAllOffersMoreRelated( mapResult ).get( email );
            }
        }
        request.setAttribute( "offerListProduct", productList );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, false, false, true, false );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
