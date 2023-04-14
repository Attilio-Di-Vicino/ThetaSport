package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * La classe HelperDAO contiene metodi utili per la gestione dei DAO. <br>
 * Questi metodi vengono utilizzati per ottenere informazioni sui prodotti e gestire il database.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperDAO {

    /**
     * Imposta i valori di ProductBean con i dati restituiti da ResultSet.
     *
     * @param productBean il bean del prodotto da aggiornare
     * @param rs il ResultSet contenente i dati del prodotto
     * @throws SQLException se si verifica un errore durante l'accesso al database
     */
    public static void setSingleProductBean( @NotNull ProductBean productBean, @NotNull ResultSet rs ) throws SQLException {
        productBean.setCode( rs.getInt( "CODE" ) );
        productBean.setName( rs.getString( "NAME" ) );
        productBean.setDescription( rs.getString( "DESCRIPTION" ) );
        productBean.setStock( rs.getInt( "STOCK" ) );
        productBean.setPrice( rs.getDouble( "PRICE" ) );
        productBean.setCategory( Category.valueOf( rs.getString( "CATEGORY" ).toUpperCase().trim() ) );
        productBean.setSubCategory( SubCategory.valueOf( rs.getString( "SUBCATEGORY" ).toUpperCase().trim() ) );
        productBean.setImage( rs.getString( "IMAGE" ) );
    }

    /**
     * Modifica il valore di una colonna nella tabella dei prodotti nel database.
     *
     * @param update il nuovo valore da inserire
     * @param code il codice del prodotto da modificare
     * @param query la query SQL per eseguire l'aggiornamento
     */
    public static void editProduct( String update, int code, String query) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( query ) ) {
            pstmt.setString(1, update);
            pstmt.setInt(2, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restituisce il numero del mese corrente (1-12).
     *
     * @return il numero del mese corrente
     */
    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get( Calendar.MONTH ) + 1; // month index starts from 0, so 1 is added
    }

    /**
     * Restituisce l'anno corrente.
     *
     * @return l'anno corrente
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get( Calendar.YEAR );
    }
}
