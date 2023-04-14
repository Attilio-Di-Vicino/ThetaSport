package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet(name = "EditNameProductServlet", value = "/EditNameProductServlet")
public class EditNameProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("nameSingleProduct");
        int code = Integer.parseInt(request.getParameter("codeSingleProduct"));
        ProductDAO.editNameProduct(name,code);
        HelperController.ForwardProductList( request );
        HelperControllerAdmin.setAdminPage( request, false, true, false, false,
                false, false, false, false );
        request.getRequestDispatcher( "jsp/protected_admin_area.jsp" ).forward( request, response );
    }
}
