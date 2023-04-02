package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelperTFIDF {

    /**
     * Converta una Lista di Stringe in una lista di singole parole
     * @param phraseList lista di stringhe da convertire
     * @return lista di word
     */
    public static @NotNull List<String> convertListStringToWordList( @NotNull List< String > phraseList ) {
        List< String > wordList = new ArrayList<>();
        for ( String singleWord: phraseList ) {
            String[] words = singleWord.trim().split( "\\s+" );
            Collections.addAll( wordList, words );
        }
        return wordList;
    }

    public static @NotNull List<String> convertStringToWordList( @NotNull String phraseList ) {
        List<String> wordList = new ArrayList<>();
        String[] words = phraseList.trim().split( "\\s+" );
        Collections.addAll( wordList, words );
        return wordList;
    }
}
