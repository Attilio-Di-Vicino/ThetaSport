package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CustomPriorityQueue< K, V > extends PriorityQueue< Map.Entry < K, V > > {

    private final Map< K, V > map;

    public CustomPriorityQueue( Comparator< ? super Map.Entry<K, V> > comparator ) {
        super( comparator );
        this.map = new HashMap<>();
    }

    public boolean containsKey( K key ) {
        return map.containsKey( key );
    }

    @Override
    public boolean offer( Map.@NotNull Entry< K, V > entry ) {
        if ( map.containsKey( entry.getKey() ) ) {
            // Rimuovi la vecchia entry se la chiave esiste già nella mappa
            remove( entry );
        }
        // Aggiungi la nuova entry e la chiave alla mappa
        map.put( entry.getKey(), entry.getValue() );
        return super.offer( entry );
    }
}
