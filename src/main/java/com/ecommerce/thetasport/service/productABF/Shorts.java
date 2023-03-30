package com.ecommerce.thetasport.service.productABF;

public class Shorts extends Product {
    @Override
    public Product build(Category category){
        super.build(category);
        this.subCategory = SubCategory.SHORTS;
        return this;
    }
}
