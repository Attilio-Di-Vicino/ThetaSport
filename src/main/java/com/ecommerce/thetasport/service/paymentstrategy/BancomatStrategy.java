package com.ecommerce.thetasport.service.paymentstrategy;

public class BancomatStrategy implements PaymentStrategy{
    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    public BancomatStrategy( String name, String cardNumber, String cvv, String dateOfExpiry ) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay( double amount ) {
        System.out.println( amount + " paid with Bancomat card" );
    }
}
