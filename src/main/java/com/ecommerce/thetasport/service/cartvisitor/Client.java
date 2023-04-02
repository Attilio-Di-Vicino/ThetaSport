package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.paymentstrategy.CashStrategy;
import com.ecommerce.thetasport.service.paymentstrategy.ManagerPayments;
import com.ecommerce.thetasport.service.productabstractfactory.*;

public class Client {
    public static void main( String[] agrs )  {
        // create Cart and first product
        Cart cart = new Cart();
        ProductBean productBean = new ProductBean();
        productBean.setName( "Product one" );
        productBean.setDescription( "Product one" );
        productBean.setPrice( 88.99 );
        productBean.setStock( 12 );
        productBean.setImage( "image-one.png" );
        productBean.setCategory( Category.TENNIS );
        productBean.setSubCategory( SubCategory.TSHIRT );
        Product newProduct = DirectorTennis.createProduct( productBean );
        // add first product to cart
        cart.add( newProduct );
        // create Cart and second product
        productBean.setName( "Product two" );
        productBean.setDescription( "Product two" );
        productBean.setPrice( 14.99 );
        productBean.setStock( 12 );
        productBean.setImage( "image-due.png" );
        productBean.setCategory( Category.FOOTBALL );
        productBean.setSubCategory( SubCategory.SHOES );
        newProduct = DirectorFootball.createProduct(productBean);
        // add second product to cart
        cart.add(newProduct);
        System.out.println( "\n*** TEST CART WITH TWO PRODUCT ***" );
        System.out.println( cart );
        // create third product
        productBean.setName( "Product three" );
        productBean.setDescription( "Product three" );
        productBean.setPrice( 34.99 );
        productBean.setStock( 16 );
        productBean.setImage( "image-three.png" );
        productBean.setCategory( Category.FOOTBALL );
        productBean.setSubCategory( SubCategory.TSHIRT );
        newProduct = DirectorFootball.createProduct( productBean );
        // add third product to cart whit quantity
        cart.add( newProduct, 4 );
        System.out.println( "\n*** TEST ADD CART WITH QUANTITY AND WITH THREE PRODUCT ***" );
        System.out.println( cart );
        // decrease quantity third product
        cart.decreaseQuantity( newProduct );
        System.out.println( "\n*** TEST CART WITH DECREASE QUANTITY THIRD PRODUCT ***" );
        System.out.println( cart );
        // remove third product
        cart.remove( newProduct );
        System.out.println( "\n*** TEST CART REMOVE THIRD PRODUCT ***" );
        System.out.println( cart );
        // test add new product but equals third product
        newProduct = DirectorFootball.createProduct( productBean );
        cart.add( newProduct );
        System.out.println( "\n*** TEST ADD CART NEW PRODUCT BUT EQUALS THIRD PRODUCT ***" );
        System.out.println( cart );
        // test add new product with quantity but equals third product
        newProduct = DirectorFootball.createProduct( productBean );
        cart.add( newProduct, 10 );
        System.out.println( "\n*** TEST ADD CART NEW PRODUCT WITH QUANTITY BUT EQUALS THIRD PRODUCT ***" );
        System.out.println( cart );
        // test payment method
        ShoppingCartVisitorImpl shoppingCartVisitor = new ShoppingCartVisitorImpl( cart );
        double total = shoppingCartVisitor.getTotal();
        System.out.println( "\n*** TEST PAYMENT METHOD AND TOTAL CART ***" );
        System.out.println( "My cart total: " + total );
        ManagerPayments.pay( new CashStrategy( "attilio@gmail.com",
                "3333333333", "Via Napoli 18" ), shoppingCartVisitor.getTotal() );
        // test remove all
        cart.removeAll();
        System.out.println( "\n*** TEST CART REMOVE ALL ***" );
        System.out.println( cart );
    }
}
