package com.ecommerce.thetasport.service.loginCor;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * La classe HelperCoR contiene metodi utili per la gestione della catena di responsabilità.<br>
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperCoR {

    /**
     * Metodo utilizzato per collegare insieme gli handler della catena di responsabilità.<br>
     *
     * 'annotazione @Contract viene utilizzata per specificare che il metodo linkHandler restituisce<br>
     * sempre il primo parametro che gli viene passato, cioè l'istanza del primo Handler della catena di responsabilità.<br>
     * Il primo _ indica che il metodo accetta un qualsiasi tipo di parametro come primo parametro.<br>
     * Il secondo _ indica che il metodo accetta un qualsiasi tipo di parametro come secondo parametro.<br>
     * In input assume un numero indefinito di handler
     *
     * @param head     Il primo handler della catena
     * @param handlers Gli handler successivi da collegare
     * @return L'handler iniziale della catena di responsabilità
     */
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
