package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;

import java.sql.SQLException;
import java.util.*;

//public class TFIDFExample {

    /*public static void tfidfExample() throws SQLException {
        Map< String, List<String> > usersOrders = new HashMap<>();
        // ordini di tutti gli utenti
        usersOrders.put( "attilio", Arrays.asList( "scarpe", "scarpe", "maglia", "maglia" ) );
        usersOrders.put( "mario", Arrays.asList( "scarpe", "cappello", "cappello", "cappello" ) );
        usersOrders.put( "lorenzo", Arrays.asList( "scarpe", "scarpe", "maglia", "maglia", "cappello" ) );
        usersOrders.put( "carmine", Arrays.asList( "scarpe", "pantalone", "pantalone", "scarpe", "scarpe" ) );
        usersOrders.put( "camilla", Arrays.asList( "scarpe", "scarpe", "scarpe", "pantalone" ) );
        usersOrders.put( "vincenzo", Arrays.asList( "scarpe", "cappello", "cappello", "pantalone" ) );
        //
        List< List<String> > documents;
        //
        Map< String, CustomPriorityQueue<String, Double> > mapResult = new HashMap<>();
        double sumTFIDF;
        // per tutti gli utenti
        for ( String userMail : usersOrders.keySet() ) {
            mapResult.put( userMail, new CustomPriorityQueue<>( new MyEntryComparator() ) );
            documents = new ArrayList<>();
            // per tutta la lista creare i documents senza il singleDocument della persona corrente
            for ( String userMailDocuments : usersOrders.keySet() ) {
                if ( !userMailDocuments.equals( userMail ) ) {
                    documents.add( usersOrders.get( userMailDocuments ) );
                }
            }
            // per ogni singolo documento
            for ( String userMailSingleDocument : usersOrders.keySet() ) {
                // setting sumTFIDF
                sumTFIDF = 0.0;
                // calcolare il TF-IDF di un utente apparte che per l'utente corrente
                if ( !userMailSingleDocument.equals( userMail ) ) {
                    List<String> termsAlredyCalculated = new ArrayList<>();
                    // per tutti i termini una e una sola volta altrimenti sarebbe ridondante
                    for ( String term : usersOrders.get( userMail ) ) {
                        // se non è stato gia calcolato per questo termine specifico
                        if ( !termsAlredyCalculated.contains( term ) ) {
                            termsAlredyCalculated.add( term );
                            // TF-IDF per tutti i "term" sommato per un singleDocument
                            sumTFIDF += TFIDFCalculator.tfIdf( usersOrders.get( userMailSingleDocument ), documents, term );
                        }
                    }
                    // aggiungo alla map il risultato appena calcolato con la mail dello user in questione
                    mapResult.get( userMail ).add( new AbstractMap.SimpleEntry<>( userMailSingleDocument, sumTFIDF ) );
                }
            }
        }
        for ( String mail : mapResult.keySet() ) {
            System.out.println( "\nResult of " + mail + ": " );
            System.out.println( mapResult.get( mail ) );
        }
    }

    public static void main(String[] args) throws SQLException {
        tfidfExample();
    }
}*/