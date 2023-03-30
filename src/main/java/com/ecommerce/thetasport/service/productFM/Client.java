package com.ecommerce.thetasport.service.productFM;

import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.service.productABF.Category;
import com.ecommerce.thetasport.service.productABF.SubCategory;
import com.ecommerce.thetasport.service.productABF.Product;

public class Client {
    public static void main(String[] args) {

        ProductBean productBean3 = new ProductBean();
        productBean3.setCategory(Category.FOOTBALL);

        ProductBean productBean = new ProductBean();
        productBean.setCategory(Category.FOOTBALL);
        productBean.setSubCategory(SubCategory.SHORTS);
        productBean.setName("p");


        Product product = Director.createProduct(productBean);
        System.out.println(product.getName() + " " + product.getCategory() + " " + product.getSubCategory());

        productBean.setCategory(Category.TENNIS);
        productBean.setSubCategory(SubCategory.TSHIRT);
        productBean.setName("p");

        product = Director.createProduct(productBean);
        System.out.println(product.getName() + " " + product.getCategory() + " " + product.getSubCategory());
    }
}
