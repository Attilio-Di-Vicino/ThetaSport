package com.ecommerce.thetasport.controller.general;

import com.ecommerce.thetasport.controller.HelperController;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreditCartServlet", value = "/CreditCartServlet")
public class CreditCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HelperController.checkoutPage(request,response);
        request.setAttribute("creditCart", true);
        request.setAttribute("bancomat", false);
        request.setAttribute("cash", false);
        request.getRequestDispatcher("jsp/checkout.jsp").forward(request,response);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String country = request.getParameter("c_country");
        String firstName = request.getParameter("c_fname");
        String lastName = request.getParameter("c_lname");
        String cartNumber = request.getParameter("c_cart_number");
        String cvv = request.getParameter("c_cvv");
        String dateOfExpiry = request.getParameter("c_date_expiry");
        String address = request.getParameter("c_address");
        String shippingAddress = request.getParameter("c_s_address");
        String stateCountry = request.getParameter("c_state_country");
        String postalCode = request.getParameter("c_postal_zip");
        String email = request.getParameter("c_email_address");
        String phone = request.getParameter("c_phone");
        String orderNotes = request.getParameter("c_order_notes");

        /*HttpSession session = request.getSession(false);
        Cart myCart = (Cart) session.getAttribute( "itemsCart" );
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl();
        shoppingCartVisitor.pay(new CreditCardStrategy(firstName,cartNumber,cvv,dateOfExpiry),items);
        // se è andato a buon fine non facciamo i seguenti controlli essendo un pagamento fake
        // inserire gli ordini nel DB
        // svuotare il carrello
        try {
            items = ManagerPay.managePay(items,email);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("itemsCart", items);

        System.out.println("Credit Cart Servlet");
        request.getRequestDispatcher("jsp/thank_you.jsp").forward(request,response);*/
    }
}
