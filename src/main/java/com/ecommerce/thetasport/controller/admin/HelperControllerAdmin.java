package com.ecommerce.thetasport.controller.admin;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.service.productabstractfactory.ManagerProduct;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class HelperControllerAdmin {

    /**
     * Si è èreferito non utilizzare una struttura dati collection per evitare errori
     * nel passagio dei dati
     *
     * @param request
     * @param addProduct
     * @param editProduct
     * @param editSingleProduct
     * @param salesUpdates
     * @param sendOffers
     * @param queryDone
     */
    public static void setAdminPage( @NotNull HttpServletRequest request, boolean addProduct,
                                     boolean editProduct, boolean editSingleProduct, boolean salesUpdates,
                                     boolean sendOffers, boolean queryDone, boolean offerDone, boolean sendOffer ) {
        request.setAttribute( "addproduct", addProduct );
        request.setAttribute( "editproduct", editProduct );
        request.setAttribute( "editsingleproduct", editSingleProduct );
        request.setAttribute( "salesupdates", salesUpdates );
        request.setAttribute( "sendoffers", sendOffers );
        request.setAttribute( "queydone", queryDone );
        request.setAttribute( "offerDone", offerDone );
        request.setAttribute( "sendOffer", sendOffer );
        setListAndSumPrice( request );
    }

    public static void setListAndSumPrice( @NotNull HttpServletRequest request ) {
        request.setAttribute( "categoryList", ManagerProduct.getCategoryList());
        try {
            request.setAttribute( "earningMonthly", OrderDAO.getSumPriceOrderMonthly() );
            request.setAttribute( "earningYears", OrderDAO.getSumPriceOrderYear() );
            request.setAttribute( "earningTotal", OrderDAO.getSumPriceOrderTotal() );
            request.setAttribute( "orderList", OrderDAO.getTotalOrdersUsersBeanList() );
        } catch ( SQLException e ) {
            throw new RuntimeException( "SQL Error in HelperControllerAdmin" + e );
        }
    }

    public static void setAdminPage( @NotNull HttpServletRequest request, boolean value ){
        setAdminPage( request, value, value, value, value, value, value, value, value );
    }
}
