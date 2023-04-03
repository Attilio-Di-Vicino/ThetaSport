package com.ecommerce.thetasport.service.loginCor;

/**
 * La classe RoleCheckHandler è un gestore per controllare il ruolo dell'utente.
 * estende la classe {@link Handler}
 *
 * @author Theta Sport
 * @version 1.0
 * @see Handler
 */
public class RoleCheckHandler extends Handler {
    private final String USERNAME_ADMIN;

    /**
    * Costruttore della classe RoleCheckHandler.
    *
    * @param usernameAdmin Username dell'amministratore
    */
    public RoleCheckHandler( String usernameAdmin ) { this.USERNAME_ADMIN = usernameAdmin; }

    /**
     * Gestisce la richiesta di login controllando il ruolo dell'utente.<br>
     * Se si è attivato questo gestore significa che l'utente<br>
     * ha effettuato l'accesso con successo, quindi bisogna ora controllare il ruolo,<br>
     * nel caso in cui la mail corrisponde a quella dell'admin<br>
     * ritorna {@link ToHandle#ADMIN_ACCESS} <br>
     * altrimenti passa al gestore successivo <br>
     * sovrascrive il metodo {@link Handler#handle(String, String)}
     *
     * @param username Username inserito dall'utente
     * @param password Password inserita dall'utente
     * @return Ritorna un'enumerazione {@link ToHandle} che indica l'operazione successiva da effettuare
     */
    @Override
    public ToHandle handle( String username, String password ) {
        if ( USERNAME_ADMIN.equals( username ) ) {
            System.out.println( "Admin page" );
            return ToHandle.ADMIN_ACCESS;
        }
        return handlerNext( username, password );
    }
}
