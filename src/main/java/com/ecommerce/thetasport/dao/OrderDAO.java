package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.TotalOrdersUsersBean;
import com.ecommerce.thetasport.service.cartvisitor.ItemElement;
import com.ecommerce.thetasport.service.productabstractfactory.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OrderDAO {

    private static Connection connection; // connessione al database
    private static PreparedStatement pstmt; // prepared statement per le query al database
    private static ResultSet rs; // risultato delle query
    private static double result;
    private static List<TotalOrdersUsersBean> totalOrdersUsersBeanList;
    private final static String INSERT_ORDER_SQL = "INSERT INTO `ORDER` (ORDERDATE, EMAIL, GROSSPROFIT) " +
            "VALUES (?,?,?)";
    private final static String INSERT_CONTAINS_SQL = "INSERT INTO CONTAINS (CODE, ORDERID_C, QUANTITY) " +
            "VALUES (?,?,?)";
    private final static String QUERY_MONTHLY_YEAR_SQL = "SELECT SUM(GROSSPROFIT) FROM `ORDER` " +
            "WHERE MONTH(ORDERDATE) = (?) AND YEAR(ORDERDATE) = (?)";
    private final static String QUERY_YEARS_SQL = "SELECT SUM(GROSSPROFIT) FROM `ORDER` " +
            "WHERE YEAR(ORDERDATE) = (?)";
    private final static String QUERY_TOTAL_SQL = "SELECT SUM(GROSSPROFIT) FROM `ORDER`";
    private final static String QUERY_TOTAL_ORDERS_USERS_SQL = "SELECT O.ORDERID,U.NAME,O.EMAIL,O.ORDERDATE," +
            "SUM(P.PRICE * C.QUANTITY) AS TOTAL,SUM(QUANTITY) AS QUANTITY " +
            "FROM `ORDER` O JOIN CONTAINS C on O.ORDERID = C.ORDERID_C " +
            "JOIN PRODUCT P on P.CODE = C.CODE JOIN USER U on U.EMAIL = O.EMAIL" +
            " GROUP BY ORDERID";

    /**
     * il metodo insertListOrder assume in input una map che rappresenta il carrello della spesa
     * una mail che rappresenta la mail di chi ha eseguito l'ordine, e un totale dato dal totale
     * dei prodotti presenti nel carrello
     * in primis va a recuperare l'orario al momento dell'inserimento in formato anno-mese-giorno
     * quindi prepara lo statement dando un input un ulteriore statement il quale è una richiesta
     * di ritornare la chiave generata all'inserimento nella tabella ORDER essendo un attributo "AUTO_INCREMENT"
     * quindi imposta i valori ed esegue l'inserimento nella tabella ORDER,
     * recuperando ORDERID generato automaticamente.
     * Dopodiche bisogna riempire la tabella CONTAINS, quindi si itera la map che rappresenta il carrello
     * ed per ogni elemento si inserisce nella tabella CONTAINS con i rispettivi codici
     *
     * @param items map che rappresenta il carrello della spesa
     * @param email mail dell'user che ha effettuato l'ordine
     * @param total totale del carrello della spesa
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void insertOrder(Map<ItemElement, Integer> items, String email, double total ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS );
            // prendo la data attuale in formato anno-mese-giorno
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.setTimeZone( TimeZone.getTimeZone( "Europe/Rome" ) );
            dateFormat.applyPattern( "yyyy.MM.dd" );
            pstmt.setString( 1, dateFormat.format( new Date() ) );
            pstmt.setString( 2, email );
            pstmt.setDouble( 3, total );
            pstmt.executeUpdate();
            int id = 0;
            // Recupera il valore generato per la colonna di chiave primaria
            rs = pstmt.getGeneratedKeys();
            if ( rs.next() ) {
                id = rs.getInt( 1 );
                System.out.println( "Il valore generato per la chiave primaria è: " + id );
            }
            // ciclo i prodotti presenti all'interno del carrello
            // ed inserisco all'interno della tabella CONTAINS
            for ( ItemElement item : items.keySet() ) {
                Product product = ( Product ) item;
                pstmt = connection.prepareStatement( INSERT_CONTAINS_SQL );
                pstmt.setInt( 1, product.getCode() );
                pstmt.setInt( 2, id );
                pstmt.setInt( 3, items.get( item ) );
                int result = pstmt.executeUpdate();
                System.out.println( "Insert contains: " + product.getCode() + " result: " + result );
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
    }

    /**
     * il metodo getSumPriceOrderMonthly() esegue un interrogazione al database
     * avvalendosi di due funzioni che restituiscono il mese corrente e l'anno corrente
     * per il mese usa {@link HelperDAO#getCurrentMonth()}
     * per l'anno usa {@link HelperDAO#getCurrentYear()}
     * @return totale profitto lordo del mese corrente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static double getSumPriceOrderMonthly() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_MONTHLY_YEAR_SQL );
            pstmt.setInt( 1, HelperDAO.getCurrentMonth() );
            pstmt.setInt( 2, HelperDAO.getCurrentYear() );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getDouble( 1 );
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
     * il metodo getSumPriceOrderYear() esegue un interrogazione al database
     * avvalendosi di due funzioni che restituiscono l'anno corrente {@link HelperDAO#getCurrentYear()}
     * @return totale profitto lordo dell'anno corrente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static double getSumPriceOrderYear() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_YEARS_SQL );
            pstmt.setInt( 1, HelperDAO.getCurrentYear() );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getDouble( 1 );
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
     * il metodo getSumPriceOrderTotal() esegue un interrogazione al database
     * @return totale profitto lordo
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static double getSumPriceOrderTotal() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_TOTAL_SQL );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getDouble( 1 );
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
     * il metodo getTotalOrdersUsersBeanList() interroga il database
     * e restituisce una tupla composta da più attributi presenti in più tabelle
     * dopodiche construisce la lista che contiene tutti gli ordini effettuati
     * con ulteriori informazioni come il nome la mail di chi ha effettuato quell'ordine
     *
     * @return lista di un oggetto composto da attributi di più tabelle
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List< TotalOrdersUsersBean > getTotalOrdersUsersBeanList() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_TOTAL_ORDERS_USERS_SQL );
            rs = pstmt.executeQuery();
            totalOrdersUsersBeanList = new ArrayList<>();
            while ( rs.next() ) {
                TotalOrdersUsersBean totalOrdersUsersBean = new TotalOrdersUsersBean();
                totalOrdersUsersBean.setOrderId( rs.getInt( "ORDERID" ) );
                totalOrdersUsersBean.setName( rs.getString( "NAME" ) );
                totalOrdersUsersBean.setEmail( rs.getString( "EMAIL" ) );
                totalOrdersUsersBean.setDate( rs.getString( "ORDERDATE" ) );
                totalOrdersUsersBean.setTotal( rs.getDouble( "TOTAL" ) );
                totalOrdersUsersBean.setQuantity( rs.getInt( "QUANTITY" ) );
                totalOrdersUsersBeanList.add( totalOrdersUsersBean );
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
        return totalOrdersUsersBeanList;
    }
}
