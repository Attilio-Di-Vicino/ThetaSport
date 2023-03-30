package com.ecommerce.thetasport.service.productABF;

public class Accessories extends Product {
    @Override
    public Product build(Category category){
        super.build(category);
        this.subCategory = SubCategory.ACCESSORIES;
        return this;
    }
}
