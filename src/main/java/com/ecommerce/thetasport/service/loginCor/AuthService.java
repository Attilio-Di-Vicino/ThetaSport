package com.ecommerce.thetasport.service.loginCor;

/**
 * La classe AuthService rappresenta il servizio di autenticazione degli utenti.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Handler
 */
public class AuthService {
    private final Handler HANDLER;

    /**
     * Costruttore della classe AuthService.
     *
     * @param handler Gestore della richiesta di login
     */
    public AuthService( Handler handler ) { this.HANDLER = handler; }

    /**
     * Esegue il login dell'utente.
     *
     * @param username Username inserito dall'utente
     * @param password Password inserita dall'utente
     * @return Ritorna un'enumerazione {@link ToHandle} che indica l'operazione successiva da effettuare
     */
    public ToHandle login( String username, String password ){
        return HANDLER.handle( username,password );
    }
}
