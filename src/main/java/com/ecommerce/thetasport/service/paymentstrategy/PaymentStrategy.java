package com.ecommerce.thetasport.service.paymentstrategy;

/**
 * Questa interfaccia definisce il contratto per le strategie di pagamento.<br>
 * Le classi che implementano questa interfaccia devono fornire un'implementazione<br>
 * del metodo "pay" che accetta un importo come parametro e gestisce il pagamento.<br>
 *
 * @author Theta Sport
 * @version 1.0
 */
@FunctionalInterface
public interface PaymentStrategy {

    /**
     * Questo metodo deve essere implementato dalle classi che implementano questa interfaccia.<br>
     *
     * @param amount l'importo da pagare.
     */
    void pay( double amount );
}
