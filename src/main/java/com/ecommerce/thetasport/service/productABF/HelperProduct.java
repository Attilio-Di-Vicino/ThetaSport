package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;

public class HelperProduct {
    public static void initPorduct(AbstractProduct newProduct, ProductBean productBean){
        try {
            if ( newProduct == null ) {
                throw new NullPointerException("Product is null.");
            }
            newProduct.build()
                    .setName(productBean.getName())
                    .setDescription(productBean.getDescription())
                    .setStock(productBean.getStock())
                    .setPrice(productBean.getPrice())
                    .setImage(productBean.getImage());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
