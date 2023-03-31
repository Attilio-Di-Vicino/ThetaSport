package com.ecommerce.thetasport.service.loginCor;

public class HelperCoR {
    public static Handler linkHandler( Handler first, Handler ...handlers ) {
        Handler head = first;
        for ( Handler nextInChain : handlers ) {
            head.setNextHandler(nextInChain);
            head = head.getNextHandler();
        }
        return first;
    }
}
