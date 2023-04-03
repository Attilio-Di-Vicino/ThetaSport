package com.ecommerce.thetasport.service.paymentstrategy;

/**
 * Questa classe implementa la strategia di pagamento con carta di credito.<br>
 *
 * La classe definisce un costruttore che accetta le informazioni sulla carta di credito,<br>
 * e implementa il metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class CreditCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;


    /**
     * Costruttore per la classe CreditCardStrategy.<br>
     *
     * @param name il nome del titolare della carta.
     * @param cardNumber il numero della carta di credito.
     * @param cvv il codice di sicurezza CVV della carta.
     * @param dateOfExpiry la data di scadenza della carta.
     */
    public CreditCardStrategy( String name, String cardNumber, String cvv, String dateOfExpiry ) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    /**
     * Implementazione del metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.<br>
     *
     * Stampa un messaggio di conferma del pagamento effettuato con carta di credito.<br>
     *
     * @param amount l'importo da pagare.
     */
    @Override
    public void pay( double amount ) {
        System.out.println( "\n " + name + " has successfully made a payment of $"
                + amount + " paid with credit/debit card" );
    }
}
