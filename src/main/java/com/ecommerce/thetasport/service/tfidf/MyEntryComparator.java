package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Map;

public class MyEntryComparator implements Comparator< Map.Entry<String, Double> > {
    @Override
    public int compare( Map.@NotNull Entry<String, Double> entry1, Map.@NotNull Entry<String, Double> entry2 ) {
        // Ordinamento in ordine decrescente in base al valore associato alla chiave
        return Double.compare( entry2.getValue(), entry1.getValue() );
    }
}
