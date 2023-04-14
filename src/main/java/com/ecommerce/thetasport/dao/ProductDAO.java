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
 *
 * @author Theta Sport
 * @version 1.0
 */
public class ProductDAO {
    private final static String QUERY_SINGLE_PRODUCT_SQL = "SELECT * FROM PRODUCT WHERE CODE = (?)";
    private final static String QUERY_SOLD_SINGLE_CATEGORY_SQL = "SELECT SUM(C.QUANTITY) FROM `ORDER` " +
            "JOIN `CONTAINS` C on `ORDER`.ORDERID = C.ORDERID_C JOIN PRODUCT P on P.CODE = C.CODE " +
            "WHERE P.CATEGORY = (?) AND P.SUBCATEGORY = (?)";
    private final static String QUERY_ALL_PRODUCT_SQL = "SELECT * FROM PRODUCT";
    private final static String INSERT_SINGLE_PRODUCT_SQL = "INSERT INTO PRODUCT (NAME, DESCRIPTION, STOCK, " +
            "PRICE, CATEGORY, SUBCATEGORY, IMAGE) VALUES (?,?,?,?,?,?,?)";
    private final static String UPDATE_NAME_SQL = "UPDATE PRODUCT SET NAME = (?) WHERE CODE = (?)";
    private final static String UPDATE_DESCRIPTION_SQL = "UPDATE PRODUCT SET DESCRIPTION = (?) WHERE CODE = (?)";
    private final static String UPDATE_STOCK_SQL = "UPDATE PRODUCT SET STOCK = (?) WHERE CODE = (?)";
    private final static String UPDATE_PRICE_SQL = "UPDATE PRODUCT SET PRICE = (?) WHERE CODE = (?)";
    private final static String UPDATE_IMAGE_SQL = "UPDATE PRODUCT SET IMAGE = (?) WHERE CODE = (?)";
    private final static String QUERY_PRODUCT_ORDER_SINGLE_USER_SQL = "SELECT P.NAME,C.QUANTITY FROM PRODUCT P " +
            "JOIN CONTAINS C on P.CODE = C.CODE JOIN `ORDER` O on O.ORDERID = C.ORDERID_C " +
            "JOIN USER U on O.EMAIL = U.EMAIL WHERE O.EMAIL = (?)";

