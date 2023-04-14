package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet( name = "RequestOfferTFIDFProductServlet", value = "/RequestOfferTFIDFProductServlet" )
public class RequestOfferTFIDFProductServlet extends HttpServlet {

    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperControllerAdmin.setAdminPage( request, false, false, false,
                false, true, false );
        request.setAttribute( "userList", UserDAO.getUsersMail() );
        request.getRequestDispatcher("jsp/protected_admin_area.jsp").forward(request, response);
    }
}
