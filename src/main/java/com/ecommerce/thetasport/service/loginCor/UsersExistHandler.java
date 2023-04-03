package com.ecommerce.thetasport.service.loginCor;

import java.util.Map;

/**
 * La classe UsersExistHandler è un gestore per controllare se un utente esiste già nel sistema. <br>
 * estende la classe {@link Handler}
 *
 * @author Theta Sport
 * @version 1.0
 * @see Handler
 */
public class UsersExistHandler extends Handler {
    private final Map<String, String> USERS;

    /**
     * Costruttore della classe UsersExistHandler
     *
     * @param users Mappa contenente gli utenti registrati nel sistema
     */
    public UsersExistHandler( Map<String, String> users ){
        this.USERS = users;
    }

    /**
     * Gestisce la richiesta di login controllando se l'utente esiste già nel sistema.
     * nel caso in cui la mail è sbagliata ritorna {@link ToHandle#UNREGISTERED} <br>
     * altrimenti passa al gestore successivo <br>
     * sovrascrive il metodo {@link Handler#handle(String, String)}
     *
     * @param username Username inserito dall'utente
     * @param password Password inserita dall'utente
     * @return Ritorna un'enumerazione {@link ToHandle} che indica l'operazione successiva da effettuare
     */
    @Override
    public ToHandle handle( String username, String password ) {
        if ( !USERS.containsKey( username ) ) {
            System.out.println( "User unregistred" );
            return ToHandle.UNREGISTERED;
        }
        return handlerNext( username, password );
    }
}
