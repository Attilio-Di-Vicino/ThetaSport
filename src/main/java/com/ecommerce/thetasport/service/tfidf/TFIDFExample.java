package com.ecommerce.thetasport.service.tfidf;

import java.util.*;

public class TFIDFExample {

    public static void Test() {
        Map< String, List<String> > usersOrders = new HashMap<>();
        // ordini di tutti gli utenti
        usersOrders.put( "attilio", Arrays.asList( "scarpe", "scarpe", "maglia", "maglia" ) );
        usersOrders.put( "mario", Arrays.asList( "scarpe", "cappello", "cappello", "cappello" ) );
        usersOrders.put( "lorenzo", Arrays.asList( "scarpe", "scarpe", "maglia", "maglia", "cappello" ) );
        usersOrders.put( "carmine", Arrays.asList( "scarpe", "pantalone", "pantalone", "scarpe", "scarpe" ) );
        usersOrders.put( "camilla", Arrays.asList( "scarpe", "scarpe", "scarpe", "pantalone" ) );
        usersOrders.put( "vincenzo", Arrays.asList( "scarpe", "cappello", "cappello", "pantalone" ) );

        //
        List<String> singleDocuments = new ArrayList<>();
        List<List<String>> documents = new ArrayList<>();

        //
        Map< String, CustomPriorityQueue<String, Double> > mapResult = new HashMap<>();
        double tfidf = 0.0;

        // per tutti gli utenti
        for ( String userMail : usersOrders.keySet() ) {

            tfidf = 0.0;
            mapResult.put( userMail, new CustomPriorityQueue<>( new MyEntryComparator() ) );

            documents = new ArrayList<>();
            // per tutta la lista creare i documents
            for ( String userMail__ : usersOrders.keySet() ) {
                if ( !userMail__.equals( userMail ) ) {
                    documents.add( usersOrders.get( userMail__ ) );
                }
            }

            tfidf = 0.0;
            // per ogni singolo documento
            for ( String userMail_ : usersOrders.keySet() ) {
                tfidf = 0.0;
                // apparte che per lo user del qaule stiamo calconado i correlati
                if ( !userMail_.equals( userMail ) ) {

                    List<String> termsAlredyCalculated = new ArrayList<>();

                    // per tutti i termini
                    for ( String term : usersOrders.get( userMail ) ) {

                        if ( !termsAlredyCalculated.contains( term ) ) {

                            termsAlredyCalculated.add( term );

                            // TF-IDF
                            tfidf += TFIDFCalculator.tfIdf( usersOrders.get( userMail_ ), documents, term );

                        }
                    }
                    mapResult.get( userMail ).add( new AbstractMap.SimpleEntry<>( userMail_, tfidf ) );
                }
            }

        }

        for ( String mail : mapResult.keySet() ) {
            System.out.println( "\n Per " + mail + ": " );
            System.out.println( "\n" + mapResult.get( mail ) );
        }
    }

    public static void main(String[] args) {
        Test();
    }
}