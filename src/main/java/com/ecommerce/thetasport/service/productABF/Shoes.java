package com.ecommerce.thetasport.service.productABF;

public class Shoes extends Product{
    @Override
    public Product build(Category category){
        super.build(category);
        this.subCategory = SubCategory.SHOES;
        return this;
    }
}
