package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BancomatServlet", value = "/BancomatServlet")
public class BancomatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.checkoutPage(request,response);
        request.setAttribute("creditCart", false);
        request.setAttribute("bancomat", true);
        request.setAttribute("cash", false);
        request.getRequestDispatcher("jsp/checkout.jsp").forward(request,response);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String country = request.getParameter("b_country");
        String firstName = request.getParameter("b_fname");
        String lastName = request.getParameter("b_lname");
        String cartNumber = request.getParameter("b_cart_number");
        String cvv = request.getParameter("b_cvv");
        String dateOfExpiry = request.getParameter("b_date_expiry");
        String address = request.getParameter("b_address");
        String shippingAddress = request.getParameter("b_s_address");
        String stateCountry = request.getParameter("b_state_country");
        String postalCode = request.getParameter("b_postal_zip");
        String email = request.getParameter("b_email_address");
        String phone = request.getParameter("b_phone");
        String orderNotes = request.getParameter("b_order_notes");

        /*HttpSession session = request.getSession(false);
        List<ItemElement> items = (List<ItemElement>) session.getAttribute("itemsCart");
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl();
        shoppingCartVisitor.pay(new BancomatStrategy(firstName,cartNumber,cvv,dateOfExpiry),items);
        // se è andato a buon fine non facciamo i seguenti controlli essendo un pagamento fake
        // inserire gli ordini nel DB
        // svuotare il carrello
        try {
            items = ManagerPay.managePay(items,email);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("itemsCart", items);
        System.out.println("Bancomat Servlet");
        request.getRequestDispatcher("jsp/thank_you.jsp").forward(request,response);*/
    }
}
