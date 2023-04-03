package com.ecommerce.thetasport.service.paymentstrategy;

/**
 * Questa classe implementa la strategia di pagamento in contanti alla consegna.<br>
 * La classe definisce un costruttore che accetta le informazioni di contatto del cliente,<br>
 * e implementa il metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see PaymentStrategy
 */
public class CashStrategy implements PaymentStrategy {

    @SuppressWarnings( value = { "Field can be converted to a local variable",
            "Private field 'EMAIL' is assigned but never accessed" } )
    private final String EMAIL;
    @SuppressWarnings( value = { "Field can be converted to a local variable",
            "Private field 'TEL' is assigned but never accessed" } )
    private final String TEL;
    private final String ADDRESS;


    /**
     * Costruttore per la classe CashStrategy.
     *
     * @param email l'indirizzo email del cliente.
     * @param tel il numero di telefono del cliente.
     * @param address l'indirizzo di spedizione del cliente.
     */
    public CashStrategy( String email, String tel, String address ) {
        this.EMAIL = email;
        this.TEL = tel;
        this.ADDRESS = address;
    }

    /**
     * Implementazione del metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.<br>
     *
     * Stampa un messaggio di conferma dell'ordine effettuato con pagamento in contanti alla consegna.
     *
     * @param amount l'importo da pagare.
     */
    @Override
    public void pay( double amount ){
        System.out.println( "\nShipping made to the " + ADDRESS + " address with a total of $"
                + amount + " and payment on delivery." );
    }
}
