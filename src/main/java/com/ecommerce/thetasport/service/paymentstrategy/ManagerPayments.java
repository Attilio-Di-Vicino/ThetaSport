package com.ecommerce.thetasport.service.paymentstrategy;

import org.jetbrains.annotations.NotNull;

/**
 * Classe che rappresenta un gestore di pagamenti.
 *
 * <p>Permette di effettuare il pagamento di una determinata somma attraverso<br> una specifica strategia di pagamento.</p>
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
}
