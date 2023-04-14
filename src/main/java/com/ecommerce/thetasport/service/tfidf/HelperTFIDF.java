package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * La classe HelperTFIDF contiene metodi utili per il calcolo del TF-IDF.<br>
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperTFIDF {

    /**     DELETE
     * Converte una lista di frasi in una lista di parole.
     *
     * @param phraseList la lista di frasi da convertire
     * @return la lista di parole
     */
    public static @NotNull List<String> convertListStringToWordList( @NotNull List< String > phraseList ) {
        List< String > wordList = new ArrayList<>();
        for ( String singleWord: phraseList ) {
            String[] words = singleWord.trim().split( "\\s+" );
            Collections.addAll( wordList, words );
        }
        return wordList;
    }

    /**     DELETE
     * Converte una singola frase in una lista di parole.
     *
     * @param phraseList la frase da convertire
     * @return la lista di parole
     */
    public static @NotNull List<String> convertStringToWordList( @NotNull String phraseList ) {
        List<String> wordList = new ArrayList<>();
        String[] words = phraseList.trim().split( "\\s+" );
        Collections.addAll( wordList, words );
        return wordList;
    }

    /**
     * Questo metodo rimuove i duplicati da una lista di liste di stringhe mantenendo l'ordine preso in input
     * Quindi come risultato avremo la differenza insiemistica delle liste di liste di stringhe e l'output
     * mantiene l'ordine preso in input essendo importante
     *
     * @param originalList la lista originale da cui rimuovere i duplicati
     * @return una nuova lista di liste di stringhe senza duplicati
     * @throws NullPointerException se la lista originale è nulla
     */
    public static @NotNull List<List<String>> deleteDuplicates( List<List<String>>  originalList ) {
        if ( originalList == null ) {
            throw new NullPointerException( "Original List is null in deleteDuplicates." );
        }
        Set<String> uniqueStrings = new HashSet<>();
        for ( List<String> innerList : originalList ) {
            uniqueStrings.addAll( innerList );
        }

        List< List<String> > output = new ArrayList<>();
        for ( List<String> innerList : originalList ) {
            List< String > uniqueList = new ArrayList<>();
            for ( String str : innerList ) {
                if ( uniqueStrings.contains( str ) ) {
                    uniqueList.add( str );
                    uniqueStrings.remove( str );
                }
            }
            output.add( uniqueList );
        }
        return output;
    }
}
