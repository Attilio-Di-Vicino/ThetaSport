package com.ecommerce.thetasport.service.paymentstrategy;

public class CashStrategy implements PaymentStrategy {
    private String email;
    private String tel;
    private String address;

    public CashStrategy( String email, String tel, String address ) {
        this.email = email;
        this.tel = tel;
        this.address = address;
    }

    @Override
    public void pay( double amount ){
        System.out.println( "Spedizione effettuata: " + amount + " Pagamento alla consegna." );
    }
}
