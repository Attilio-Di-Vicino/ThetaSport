package com.ecommerce.thetasport.service.loginCor;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HelperCoR {

    @Contract("_, _ -> param1")
    public static Handler linkHandler( Handler head, Handler @NotNull ...handlers ) {
        Handler first = head;
        for ( Handler nextInChain : handlers ) {
            first.setNextHandler( nextInChain );
            first = first.getNextHandler();
        }
        return head;
    }
}
