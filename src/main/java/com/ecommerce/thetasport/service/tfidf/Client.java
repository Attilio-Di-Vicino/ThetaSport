package com.ecommerce.thetasport.service.tfidf;

import java.sql.SQLException;
import java.util.AbstractMap;

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

        CustomPriorityQueue< String, Double > maxQueue = new CustomPriorityQueue<>( new MyEntryComparator() );
        // TF-IDF non è stato ancora calcolato rispetto al term corrente
        maxQueue.add( new AbstractMap.SimpleEntry<>( "uno", 1.0 ) );
        maxQueue.add( new AbstractMap.SimpleEntry<>( "uno", 2.0 ) );
        maxQueue.add( new AbstractMap.SimpleEntry<>( "uno", 3.0 ) );
        System.out.println( "\nTest" );
        System.out.println( maxQueue );

    }
}
