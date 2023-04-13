package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RequestEditProductServlet", value = "/RequestEditProductServlet")
public class RequestEditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, true, false,
                false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
