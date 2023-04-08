package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.model.ProductBean;

import java.sql.SQLException;
import java.util.List;

public class ManagerProduct {

    public static List< ProductBean > getProductList() throws SQLException {
        return ProductDAO.getProductBeanList();
    }
}
