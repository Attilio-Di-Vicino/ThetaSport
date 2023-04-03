package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Questa classe estende {@link PriorityQueue} e implementa una coda di priorità con l'aggiunta di una mappa per gestire
 * la presenza di chiavi duplicate. Le entry sono ordinate sulla base di un {@link Comparator} personalizzato.
 * @param <K> Il tipo della chiave.
 * @param <V> Il tipo del valore.
 */
public class CustomPriorityQueue< K, V > extends PriorityQueue< Map.Entry < K, V > > {

    /**
     * La mappa che contiene le entry della coda di priorità.
     */
    private final Map< K, V > map;

    /**
     * Costruisce una nuova coda di priorità con un comparatore personalizzato.
     *
     * @param comparator Il comparatore personalizzato da utilizzare per ordinare le entry della coda di priorità.
     */
    public CustomPriorityQueue( Comparator< ? super Map.Entry<K, V> > comparator ) {
        super( comparator );
        this.map = new HashMap<>();
    }

    /**
     * Verifica se la coda di priorità contiene la chiave specificata.
     *
     * @param key La chiave da cercare nella coda di priorità.
     * @return {@code true} se la coda di priorità contiene la chiave specificata, {@code false} altrimenti.
     */
    public boolean containsKey( K key ) {
        return map.containsKey( key );
    }

    /**
     * Aggiunge la nuova entry alla coda di priorità e alla mappa.
     * Se la chiave è già presente nella mappa, la vecchia entry verrà rimossa prima di aggiungere la nuova entry.
     *
     * @param entry La nuova entry da aggiungere alla coda di priorità.
     * @return {@code true} se la nuova entry è stata aggiunta con successo alla coda di priorità, {@code false} altrimenti.
     */
    @Override
    public boolean offer( Map.Entry< K, V > entry ) {
        if ( map.containsKey( entry.getKey() ) ) {
            // Rimuovi la vecchia entry se la chiave esiste già nella mappa
            remove( entry );
        }
        // Aggiungi la nuova entry e la chiave alla mappa
        map.put( entry.getKey(), entry.getValue() );
        return super.offer( entry );
    }
}
