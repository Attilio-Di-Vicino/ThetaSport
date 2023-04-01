package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    @SuppressWarnings("Field 'usersMap' may be 'final'")
    private static Map<String, String> usersMap = new HashMap<>();
    private static boolean result;
    private static UserBean userBean = new UserBean();
    private final static String QUERY_USERS_MAP_SQL = "SELECT EMAIL,PASSWORD FROM USER";
    private final static String INSERT_REGISTRATION_SQL = "INSERT INTO USER (EMAIL,NAME,PASSWORD) VALUES (?,?,?)";
    private final static String QUERY_EMAIL_EXIST_SQL = "SELECT count(EMAIL) FROM USER WHERE EMAIL = (?)";
    private final static String QUERY_USER_SQL = "SELECT * FROM USER WHERE EMAIL = (?)";

    /**
     * il metodo getUsersMap() interroga il database e ritorna
     * una map la quale contiene coppie chiave valore di mail e password di un utente
     * viene utilizzata per effettuare l'accesso
     * @return map di mail e password contenente tutti gli utenti
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static Map<String, String> getUsersMap() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_USERS_MAP_SQL );
            rs = pstmt.executeQuery();
            while ( rs.next() ) {
                usersMap.put( rs.getString( "EMAIL" ), rs.getString( "PASSWORD" ) );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null ) {
                connection.close();
            }
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
        return usersMap;
    }

    /**
     * il metodo registration() viene utilizzato per la registrazione di un utente
     * esegue una insert nel database
     * @param name nome dell'utente che si sta registrando
     * @param email email dell'utente che si sta registrando
     * @param password password dell'utente che si sta registrando
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void registration( String name, String email, String password ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( INSERT_REGISTRATION_SQL );
            pstmt.setString( 1, email );
            pstmt.setString( 2, name );
            pstmt.setString( 3, password );
            pstmt.executeUpdate();
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null ) {
                connection.close();
            }
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
    }

    /**
     * il metodo userMailExist() va a verificare se la mail presa in input è presente o meno
     * all'interno del database
     * @param email mail da verificare
     * @return un boolean che sarà true se la mail è presente e false se non è presente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static boolean userMailExist( String email ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_EMAIL_EXIST_SQL );
            pstmt.setString( 1, email );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getBoolean( 1 );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null ) {
                connection.close();
            }
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
     * il metodo getUser() interroga il database e restituisce
     * le informazioni di un singolo utente
     * @param email email dell'utente chiave primaria
     * @return UserBean che conterrà le informazioni dell'utente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public UserBean getUser( String email ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_USER_SQL );
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
            if ( connection != null ) {
                connection.close();
            }
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
