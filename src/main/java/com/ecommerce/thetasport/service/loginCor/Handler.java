package com.ecommerce.thetasport.service.loginCor;

/**
 * Classe astratta che rappresenta un gestore di accesso.<br>
 * Ogni gestore ha un riferimento al gestore successivo,<br>
 * in modo tale da creare una catena di responsabilità
 *
 * @author Theta Sport
 * @version 1.0
 */
public abstract class Handler {
    private Handler next;

    /**
     * Imposta il gestore successivo.
     *
     * @param next il gestore successivo.
     */
    public void setNextHandler( Handler next ){
        this.next = next;
    }

    /**
     * Restituisce il gestore successivo.
     *
     * @return il gestore successivo.
     */
    public Handler getNextHandler(){
        return this.next;
    }

    /**
     * Metodo astratto per gestire l'accesso.
     *
     * @param username il nome utente.
     * @param password la password dell'utente.
     * @return il risultato dell'operazione di gestione dell'accesso.
     */
    public abstract ToHandle handle( String username, String password );

    /**
     * Invoca il gestore successivo se presente. <br>
     * Se il gestore successivo non è presente, significa che ha <br>
     * passato tutti i precenti handler con successo, <br>
     * e che non è un admin, quindi vine restituito {@link ToHandle#USER_ACCESS}
     *
     * @param username il nome utente.
     * @param password la password dell'utente.
     * @return il risultato dell'operazione di gestione dell'accesso di tipo {@link ToHandle}
     */
    protected ToHandle handlerNext( String username, String password ) {
        if ( this.next == null ) {
            System.out.println( "User page" );
            return ToHandle.USER_ACCESS;
        }
        return next.handle( username,password );
    }
}
