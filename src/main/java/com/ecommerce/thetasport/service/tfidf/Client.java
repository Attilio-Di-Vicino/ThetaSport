package com.ecommerce.thetasport.service.tfidf;

import java.sql.SQLException;

/**
 * La classe Client contiene test delle funzionalità.<br>
 *
 * @author Theta Sport
 * @version 1.0
 * @see ManagerTFIDF
 */
public class Client {
    /**
     * Il parametro TF-IDF è elevato quando la parola è molto frequente in un documento e <br>
     * il termine non è presente su tutti i documenti della banca dati. <br>
     * Questo permette di ridurre l'importanza delle parole comuni ( es. "del", "della", "un", ecc. ) e <br>
     * valorizzare gli altri termini della query dell'utente. <br>
     *
     * @param args argomento di default
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static void main( String[] args ) throws SQLException {
        System.out.println( "ATTILIO" );
        CustomPriorityQueue< String, Double > result = ManagerTFIDF.TFIDFSingleUser( "attilio@gmail.com" );
        System.out.println( "\n*** TEST RESULT ***" );
        System.out.println( result );
    }
}
