package com.ecommerce.thetasport.service.paymentstrategy;

import org.jetbrains.annotations.NotNull;

public class HelperStrategy {
    public static void pay( @NotNull PaymentStrategy paymentStrategy, double total ){
        paymentStrategy.pay( total );
    }
}
