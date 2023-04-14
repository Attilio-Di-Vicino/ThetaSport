package com.ecommerce.thetasport.service.productabstractfactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *La classe ManagerProduct fornisce un metodo statico per ottenere<br>
 *  la lista delle categorie di prodotti disponibili nel sistema.
 */
public class ManagerProduct {
    /**
     * Restituisce la lista delle categorie di prodotti disponibili nel sistema.
     * @return una lista di oggetti Category che rappresentano le categorie di prodotti disponibili.
     */
    public static @NotNull List<Category> getCategoryList(){
        List<Category> categoryList = new ArrayList<>();
        Collections.addAll(categoryList, Category.values());
        return categoryList;
    }
}
