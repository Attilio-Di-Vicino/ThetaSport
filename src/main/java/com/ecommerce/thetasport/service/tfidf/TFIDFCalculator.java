package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Il TF-IDF è un algoritmo di estrazione delle informazioni testuali da una base dati. <br>
 * è una funzione utilizzata per misurare l'importanza di un termine rispetto ad un documento <br>
 * o ad una collezione di documenti. <br>
 * Tale funzione aumenta proporzionalmente al numero di volte che il termine è contenuto nel documento,<br>
 * ma cresce in maniera inversamente proporzionale con la frequenza del termine nella collezione. <br>
 * L'idea alla base di questo comportamento è di dare più importanza ai termini <br>
 * che compaiono nel documento, ma che in generale sono poco frequenti.
 *
 * @author Theta Sport
 * @version 1.0
 */
public class TFIDFCalculator {

    /**
     * Questa componente misura la frequenza di una parola all'interno di un documento specifico.<br>
     * Quante più occorrenze della parola si presentano nel documento,<br>
     * tanto maggiore è il valore dell'indicatore TF.<br><br>
     *
     * TF = O(K,X) / D(X)<br>
     *
     * @param doc  lista di strings
     * @param term String che rappresenta un termine
     * @return term frequency del termine nel documento
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
     * Questo indicatore misura la frequenza inversa di una parola tra tutti i documenti.<br>
     * E' molto alto nei termini specifici, mentre è molto basso nelle parole comuni.<br><br>
     *
     * IDF = log N / n(k)<br>
     *
     * @param docs list di list di string che rappresentano i documenti
     * @param term String che rappresenta un termine
     * @return inverse term frequency di un termine nei documenti
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
        if ( n == 0 ) {
            return 0;
        }
        return Math.log( docs.size() / n ); // logaritmo naturale
    }

    /**
     * Esegue il prodotto tra i rispettivi risultati del tf e del idf<br><br>
     *
     * TF-IDF = TF(K,X) · IDF(K)<br>
     *
     * @param doc singolo documento
     * @param docs tutti i documenti
     * @param term termine
     * @return TF-IDF del termine
     */
    public static double tfIdf( List<String> doc, List< List<String> > docs, String term ) {
        return tf( doc, term ) * idf( docs, term );
    }
}
