package com.ecommerce.thetasport.service.loginCor;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class HelperCoR {
    @Contract("_, _ -> param1")
    public static Handler linkHandler(Handler first, Handler @NotNull ...handlers ) {
        Handler head = first;
        for ( Handler nextInChain : handlers ) {
            head.setNextHandler(nextInChain);
            head = head.getNextHandler();
        }
        return first;
    }
}
