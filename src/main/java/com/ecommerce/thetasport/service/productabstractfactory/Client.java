package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

public class Client {
    public static void main(String[] args) {
        ProductBean productBean = new ProductBean();
        productBean.setName("prova");
        productBean.setDescription("prova");
        productBean.setPrice(100);
        productBean.setStock(10);
        productBean.setImage("image");
        productBean.setCategory(Category.TENNIS);
        productBean.setSubCategory(SubCategory.TSHIRT);

        Product newProduct = null;
        switch (productBean.getCategory()){
            case FOOTBALL: newProduct = DirectorFootball.createProduct(productBean);
                break;
            case TENNIS: newProduct = DirectorTennis.createProduct(productBean);
                break;
            default:
                System.out.println("c");
                break;
        }
        System.out.println(newProduct.getCategory() + " " + newProduct.getSubCategory());
    }
}
