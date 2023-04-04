package com.ecommerce.thetasport.service.loginCor;

import java.util.Map;

/**
 * La classe ValidPasswordHandler è un gestore per controllare se la password <br>
 * inserita dall'utente è valida, estende la classe {@link Handler}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Handler
 */
public class ValidPasswordHandler extends Handler {
    private final Map<String, String> USERS;

    /**
     * Costruttore della classe ValidPasswordHandler.
     *
     * @param users Mappa contenente gli utenti registrati nel sistema
     */
    public ValidPasswordHandler( Map<String, String> users ){
        this.USERS = users;
    }

    /**
     * Gestisce la richiesta di login controllando se la password inserita dall'utente è valida. <br>
     * nel caso in cui la password è sbagliata ritorna {@link ToHandle#WRONG_PASSWORD} <br>
     * altrimenti passa al gestore successivo <br>
     * Implementa il metodo {@link Handler#handle(String, String)}
     *
     * @param username Username inserito dall'utente
     * @param password Password inserita dall'utente
     * @return Ritorna un'enumerazione {@link ToHandle} che indica l'operazione successiva da effettuare
     */
    @Override
    public ToHandle handle( String username, String password ) {
        if ( !USERS.get( username ).equals( password ) ) {
            System.out.println( "Password wrong" );
            return ToHandle.WRONG_PASSWORD;
        }
        return handlerNext( username,password );
    }
}
