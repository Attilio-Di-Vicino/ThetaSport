package com.ecommerce.thetasport.service.paymentstrategy;

/**
 * Questa classe implementa la strategia di pagamento con carta Bancomat.<br>
 * La classe definisce un costruttore che accetta le informazioni della carta Bancomat del cliente,<br>
 * e implementa il metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see PaymentStrategy
 */
public class BancomatStrategy implements PaymentStrategy{
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    /**
     * Costruttore per la classe BancomatStrategy.
     *
     * @param name il nome del titolare della carta Bancomat.
     * @param cardNumber il numero della carta Bancomat.
     * @param cvv il codice di sicurezza della carta Bancomat.
     * @param dateOfExpiry la data di scadenza della carta Bancomat.
     */
    public BancomatStrategy( String name, String cardNumber, String cvv, String dateOfExpiry ) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    /**
    * Implementazione del metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.<br>
    * Stampa un messaggio di conferma dell'ordine effettuato con pagamento con carta Bancomat.
    *
    * @param amount l'importo da pagare.
    */
    @Override
    public void pay( double amount ) {
        System.out.println( "\n " + name + " has successfully made a payment of $"
                + amount + " paid with Bancomat card." );
    }
}
