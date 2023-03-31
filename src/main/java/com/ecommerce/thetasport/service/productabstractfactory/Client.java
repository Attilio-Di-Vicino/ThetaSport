package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class Client {
    public static void main( String[] args ) {
        // create first product
        ProductBean productBean = new ProductBean();
        productBean.setName( "Product 1" );
        productBean.setDescription( "Product 1" );
        productBean.setPrice( 10.99 );
        productBean.setStock( 10 );
        productBean.setImage( "image-uno.png" );
        productBean.setCategory( Category.TENNIS );
        productBean.setSubCategory( SubCategory.TSHIRT );
        // calling the director for object creation
        Product newProduct = DirectorTennis.createProduct( productBean );
        System.out.println( "\n*** DIRECTOR TEST ***" );
        System.out.println( "\n*** TEST FIRST PRODUCT ***" );
        System.out.println( newProduct + " " + newProduct.getCategory() + " " + newProduct.getSubCategory() );
        // create second product
        productBean.setName( "Product 2" );
        productBean.setDescription( "Product 2" );
        productBean.setPrice( 14.99 );
        productBean.setStock( 12 );
        productBean.setImage( "image-due.png" );
        productBean.setCategory( Category.FOOTBALL );
        productBean.setSubCategory( SubCategory.SHOES );
        // calling the director for object creation
        newProduct = DirectorFootball.createProduct( productBean );
        System.out.println( "\n*** TEST SECOND PRODUCT ***" );
        System.out.println( newProduct + " " + newProduct.getCategory() + " " + newProduct.getSubCategory() );
    }
}
