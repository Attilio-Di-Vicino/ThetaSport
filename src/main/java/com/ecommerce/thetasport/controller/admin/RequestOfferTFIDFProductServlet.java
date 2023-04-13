package com.ecommerce.thetasport.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet(name = "RequestOfferTFIDFProductServlet", value = "/RequestOfferTFIDFProductServlet")
public class RequestOfferTFIDFProductServlet extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, true, false );
        /*request.setAttribute("addproduct", false);
        request.setAttribute("editproduct", false);
        request.setAttribute("editsingleproduct", false);
        request.setAttribute("salesupdates", false);
        request.setAttribute("sendoffers", true);
        request.setAttribute("queydone", false);
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
