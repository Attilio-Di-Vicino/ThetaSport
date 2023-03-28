package com.ecommerce.thetasport.controller.general;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("welcome", "This is a official project");
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }
}
