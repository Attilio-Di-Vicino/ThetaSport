package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerProduct {

    public static List< ProductBean > getProductList() throws SQLException {
        return ProductDAO.getProductBeanList();
    }

    public static ProductBean getSingleProduct( int code ) throws SQLException, ClassNotFoundException {
        return ProductDAO.getSingleProduct( code );
    }

    public static @NotNull List<Category> getCategoryList(){
        List<Category> categoryList = new ArrayList<>();
        Collections.addAll(categoryList, Category.values());
        return categoryList;
    }
}
