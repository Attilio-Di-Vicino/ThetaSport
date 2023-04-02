package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelperTFIDF {

    /**
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

    /**
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
}
