package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Questa classe estende {@link PriorityQueue} e implementa una coda di priorità con l'aggiunta di una mappa per gestire <br>
 * la presenza di chiavi duplicate. Le entry sono ordinate sulla base di un {@link Comparator} personalizzato. <br>
 *
 * @param <K> Il tipo della chiave.
 * @param <V> Il tipo del valore.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class CustomPriorityQueue< K, V > extends PriorityQueue< Map.Entry < K, V > > {

    /**
     * La mappa che contiene le entry della coda di priorità.
     */
    private final Map< K, V > map;

    /**
     * Costruisce una nuova coda di priorità con un {@link Comparator} personalizzato.
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
    public boolean offer( Map.@NotNull Entry< K, V > entry ) {
        if ( this.map.containsKey( entry.getKey() ) ) {
            return false;
        }
        // Aggiungi la nuova entry e la chiave alla mappa
        this.map.put( entry.getKey(), entry.getValue() );
        return super.offer( entry );
    }

    /**
     * viene utilizzato un oggeto di tipo {@link StringBuilder} <br>
     * che ci permette di construire output tramite il pattern builder
     *
     * @return stringa elenco della coda di massima priorità
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Iterator< Map.Entry<K, V> > iterator = this.map.entrySet().iterator();
        while ( iterator.hasNext() ) {
            Map.Entry<K, V> entry = iterator.next();
            stringBuilder.append( entry.getKey() ).append( " -> " ).append( entry.getValue() );
            if ( iterator.hasNext() ) {
                stringBuilder.append( "\n" );
            }
        }
        return stringBuilder.toString();
    }
}
