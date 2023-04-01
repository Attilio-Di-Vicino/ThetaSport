package com.ecommerce.thetasport.dao;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ItemElement;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitorImpl;
import com.ecommerce.thetasport.service.paymentstrategy.BancomatStrategy;
import com.ecommerce.thetasport.service.paymentstrategy.HelperStrategy;
import com.ecommerce.thetasport.service.paymentstrategy.PaymentStrategy;
import com.ecommerce.thetasport.service.productabstractfactory.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Client {
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        // test connection
        Connection connection = DatabaseConnection.getInstance().getConnection();
        // test single product
        System.out.println( "\n*** TEST SINGLE PRODUCT DAO CODE 18 ***" );
        System.out.println( ProductDAO.getSingleProduct( 18 ) );
        System.out.println( "\n*** TEST SINGLE PRODUCT DAO CODE 38 ***" );
        System.out.println( ProductDAO.getSingleProduct( 38 ) );
        // test sum sold items
        System.out.println( "\n*** TEST SUM SOLD ITEMS FOOTBALL SHOES ***" );
        System.out.println( ProductDAO.getSumSoldItemsCategory( Category.FOOTBALL, SubCategory.SHOES ) );
        System.out.println( "\n*** TEST SUM SOLD ITEMS TENNIS T-SHIRT ***" );
        System.out.println( ProductDAO.getSumSoldItemsCategory( Category.TENNIS, SubCategory.TSHIRT ) );
        // test all product
        System.out.println( "\n*** TEST ALL PRODUCT ***" );
        List<ProductBean> productBeanList = ProductDAO.getProductBeanList();
        for ( ProductBean productBean : productBeanList ) {
            System.out.println( productBean );
        }
        /*// test insert order
        // add new products to your cart by setting only the category, sub-category, name and price
        Cart cart = new Cart();
        // add cart first product
        ProductBean productBean = ProductDAO.getSingleProduct( 10 );
        Product product = Director.createProduct( productBean );
        product.setCode( productBean.getCode() );
        cart.add( product );
        // add cart second product
        productBean = ProductDAO.getSingleProduct( 18 );
        product = Director.createProduct( productBean );
        product.setCode( productBean.getCode() );
        cart.add( product, 4 );
        // add cart second product
        productBean = ProductDAO.getSingleProduct( 30 );
        product = Director.createProduct( productBean );
        product.setCode( productBean.getCode() );
        cart.add( product, 2 );
        System.out.println( "\n*** SHOW NEW CART ***" );
        System.out.println( cart );
        // new visitor
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( cart );
        // test payment method with bancomat
        System.out.println( "\n*** TEST WITH BANCOMAT ***" );
        PaymentStrategy paymentStrategy = new BancomatStrategy( "attilio", "4456 3245 1234 6345 5567",
                "771", "2030-10-12" );
        HelperStrategy.pay( paymentStrategy, shoppingCartVisitor.getTotal() );
        ProductDAO.insertListOrder( cart.getMyCart(), "attilio@gmail.com", shoppingCartVisitor.getTotal() );
        cart.removeAll();
        System.out.println( "\n*** SHOW NEW CART ***" );
        System.out.println( cart );*/
        // test insert single product
        ProductBean productBeanTest = new ProductBean();
        productBeanTest.setName( "test" );
        productBeanTest.setDescription( "test" );
        productBeanTest.setPrice( 10.0 );
        productBeanTest.setStock( 20 );
        productBeanTest.setCategory( Category.TENNIS );
        productBeanTest.setSubCategory( SubCategory.SHOES );
        Product productTest = Director.createProduct( productBeanTest );
        ProductDAO.insertProduct( productTest );
    }
}
