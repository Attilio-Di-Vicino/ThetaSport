package com.ecommerce.thetasport.service.loginCor;

import com.ecommerce.thetasport.dao.UserDAO;

import java.sql.SQLException;
import java.util.Map;

/**
 * La classe ManagerLogin fornisce un metodo statico per effettuare il login di un utente.<br>
 */

public class ManagerLogin {

    /**
     * Effettua il login di un utente utilizzando il nome utente e la password forniti come input.
     * @param name  il nome utente dell'utente che vuole effettuare il login.
     * @param password la password dell'utente che vuole effettuare il login.
     * @param usernameAdmin il nome utente dell'amministratore del sistema.
     * @return un oggetto ToHandle che rappresenta il risultato del login.
     * @throws RuntimeException se si verifica un'eccezione SQL durante la lettura delle informazioni degli utenti dal database
     */
    public static ToHandle login( String name, String password, String usernameAdmin ) {
        Map<String, String> usersMap;
        try {
            usersMap = UserDAO.getUsersMap();
        } catch ( SQLException e ) {
            throw new RuntimeException( "Error in ManagerLogin/Login " + e );
        }
        // Configura la catena di responsabilità per il controllo dell'autenticazione dell'utente
        Handler handler = HelperCoR.linkHandler( new UsersExistHandler( usersMap ),
                new ValidPasswordHandler( usersMap ),
                new RoleCheckHandler( usernameAdmin ));
        AuthService authService = new AuthService( handler );
        return authService.login( name, password );
    }
}
