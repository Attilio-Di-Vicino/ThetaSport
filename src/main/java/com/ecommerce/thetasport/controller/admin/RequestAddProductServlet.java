package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet( name = "RequestAddProductServlet", value = "/RequestAddProductServlet" )
public class RequestAddProductServlet extends HttpServlet {
    @Override
    protected void doGet( @NotNull HttpServletRequest request, @NotNull HttpServletResponse response ) throws ServletException, IOException {
        response.setContentType( "text/html");
        HelperControllerAdmin.setAdminPage( request, true, false, false,
                false, false, false );
        /*request.setAttribute( "addproduct", true );
        request.setAttribute( "editproduct", false );
        request.setAttribute( "editsingleproduct", false );
        request.setAttribute( "salesupdates", false );
        request.setAttribute( "sendoffers", false );
        request.setAttribute( "queydone", false );
        request.setAttribute( "categoryList", ManagerProduct.getCategoryList());
        try {
            request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
            request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
            request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
            request.setAttribute( "orderList", OrderDAO.getTotalOrdersUsersBeanList() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
