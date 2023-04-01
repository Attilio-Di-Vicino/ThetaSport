package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TFIDFCalculator {

    /**
     * Questa componente misura la frequenza di una parola all'interno di un documento specifico.
     * Quante più occorrenze della parola si presentano nel documento,
     * tanto maggiore è il valore dell'indicatore TF.
     * TF = O(K,X) / D(X)
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public static double tf( @NotNull List< String > doc, String term ) {
        double result = 0;
        for ( String word : doc ) {
            if ( term.equalsIgnoreCase( word ) )
                result++;
        }
        return result / doc.size();
    }

    /**
     * Questo indicatore misura la frequenza inversa di una parola tra tutti i documenti.
     * E' molto alto nei termini specifici, mentre è molto basso nelle parole comuni.
     * IDF = log N / n(k)
     * @param docs list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public static double idf( @NotNull List<List< String >> docs, String term ) {
        double n = 0;
        for ( List<String> doc : docs ) {
            for ( String word : doc ) {
                if ( term.equalsIgnoreCase( word ) ) {
                    n++;
                    break;
                }
            }
        }
        return Math.log( docs.size() / n );
    }

    /**
     * TF-IDF = TF(K,X) · IDF(K)
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public static double tfIdf( List<String> doc, List< List<String> > docs, String term ) {
        return tf( doc, term ) * idf( docs, term ) ;
    }
}
