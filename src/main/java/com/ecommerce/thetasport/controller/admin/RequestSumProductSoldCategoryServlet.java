package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "RequestSumProductSoldCategoryServlet", value = "/RequestSumProductSoldCategoryServlet" )
public class RequestSumProductSoldCategoryServlet extends HttpServlet {
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                true, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        Category category = Category.valueOf( request.getParameter( "categoryRequest" ).toUpperCase().trim() );
        SubCategory subCategory = SubCategory.valueOf( request.getParameter( "subCategoryRequest" ).toUpperCase().trim() );
        int result;
        System.out.println( "\n" + subCategory );
        try {
            result = ProductDAO.getSumSoldItemsCategory( category, subCategory );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in RequestSumProductSoldCategoryServlet" + e );
        }
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                true, false, true );
        System.out.println("\n" + result);
        request.setAttribute( "categotySum", result );
        request.setAttribute( "category", category );
        request.setAttribute( "subCategory", subCategory );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
