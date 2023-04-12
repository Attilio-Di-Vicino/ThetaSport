package com.ecommerce.thetasport.service.paymentstrategy;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta un gestore di pagamenti.
 *
 * <p>Permette di effettuare il pagamento di una determinata somma attraverso<br> una specifica strategia di pagamento.</p>
 *
 * @author Theta Sport
 * @version 1.0
 */
public class ManagerPayments {

    /**
     * Metodo statico che permette di effettuare il pagamento di una determinata somma attraverso<br> una specifica strategia di pagamento.
     *
     * <p>La somma totale da pagare viene passata come parametro.</p>
     *
     * @param paymentStrategy La strategia di pagamento da utilizzare per effettuare il pagamento.
     * @param total La somma totale da pagare.
     */
    public static void pay( @NotNull PaymentStrategy paymentStrategy, double total ){
        paymentStrategy.pay( total );
    }

    public static void insertOrder( @NotNull Cart myCart, String email, double total ) throws SQLException {
        OrderDAO.insertOrder( myCart.getMyCart(), email, total);
    }
}
