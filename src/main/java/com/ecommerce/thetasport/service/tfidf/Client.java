package com.ecommerce.thetasport.service.tfidf;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * La classe Client contiene test delle funzionalità.<br>
 *
 * @author Theta Sport
 * @version 1.0
 * @see ManagerTFIDF
 * @see TFIDFCalculator
 */
public class Client {

    /**
     * Il parametro TF-IDF è elevato quando la parola è molto frequente in un documento e <br>
     * il termine non è presente su tutti i documenti della banca dati. <br>
     * Questo permette di ridurre l'importanza delle parole comuni ( es. "del", "della", "un", ecc. ) e <br>
     * valorizzare gli altri termini della query dell'utente. <br>
     *
     * @param args argomento di default
     */
    public static void main( String[] args ) throws SQLException {
        Map< String, CustomPriorityQueue< String, Double > > mapResult = ManagerTFIDF.TFIDFAllUsers();
        System.out.println( "\n*** TEST MAP RESULT OF TFIDF USERS ***" );
        for ( String mail : mapResult.keySet() ) {
            System.out.println( "\nResult for " + mail + ": " );
            System.out.println( mapResult.get( mail ) );
        }
        System.out.println( "\n*** TEST OFFERS ALL USERS ALL RELATED ***" );
        Map< String, List< List<String> > > offerUserMap = ManagerTFIDF.getAllOffersAllRelated( ManagerTFIDF.TFIDFAllUsers() );
        customPrint( offerUserMap );
        System.out.println( "\n*** TEST OFFERS ALL USERS MORE RELATED ***" );
        offerUserMap = ManagerTFIDF.getAllOffersMoreRelated( ManagerTFIDF.TFIDFAllUsers() );
        customPrint( offerUserMap );
        System.out.println( "\n*** TEST OFFERS ALL USERS WITH QUANTITY RELATED (2) ***" );
        offerUserMap = ManagerTFIDF.getAllOffersWithQuantityRelated( ManagerTFIDF.TFIDFAllUsers(), 2 );
        customPrint( offerUserMap );
        System.out.println( "\n*** TEST OFFERS SINGLE USERS MORE RELATED ( attilio@gmail.com ) ***" );
        offerUserMap = ManagerTFIDF.getAllOffersMoreRelated( ManagerTFIDF.TFIDFSingleUsers( "attilio@gmail.com" ) );
        customPrint( offerUserMap );
    }

    /**
     * stampa a video la map con rispettiva mail
     *
     * @param offerUserMap map da stampare a video
     */
    private static void customPrint( @NotNull Map< String, List< List<String> > > offerUserMap ) {
        for ( String email : offerUserMap.keySet() ) {
            System.out.println( "\nFor " + email + " the offer is: " );
            System.out.println( offerUserMap.get( email ) );
        }
    }
}
