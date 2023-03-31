package com.ecommerce.thetasport.service.paymentstrategy;

@FunctionalInterface
public interface PaymentStrategy {
    void pay(double amount);
}
