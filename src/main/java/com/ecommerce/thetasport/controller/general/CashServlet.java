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

@WebServlet(name = "CashServlet", value = "/CashServlet")
public class CashServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.checkoutPage(request,response);
        request.setAttribute("creditCart", false);
        request.setAttribute("bancomat", false);
        request.setAttribute("cash", true);
        request.getRequestDispatcher("jsp/checkout.jsp").forward(request,response);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String country = request.getParameter("cc_country");
        String firstName = request.getParameter("cc_fname");
        String lastName = request.getParameter("cc_lname");
        String address = request.getParameter("cc_address");
        String shippingAddress = request.getParameter("cc_s_address");
        String stateCountry = request.getParameter("cc_state_country");
        String postalCode = request.getParameter("cc_postal_zip");
        String email = request.getParameter("cc_email_address");
        String phone = request.getParameter("cc_phone");
        String orderNotes = request.getParameter("cc_order_notes");

        /*HttpSession session = request.getSession(false);
        List<ItemElement> items = (List<ItemElement>) session.getAttribute("itemsCart");
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl();
        shoppingCartVisitor.pay(new CashStrategy(firstName,phone,shippingAddress),items);
        // se è andato a buon fine non facciamo i seguenti controlli essendo un pagamento fake
        // inserire gli ordini nel DB
        // svuotare il carrello
        try {
            items = ManagerPay.managePay(items,email);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("itemsCart", items);

        System.out.println("Cash Servlet");
        request.getRequestDispatcher("jsp/thank_you.jsp").forward(request,response);*/
    }
}
