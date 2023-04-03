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
    private final String NAME;
    @SuppressWarnings( value = { "Field can be converted to a local variable",
            "Private field 'CARD_NUMBER' is assigned but never accessed" } )
    private final String CARD_NUMBER;
    @SuppressWarnings( value = { "Field can be converted to a local variable",
            "Private field 'CVV' is assigned but never accessed" } )
    private final String CVV;
    @SuppressWarnings( value = { "Field can be converted to a local variable",
            "Private field 'DATE_OF_EXPIRY' is assigned but never accessed" } )
    private final String DATE_OF_EXPIRY;

    /**
     * Costruttore per la classe BancomatStrategy.
     *
     * @param name il nome del titolare della carta Bancomat.
     * @param cardNumber il numero della carta Bancomat.
     * @param cvv il codice di sicurezza della carta Bancomat.
     * @param dateOfExpiry la data di scadenza della carta Bancomat.
     */
    public BancomatStrategy( String name, String cardNumber, String cvv, String dateOfExpiry ) {
        this.NAME = name;
        this.CARD_NUMBER = cardNumber;
        this.CVV = cvv;
        this.DATE_OF_EXPIRY = dateOfExpiry;
    }

    /**
    * Implementazione del metodo {@link PaymentStrategy#pay} definito nell'interfaccia {@link PaymentStrategy}.<br>
    * Stampa un messaggio di conferma dell'ordine effettuato con pagamento con carta Bancomat.
    *
    * @param amount l'importo da pagare.
    */
    @Override
    public void pay( double amount ) {
        System.out.println( "\n " + NAME + " has successfully made a payment of $"
                + amount + " paid with Bancomat card." );
    }
}
