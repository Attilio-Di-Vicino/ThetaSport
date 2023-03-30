package com.ecommerce.thetasport.service.productABF;

public class TShirt extends Product{
    @Override
    public Product build(Category category){
        super.build(category);
        this.subCategory = SubCategory.TSHIRT;
        return this;
    }
}
