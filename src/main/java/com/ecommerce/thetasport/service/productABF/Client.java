package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;

public class Client {
    public static void main(String[] args) {
        ProductBean productBean = new ProductBean();
        productBean.setName("prova");
        productBean.setDescription("prova");
        productBean.setPrice(100);
        productBean.setStock(10);
        productBean.setImage("image");
        productBean.setCategory(Category.FOOTBALL);
        productBean.setSubCategory(SubCategory.SHOES);

        AbstractProduct productFootball = DirectorFootball.createProduct(productBean);
        System.out.println(productFootball.getCategory() + " " + productFootball.getSubCategory());

        productFootball = DirectorTennis.createProduct(productBean);
        System.out.println(productFootball.getCategory() + " " + productFootball.getSubCategory());
    }
}
