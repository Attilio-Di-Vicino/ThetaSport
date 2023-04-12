package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.TotalOrdersUsersBean;
import com.ecommerce.thetasport.service.cartvisitor.ItemElement;
import com.ecommerce.thetasport.service.productabstractfactory.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class OrderDAO {

    private static Connection connection; // database connection
    private static PreparedStatement pstmt; // prepared statement for database queries
    private static ResultSet rs; // query result
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
     * The insertListOrder method takes as input a map representing the shopping cart,<br>
     * an email representing the email of the person who placed the order and a total given by the total<br>
     * of the products in the shopping cart.<br>
     * First goes to retrieve the time of when it was entered in year-month-day format<br>
     * then prepares the statement by giving an input an additional statement which is a request<br>
     * to return the key generated upon insertion into the ORDER table being an "AUTO_INCREMENT" attribute<br>
     * then sets the values and performs the insertion into the ORDER table,<br>
     * retrieving automatically generated ORDERID.<br>
     * After that we need to fill the CONTAINS table, so we iterate the map representing the cart<br>
     * and for each element you insert into the CONTAINS table with the respective codes.

     * @param items map representing the shopping cart
     * @param email email of the user who placed the order
     * @param total shopping cart total
     * @throws SQLException Defines a general exception that can be generated
     */
    public static void insertOrder(Map<ItemElement, Integer> items, String email, double total ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( INSERT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS );
            // the current date in year-month-day format is taken
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            dateFormat.setTimeZone( TimeZone.getTimeZone( "Europe/Rome" ) );
            dateFormat.applyPattern( "yyyy.MM.dd" );
            pstmt.setString( 1, dateFormat.format( new Date() ) );
            pstmt.setString( 2, email );
            pstmt.setDouble( 3, total );
            pstmt.executeUpdate();
            int id = 0;
            // The value generated for the primary key column is retrieved
            rs = pstmt.getGeneratedKeys();
            if ( rs.next() ) {
                id = rs.getInt( 1 );
                System.out.println( "Il valore generato per la chiave primaria è: " + id );
            }
            // products within the shopping cart are cycled
            // and inserted within the CONTAINS table.
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
     * The getSumPriceOrderMonthly method execute a query on the database<br>
     * using two function that return the current month and year.<br>
     * for the month {@link HelperDAO#getCurrentMonth()} is used<br>
     * for the month {@link HelperDAO#getCurrentYear()} is used<br>
     *
     * @return total gross profit of the current month
     * @throws SQLException Defines a general exception that can be generated
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
     * the getSumPriceOrderYear() method performs a query to the database<br>
     * making use of two functions that return the current year {@link HelperDAO#getCurrentYear()}.
     *
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
     * The getSumPriceOrderTotal method performs a query to the database<br>
     *
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
     * The getTotalOrdersUsersBeanList() method queries the database<br>
     * and returns a tuple consisting of multiple attributes present in multiple tables<br>
     * after which it constructs the list containing all the orders placed<br>
     * with additional information such as the name the email of the person who placed that order
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
