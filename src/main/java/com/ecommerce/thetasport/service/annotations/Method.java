package com.ecommerce.thetasport.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * annotazione personalizzata
 * Utilizzala per documentare i metodi delle classi.
 *
 * @author ThetaSport
 * @version 1.0
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Method {

    /**
     * Fornisce una descrizione del metodo.
     */
    String description();

    /**
     * Fornisce informazioni sui parametri del metodo.
     */
    String[] parameters() default {};

    /**
     * Fornisce informazioni sui valori di ritorno del metodo.
     */
    String[] returns() default {};

    /**
     * Fornisce informazioni sulle eccezioni che il metodo può generare.
     */
    Class<? extends Throwable>[] exceptions() default {};
}

