package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productabstractfactory.Category;
import com.ecommerce.thetasport.service.productabstractfactory.SubCategory;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class HelperDAO {
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

    public static void editProduct( String update, int code, String query) {
        try ( PreparedStatement pstmt = DatabaseConnection.getInstance().getConnection().prepareStatement( query ) ) {
            pstmt.setString(1, update);
            pstmt.setInt(2, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get( Calendar.MONTH ) + 1; // month index starts from 0, so 1 is added
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get( Calendar.YEAR );
    }
}
