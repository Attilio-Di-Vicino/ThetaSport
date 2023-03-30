package com.ecommerce.thetasport.service.productFM;

import com.ecommerce.thetasport.service.productABF.Category;
import com.ecommerce.thetasport.service.productABF.SubCategory;
import com.ecommerce.thetasport.service.productABF.Product;

public class Shoes extends Product {
    @Override
    public Product build(Category category){
        super.build(category);
        this.subCategory = SubCategory.SHOES;
        return this;
    }
}
