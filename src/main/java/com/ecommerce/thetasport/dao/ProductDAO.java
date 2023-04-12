package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.Product;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

/**
 * DAO class responsible for database queries regarding products.
 */
public class ProductDAO {
    private static Connection connection; // database connection
    private static PreparedStatement pstmt; // prepared statement for database queries
    private static ResultSet rs; // query result
    private static final ProductBean productBean = new ProductBean(); // single product
    private final static String QUERY_SINGLE_PRODUCT_SQL = "SELECT * FROM PRODUCT WHERE CODE = (?)";
    private static int result;
    private final static String QUERY_SOLD_SINGLE_CATEGORY_SQL = "SELECT SUM(C.QUANTITY) FROM `ORDER` " +
            "JOIN `CONTAINS` C on `ORDER`.ORDERID = C.ORDERID_C JOIN PRODUCT P on P.CODE = C.CODE " +
            "WHERE P.CATEGORY = (?) AND P.SUBCATEGORY = (?)";
    private static List<ProductBean> productBeanList; // list of all product
    private final static String QUERY_ALL_PRODUCT_SQL = "SELECT * FROM PRODUCT";
    private final static String INSERT_SINGLE_PRODUCT_SQL = "INSERT INTO PRODUCT (NAME, DESCRIPTION, STOCK, " +
            "PRICE, CATEGORY, SUBCATEGORY, IMAGE) VALUES (?,?,?,?,?,?,?)";
    private final static String UPDATE_NAME_SQL = "UPDATE PRODUCT SET NAME = (?) WHERE CODE = (?)";
    private final static String UPDATE_DESCRIPTION_SQL = "UPDATE PRODUCT SET DESCRIPTION = (?) WHERE CODE = (?)";
    private final static String UPDATE_STOCK_SQL = "UPDATE PRODUCT SET STOCK = (?) WHERE CODE = (?)";
    private final static String UPDATE_PRICE_SQL = "UPDATE PRODUCT SET PRICE = (?) WHERE CODE = (?)";
    private final static String UPDATE_IMAGE_SQL = "UPDATE PRODUCT SET IMAGE = (?) WHERE CODE = (?)";
    private static List<String> productList; // TFIDF
    private final static String QUERY_PRODUCT_ORDER_SINGLE_USER_SQL = "SELECT P.NAME,C.QUANTITY FROM PRODUCT P " +
            "JOIN CONTAINS C on P.CODE = C.CODE JOIN `ORDER` O on O.ORDERID = C.ORDERID_C " +
            "JOIN USER U on O.EMAIL = U.EMAIL WHERE O.EMAIL = (?)";
    private final static String QUERY_ALL_PRODUCT = "SELECT NAME FROM PRODUCT";