    /**
     * il metodo getSingleProduct() assume in input un codice univoco <br>
     * ed interroga il database restituendo il prodotto inerente al quel codice <br>
     * setta il singolo productBean aiutandosi con {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)} <br>
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     *
     * @param code codice prodotto univoco
     * @return prodotto avente quel codice
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static ProductBean getSingleProduct( int code ) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductBean productBean = new ProductBean();
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_SINGLE_PRODUCT_SQL );
            pstmt.setString( 1, String.valueOf( code ) );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                HelperDAO.setSingleProductBean( productBean, rs );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
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
     * il metodo getSumSoldItemsCategory() assume in input una categoria e sotto-categoria <br>
     * ed interroga il database restituendo la somma degli item venduti per quella categoria e sotto-categoria <br>
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     *
     * @param category categoria del porodotto
     * @param subCategory sotto-categoria del prodotto
     * @return somma degli item venduti per quella categoria e sotto-categoria
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static int getSumSoldItemsCategory( @NotNull Category category, @NotNull SubCategory subCategory ) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_SOLD_SINGLE_CATEGORY_SQL );
            pstmt.setString( 1, category.toString() );
            pstmt.setString( 2, subCategory.toString() );
            rs = pstmt.executeQuery();
            if ( rs.next() ) {
                result = rs.getInt( 1 );
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
     * il metodo getProductBeanList() viene utilizzato per la lista di tutti i prodotti <br>
     * setta il singolo productBean aiutandosi con {@link HelperDAO#setSingleProductBean(ProductBean, ResultSet)} <br>
     * per poi aggiungerlo alla lista <br>
     * infine nel blocco finally vengono chiusi connection, prepared statement ed result set
     *
     * @return lista di tutti i prodotti
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List<ProductBean> getProductBeanList() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductBean> productBeanList = new ArrayList<>();
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( QUERY_ALL_PRODUCT_SQL );
            rs = pstmt.executeQuery();
            while ( rs.next() ) {
                ProductBean productBean = new ProductBean();
                HelperDAO.setSingleProductBean( productBean, rs );
                productBeanList.add( productBean );
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
        return productBeanList;
    }

    /**
     * il metodo insertProduct() permette di inserire un prodotto all'interno del databese <br>
     * nel caso in cui l'immagine non viene inserita viene caricata un immagine di default <br>
     * considerando che solo l'admin può inserire i prodotti, e che lato client, quindi <br>
     * nella view dell'admin sono stati fatti vari controlli tramire HTML5
     *
     * @param product product da inserire
     */
    public static void insertProduct( @NotNull Product product ) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement( INSERT_SINGLE_PRODUCT_SQL ) ) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getStock());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setString(5, product.getCategory().toString());
            pstmt.setString(6, product.getSubCategory().toString());
            // in questo caso non è necessaria un'eccezione
            String image = product.getImage();
            if (image == null || image.equals("")) {
                image = "default-image.jpeg";
            }
            pstmt.setString(7, image);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * il metodo editNameProduct() permette di modificare il nome di un prodotto <br>
     * si avvale del metodo {@link HelperDAO#editProduct(String, int, String)}
     *
     * @param name nuovo nome del prodotto
     * @param code codice del prodotto che deve essere modificato
     */
    public static void editNameProduct( String name, int code ) {
        HelperDAO.editProduct( name, code, UPDATE_NAME_SQL );
    }

    /**
     * il metodo editDescriptionProduct() permette di modificare la descrizione di un prodotto <br>
     * si avvale del metodo {@link HelperDAO#editProduct(String, int, String)}
     *
     * @param description nuova descrizione del prodotto
     * @param code codice del prodotto che deve essere modificato
     */
    public static void editDescriptionProduct( String description, int code ) {
        HelperDAO.editProduct( description, code, UPDATE_DESCRIPTION_SQL );
    }

    /**
     * il metodo editStockProduct() permette di modificare lo stock di un prodotto
     *
     * @param stock nuovo stock del prodotto
     * @param code codice del prodotto che deve essere modificato
     */
    public static void editStockProduct( int stock, int code ) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement( UPDATE_STOCK_SQL ) ) {
            pstmt.setInt(1, stock);
            pstmt.setInt(2, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * il metodo editPriceProduct() permette di modificare il prezzo di un prodotto
     *
     * @param price nuovo prezzo del prodotto
     * @param code codice del prodotto che deve essere modificato
     */
    public static void editPriceProduct( double price, int code ) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection()
                .prepareStatement( UPDATE_PRICE_SQL ) ) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * il metodo editImageProduct() permette di modificare l'immagine di un prodotto <br>
     * si avvale del metodo {@link HelperDAO#editProduct(String, int, String)}
     *
     * @param image nuova immagine del prodotto
     * @param code codice del prodotto che deve essere modificato
     */
    @SuppressWarnings( value = "Method 'editImageProduct(java.lang.String, int)' is never used" )
    public static void editImageProduct( String image, int code ) {
        HelperDAO.editProduct( image, code, UPDATE_IMAGE_SQL );
    }

    /**
     * il metodo getProductListAllOrderSingleUser() esegue un'interrogazione al database <br>
     * e restituisce una lista di string la quale conterrà tutti i prodotti acquistati da uno <br>
     * specifico utente, e se un pordotto è stato acquistato più di una volta allora sarà <br>
     * inserito quante volte è stato acquistato, ES: "Air Jordan" acquistato 2 volte <br>
     * la lista avrà 2 elementi al suo interno che sono "Air Jordan".
     *
     * @param email mail che fa riferimento ad un utente specifico
     * @return lista di string contente i pordotti acquistati da un singolo utente
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static List<String> getProductListAllOrderSingleUser( String email ) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> productList = new ArrayList<>();
        try {
            pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement(QUERY_PRODUCT_ORDER_SINGLE_USER_SQL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("QUANTITY");
                for (int i = 0; i < quantity; i++) {
                    productList.add(rs.getString("NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }
}