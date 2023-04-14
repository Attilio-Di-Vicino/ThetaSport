package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.UserBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe DAO responsabile delle interrogazioni al database riguardo gli user
 *
 * @author Theta Sport
 * @version 1.0
 */
public class UserDAO {
    private final static String QUERY_USERS_MAP_SQL = "SELECT EMAIL,PASSWORD FROM USER";
    private final static String QUERY_USERS_MAIL_SQL = "SELECT EMAIL FROM USER";
    private final static String QUERY_USERS_MAIL_MINUS_ONE_SQL = "SELECT EMAIL FROM USER WHERE EMAIL != (?)";
    private final static String INSERT_REGISTRATION_SQL = "INSERT INTO USER (EMAIL,NAME,PASSWORD) VALUES (?,?,?)";
    private final static String QUERY_EMAIL_EXIST_SQL = "SELECT count(EMAIL) FROM USER WHERE EMAIL = (?)";
    private final static String QUERY_USER_SQL = "SELECT * FROM USER WHERE EMAIL = (?)";

    /**
     * il metodo getUsersMap() interroga il database e ritorna <br>
     * una map la quale contiene coppie chiave valore di mail e password di un utente <br>
     * viene utilizzata per effettuare l'accesso
     *
     * @return map di mail e password contenente tutti gli utenti
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static Map<String, String> getUsersMap() throws SQLException {
        Map<String, String> usersMap = new HashMap<>();
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                        .prepareStatement( QUERY_USERS_MAP_SQL );
              ResultSet rs = pstmt.executeQuery() ) {
            while (rs.next()) {
                usersMap.put(rs.getString("EMAIL"), rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersMap;
    }

    /**
     * il metodo getUsersMail() interroga il database e ritorna <br>
     * una list di string la quale contiene le mail di tutti gli utenti
     *
     * @return list di mail di tutti gli utenti
     */
    public static List < String > getUsersMail() {
        List< String > usersList = new ArrayList<>();
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement( QUERY_USERS_MAIL_SQL );
              ResultSet rs = pstmt.executeQuery() ) {
            while (rs.next()) {
                usersList.add(rs.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    /**
     * il metodo getUsersMail() interroga il database e ritorna <br>
     * una list di string la quale contiene le mail di tutti gli utenti
     *
     * @return list di mail di tutti gli utenti
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List < String > getUsersMailMinusOne( String email ) throws SQLException {
        List< String > usersListMinusOne = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_USERS_MAIL_MINUS_ONE_SQL );
            pstmt.setString( 1, email );
            rs = pstmt.executeQuery();
            while ( rs.next() ) {
                usersListMinusOne.add( rs.getString( "EMAIL" ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
        return usersListMinusOne;
    }

    /**
     * il metodo registration() viene utilizzato per la registrazione di un utente <br>
     * esegue una insert nel database
     *
     * @param name nome dell'utente che si sta registrando
     * @param email email dell'utente che si sta registrando
     * @param password password dell'utente che si sta registrando
     */
    public static void registration( String name, String email, String password ) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement( INSERT_REGISTRATION_SQL ) ) {
            pstmt.setString(1, email);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * il metodo userMailExist() va a verificare se la mail presa in input è presente o meno <br>
     * all'interno del database
     *
     * @param email mail da verificare
     * @return un boolean che sarà true se la mail è presente e false se non è presente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static boolean userMailExist( String email ) throws SQLException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_EMAIL_EXIST_SQL );
            pstmt.setString( 1, email );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getBoolean( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
        return result;
    }

    /**
     * il metodo getUser() interroga il database e restituisce <br>
     * le informazioni di un singolo utente
     *
     * @param email email dell'utente chiave primaria
     * @return UserBean che conterrà le informazioni dell'utente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public UserBean getUser( String email ) throws SQLException {
        UserBean userBean = new UserBean();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_USER_SQL );
            pstmt.setString( 1, email );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                userBean.setEmail( rs.getString( "EMAIL" ) );
                userBean.setName( rs.getString( "NAME" ) );
                userBean.setPassword( rs.getString( "PASSWORD" ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
        return userBean;
    }
}