    /**
     * the getSingleProduct<br>
     * il metodo getSingleProduct() takes as input a unique code<br>
     * and queries the database returning the product inherent in that code.<br>
     * sets the individual productBean with the help of {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)}<br>
     * finally in the finally block the connection, prepared statement and result set are closed.
     *
     * @param code unique product code
     * @return product having that code
     * @throws SQLException Defines a general exception that can be generated
     */
    public static ProductBean getSingleProduct( int code ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_SINGLE_PRODUCT_SQL );
            pstmt.setString( 1, String.valueOf( code ) );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                HelperDAO.setSingleProductBean( productBean, rs );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null ){
                connection.close();
            }
            if ( pstmt != null ){
                pstmt.close();
            }
            if ( rs != null ){
                rs.close();
            }
        }
        return productBean;
    }

    /**
     * the getSumSoldItemsCategory() method takes as input a category and subcategory<br>
     * and queries the database returning the sum of items sold for that category and sub-category<br>
     * finally in the finally block the connection, prepared statement and result set are closed
     *
     * @param category product category
     * @param subCategory product sub-category
     * @return sum of items sold for that category and sub-category
     * @throws SQLException Defines a general exception that can be generated
     */
    public static int getSumSoldItemsCategory( @NotNull Category category, @NotNull SubCategory subCategory ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_SOLD_SINGLE_CATEGORY_SQL );
            pstmt.setString( 1, category.toString() );
            pstmt.setString( 2, subCategory.toString() );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getInt( 1 );
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
     * The getProductBeanList() method is used for the list of all products.
     * sets the individual productBean with the help of {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)}
     * per poi aggiungerlo alla lista
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     *
     * @return lista di tutti i prodotti
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List<ProductBean> getProductBeanList() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_ALL_PRODUCT_SQL );
            rs = pstmt.executeQuery();
            productBeanList = new ArrayList<>();
            while ( rs.next() ) {
                ProductBean productBean = new ProductBean();
                HelperDAO.setSingleProductBean( productBean, rs );
                productBeanList.add( productBean );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connection != null ){
                connection.close();
            }
            if ( pstmt != null ) {
                pstmt.close();
            }
            if ( rs != null ) {
                rs.close();
            }
        }
        return productBeanList;
    }

    /**
     * il metodo insertProduct() permette di inserire un prodotto all'interno del databese
     * nel caso in cui l'immagine non viene inserita viene caricata un immagine di default
     * considerando che solo l'admin può inserire i prodotti, e che lato client, quindi
     * nella view dell'admin sono stati fatti vari controlli tramire HTML5
     * @param product product da inserire
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void insertProduct( @NotNull Product product ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( INSERT_SINGLE_PRODUCT_SQL );
            pstmt.setString( 1, product.getName() );
            pstmt.setString( 2, product.getDescription() );
            pstmt.setInt( 3,product.getStock() );
            pstmt.setDouble( 4,product.getPrice() );
            pstmt.setString( 5, product.getCategory().toString() );
            pstmt.setString( 6, product.getSubCategory().toString() );
            // in questo caso non è necessaria un'eccezione
            String image = product.getImage();
            if ( image == null || image.equals("") ) {
                image = "default-image.jpeg";
            }
            pstmt.setString( 7, image );
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
        }
    }

    /**
     * il metodo editNameProduct() permette di modificare il nome di un prodotto
     * si avvale del metodo {@link HelperDAO#editProduct(Connection, PreparedStatement, String, int, String)}
     * @param name nuovo nome del prodotto
     * @param code codice del prodotto che deve essere modificato
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void editNameProduct( String name, int code ) throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        HelperDAO.editProduct( connection, pstmt, name, code, UPDATE_NAME_SQL );
    }

    /**
     * il metodo editDescriptionProduct() permette di modificare la descrizione di un prodotto
     * si avvale del metodo {@link HelperDAO#editProduct(Connection, PreparedStatement, String, int, String)}
     * @param description nuova descrizione del prodotto
     * @param code codice del prodotto che deve essere modificato
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void editDescriptionProduct( String description, int code ) throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        HelperDAO.editProduct( connection, pstmt, description, code, UPDATE_DESCRIPTION_SQL );
    }

    /**
     * il metodo editStockProduct() permette di modificare lo stock di un prodotto
     * @param stock nuovo stock del prodotto
     * @param code codice del prodotto che deve essere modificato
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void editStockProduct( int stock, int code ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( UPDATE_STOCK_SQL );
            pstmt.setInt( 1, stock );
            pstmt.setInt( 2, code );
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
        }
    }

    /**
     * il metodo editPriceProduct() permette di modificare il prezzo di un prodotto
     * @param price nuovo prezzo del prodotto
     * @param code codice del prodotto che deve essere modificato
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void editPriceProduct( double price, int code ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( UPDATE_PRICE_SQL );
            pstmt.setDouble( 1, price );
            pstmt.setInt( 2, code );
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
        }
    }

    /**
     * il metodo editImageProduct() permette di modificare l'immagine di un prodotto
     * si avvale del metodo {@link HelperDAO#editProduct(Connection, PreparedStatement, String, int, String)}
     * @param image nuova immagine del prodotto
     * @param code codice del prodotto che deve essere modificato
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void editImageProduct( String image, int code ) throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        HelperDAO.editProduct( connection, pstmt, image, code, UPDATE_IMAGE_SQL );
    }

    /**
     * il metodo getProductListAllOrderSingleUser() esegue un'interrogazione al database
     * e restituisce una lista di string la quale conterrà tutti i prodotti acquistati da uno
     * specifico utente, e se un pordotto è stato acquistato più di una volta allora sarà
     * inserito quante volte è stato acquistato, ES: "Air Jordan" acquistato 2 volte
     * la lista avrà 2 elementi al suo interno che sono "Air Jordan".
     * @param email mail che fa riferimento ad un utente specifico
     * @return lista di string contente i pordotti acquistati da un singolo utente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List<String> getProductListAllOrderSingleUser( String email ) throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_PRODUCT_ORDER_SINGLE_USER_SQL );
            pstmt.setString( 1, email );
            rs = pstmt.executeQuery();
            productList = new ArrayList<>();
            while ( rs.next() ) {
                int quantity = rs.getInt( "QUANTITY" );
                for ( int i = 0; i < quantity; i++ ) {
                    productList.add( rs.getString( "NAME" ) );
                }
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
        return productList;
    }

    public static List< String > getAllProductList() throws SQLException {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            pstmt = connection.prepareStatement( QUERY_ALL_PRODUCT );
            rs = pstmt.executeQuery();
            productList = new ArrayList<>();
            while ( rs.next() ) {
                productList.add( rs.getString( "NAME" ) );
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
        return productList;
    }
}