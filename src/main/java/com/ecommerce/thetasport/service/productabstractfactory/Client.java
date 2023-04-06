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
        productBean.setSubCategory( SubCategory.T_SHIRT);
        // calling the director for object creation
        Product newProduct = Director.createProduct( productBean );
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
        newProduct = Director.createProduct( productBean );
        System.out.println( "\n*** TEST SECOND PRODUCT ***" );
        System.out.println( newProduct + " " + newProduct.getCategory() + " " + newProduct.getSubCategory() );
        // create three product
        // calling the director for object creation
        Product newProduct3 = Director.createProduct( productBean );
        System.out.println( "\n*** TEST THREE PRODUCT ***" );
        System.out.println( newProduct3 + " " + newProduct3.getCategory() + " " + newProduct3.getSubCategory() );
        // test equals and hashcode
        System.out.println( "\n*** TEST EQUALS AND HASHCODE ***" );
        System.out.println( "Equals between second product and three product: "
                + newProduct.equals( newProduct3 ) );
        System.out.println( "HashCode between second product and three product: "
                + "\n Product 2: " + newProduct.hashCode() + " Product 3: " + newProduct3.hashCode() );
        // delete
        productBean = new ProductBean();
        productBean.setName( "Product 1" );
        productBean.setDescription( "Product 1" );
        productBean.setPrice( 10.99 );
        productBean.setStock( 10 );
        productBean.setImage( "image-uno.png" );
        productBean.setCategory( Category.TENNIS );
        productBean.setSubCategory( SubCategory.T_SHIRT);
        // calling the director for object creation
        newProduct = Director.createProduct( productBean );
        System.out.println( "\n*** DIRECTOR TEST ***" );
        System.out.println( "\n*** TEST FIRST PRODUCT ***" );
        System.out.println( newProduct + " " + newProduct.getCategory() + " " + newProduct.getSubCategory() );

    }
}
