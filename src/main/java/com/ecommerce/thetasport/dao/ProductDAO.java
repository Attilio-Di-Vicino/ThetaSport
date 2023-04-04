package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.Product;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.*;

/**
 * Classe DAO responsabile delle interrogazioni al database riguardo i prodotti
 */
public class ProductDAO {
    private static Connection connection; // connessione al database
    private static PreparedStatement pstmt; // prepared statement per le query al database
    private static ResultSet rs; // risultato delle query
    private static final ProductBean productBean = new ProductBean(); // singolo prodotto
    private final static String QUERY_SINGLE_PRODUCT_SQL = "SELECT * FROM PRODUCT WHERE CODE = (?)";
    private static int result;
    private final static String QUERY_SOLD_SINGLE_CATEGORY_SQL = "SELECT SUM(C.QUANTITY) FROM `ORDER` " +
            "JOIN `CONTAINS` C on `ORDER`.ORDERID = C.ORDERID_C JOIN PRODUCT P on P.CODE = C.CODE " +
            "WHERE P.CATEGORY = (?) AND P.SUBCATEGORY = (?)";
    private static List<ProductBean> productBeanList; // lista di tutti i prodotti
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
     * il metodo getSingleProduct() assume in input un codice univoco
     * ed interroga il database restituendo il prodotto inerente al quel codice
     * setta il singolo productBean aiutandosi con {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)}
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     * @param code codice prodotto univoco
     * @return prodotto avente quel codice
     * @throws SQLException Definisce un'eccezione generale che si può generare
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
     * il metodo getSumSoldItemsCategory() assume in input una categoria e sotto-categoria
     * ed interroga il database restituendo la somma degli item venduti per quella categoria e sotto-categoria
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     * @param category categoria del porodotto
     * @param subCategory sotto-categoria del prodotto
     * @return somma degli item venduti per quella categoria e sotto-categoria
     * @throws SQLException Definisce un'eccezione generale che si può generare
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
     * il metodo getProductBeanList() viene utilizzato per la lista di tutti i prodotti
     * setta il singolo productBean aiutandosi con {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)}
     * per poi aggiungerlo alla lista
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
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