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
        /*request.setAttribute("addproduct", false);
        request.setAttribute("editproduct", false);
        request.setAttribute("editsingleproduct", false);
        request.setAttribute("salesupdates", true);
        request.setAttribute("sendoffers", false);
        request.setAttribute("queydone", false);
        request.setAttribute("categoryList", HelperProduct.getCategoryList());
        try {
            request.setAttribute("earningMonthly", SumPriceOrderDAO.getSumPriceOrderMonthly());
            request.setAttribute("earningYears", HelperControllerAdmin.getSumOrderYear());
            request.setAttribute("earningTotal", HelperControllerAdmin.getSumOrderTotal());
            request.setAttribute("orderList", HelperControllerAdmin.getTotalOrderBean());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }

    @Override
    protected void doPost( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html" );
        Category category = Category.valueOf( request.getParameter( "categoryRequest" ) );
        int result = 0;
        try {
            /**
             * DA AGGIUSTARE
             */
            result = ProductDAO.getSumSoldItemsCategory( category, SubCategory.SHOES );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in RequestSumProductSoldCategoryServlet" + e );
        }
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                true, false, true );
        /*request.setAttribute("addproduct", false);
        request.setAttribute("editproduct", false);
        request.setAttribute("editsingleproduct", false);
        request.setAttribute("salesupdates", true);
        request.setAttribute("sendoffers", false);
        request.setAttribute("queydone", true);
        request.setAttribute("categotySum", category.toString());
        request.setAttribute("categoryList", HelperProduct.getCategoryList());
        request.setAttribute("result", result);
        try {
            request.setAttribute("earningMonthly", HelperControllerAdmin.getSumOrderMontly());
            request.setAttribute("earningYears", HelperControllerAdmin.getSumOrderYear());
            request.setAttribute("earningTotal", HelperControllerAdmin.getSumOrderTotal());
            request.setAttribute("orderList", HelperControllerAdmin.getTotalOrderBean());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
