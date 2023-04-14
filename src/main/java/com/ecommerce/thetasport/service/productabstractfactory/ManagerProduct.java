package com.ecommerce.thetasport.service.productabstractfactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerProduct {

    public static @NotNull List<Category> getCategoryList(){
        List<Category> categoryList = new ArrayList<>();
        Collections.addAll(categoryList, Category.values());
        return categoryList;
    }
}
