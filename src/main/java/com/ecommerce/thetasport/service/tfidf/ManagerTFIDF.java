package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.dao.UserDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;

/**
 * La classe ManagerTFIDF contiene metodi utili per la gestione delle funzionalità dell'admin.<br>
 *
 * @author Theta Sport
 * @version 1.0
 * @see TFIDFCalculator
 * @see CustomPriorityQueue
 * @see MyEntryComparator
 */
public class ManagerTFIDF {

    /**
     * Calcola il punteggio TF-IDF per ogni utente e restituisce una map che ha come chiave la mail<br>
     * dell'utente di riferimento, e come valore è associato una coda di massima priorità<br>
     * la quale è ordinata in bale al valore del TF-IDF {@link TFIDFCalculator}.
     *
     * @return la mappa contenente i risultati TF-IDF per ogni user il quale è associato ad una coda di massima priorità
     */
    public static @NotNull Map< String, CustomPriorityQueue< String, Double > > TFIDFAllUsers() {
        // Map contenente i risultati TF-IDF per ogni user il quale
        // è associato ad una coda di massima priorità
        Map< String, CustomPriorityQueue< String, Double > > mapResultTFIDF = new HashMap<>();
        List< List<String> > documents;
        double sumTFIDF;
        try {
            // questa map associa ad ogni user la sua lista di ordini effettuati nel tempo
            Map< String, List<String> > usersOrders = new HashMap<>();
            // inizialmente recupero tutte le email apparte la email dell'admin
            List< String > userMails = UserDAO.getUsersMailMinusOne( "admin" );
            for ( String email : userMails ) {
                // associo ad ogni users la sua lista di ordini effettuati
                usersOrders.put( email, ProductDAO.getProductListAllOrderSingleUser( email ) );
            }
            // per tutti gli utenti
            for ( String userMail : usersOrders.keySet() ) {
                // utilizzo una coda mi massima priorità custom
                mapResultTFIDF.put( userMail, new CustomPriorityQueue<>( new MyEntryComparator() ) );
                List<String> singleDocument = usersOrders.get( userMail );
                for ( String userMailVs : usersOrders.keySet() ) {
                    // non verrà calcolato per se stesso
                    if ( !userMail.equals( userMailVs ) ) {
                        documents = new ArrayList<>();
                        // creazione dei documents, in questo caso i documents sono i prodotti ordinati da
                        // un singolo utente
                        // per tutta la lista del primo documents che conterrà la lista degli ordini
                        // del primo utente cioè userMailVs
                        for ( String product : usersOrders.get( userMailVs ) ) {
                            documents.add( Collections.singletonList( product ) );
                        }
                        List<String> termsAlredyCalculated = new ArrayList<>();
                        // per tutti i term
                        sumTFIDF = 0.0;
                        for ( String term : singleDocument ) {
                            if ( !termsAlredyCalculated.contains( term ) ) {
                                termsAlredyCalculated.add( term );
                                sumTFIDF += TFIDFCalculator.tfIdf( singleDocument, documents, term );
                            }
                        }
                        // aggiungo alla map il risultato appena calcolato con la mail dello user in questione
                        mapResultTFIDF.get( userMail ).add( new AbstractMap.SimpleEntry<>( userMailVs, sumTFIDF ) );
                    }
                }
            }
        } catch ( SQLException e ) {
            System.err.println( "Error executing SQL query on TFIDFAllUsers: " + e.getMessage() );
            e.printStackTrace();
        }
        return mapResultTFIDF;
    }

    /**
     * Questo metodo restituisce una mappa che associa ad ogni email contenuta nella chiave della mappa mapResultTFIDF una lista di prodotti.<br>
     * Utilizza il metofo {@link ManagerTFIDF#getAllOffersWithQuantityRelated(Map, int)}<br>
     * @see ManagerTFIDF#getAllOffersWithQuantityRelated(Map, int)
     *
     * @param mapResultTFIDF una mappa che associa ad ogni email una coda di priorità personalizzata di termini TF-IDF
     * @return Una mappa che associa ad ogni email una lista di prodotti che possono essere offerti all'utente associato
     */
    public static @NotNull Map< String, List<List<String>> > getAllOffersMoreRelated(Map< String, CustomPriorityQueue< String, Double > > mapResultTFIDF ) {
        return ManagerTFIDF.getAllOffersWithQuantityRelated( mapResultTFIDF, 1 );
    }

    /**
     * Questo metodo restituisce una mappa che associa ad ogni email contenuta nella chiave della mappa mapResultTFIDF una lista di prodotti.<br>
     * Utilizza il metofo {@link ManagerTFIDF#getAllOffersWithQuantityRelated(Map, int)}
     * @see ManagerTFIDF#getAllOffersWithQuantityRelated(Map, int)
     *
     * @param mapResultTFIDF una mappa che associa ad ogni email una coda di priorità personalizzata di termini TF-IDF
     * @return Una mappa che associa ad ogni email una lista di prodotti che possono essere offerti all'utente associato
     */
    public static @NotNull Map< String, List<List<String>> > getAllOffersAllRelated( Map< String, CustomPriorityQueue< String, Double > > mapResultTFIDF ) {
        return ManagerTFIDF.getAllOffersWithQuantityRelated( mapResultTFIDF, mapResultTFIDF.size() - 1 );
    }

    /**
     * Questo metodo restituisce una mappa che associa ad ogni email contenuta nella chiave della mappa mapResultTFIDF una lista di prodotti.<br>
     * La lista di prodotti rappresenta l'insieme dei prodotti che possono essere offerti all'utente associato all'email corrispondente,<br>
     * sulla base della sua attività di acquisto passata e delle attività di acquisto degli altri utenti nella mappa mapResultTFIDF.<br>
     * La lista di prodotti è organizzata in modo che i primi elementi siano quelli che sono stati acquistati con<br>
     * maggiore frequenza dagli altri utenti nella mappa mapResultTFIDF
     *
     * @param mapResultTFIDF una mappa che associa ad ogni email una coda di priorità personalizzata di termini TF-IDF
     * @param numberOfUserRelated il numero di utenti che devono essere considerati nella costruzione dell'offerta per ciascun utente
     * @return Una mappa che associa ad ogni email una lista di prodotti che possono essere offerti all'utente associato
     */
    public static @NotNull Map< String, List<List<String>> > getAllOffersWithQuantityRelated( Map< String, CustomPriorityQueue< String, Double > > mapResultTFIDF, int numberOfUserRelated ) {
        Map< String, List<List<String>> > offerUserMap = new HashMap<>();
        try {
            if ( mapResultTFIDF == null ) {
                throw new NullPointerException( "Map Result TF-IDF is null." );
            }
            for ( String email : mapResultTFIDF.keySet() ) {
                offerUserMap.put( email, new ArrayList<>() );
            }
            for ( String email : mapResultTFIDF.keySet() ) {
                CustomPriorityQueue< String, Double > userRelatedMaxQueue = mapResultTFIDF.get( email );
                if ( numberOfUserRelated > userRelatedMaxQueue.size() ) {
                    numberOfUserRelated = userRelatedMaxQueue.size();
                }
                for ( int i = 0; i < numberOfUserRelated; i++ ) {
                    String userMailRelated = Objects.requireNonNull( userRelatedMaxQueue.poll() ).getKey();
                    List< String > userProductRelated = ProductDAO.getProductListAllOrderSingleUser( userMailRelated );
                    // lista ordini dell'utente al quale si vuole fare l'offerta
                    List< String > userProductOffer = ProductDAO.getProductListAllOrderSingleUser( email );
                    userProductRelated.removeAll( userProductOffer );
                    offerUserMap.get( email ).add( userProductRelated );
                }
                offerUserMap.put( email, HelperTFIDF.deleteDuplicates( offerUserMap.get( email ) ) );
            }
        } catch ( SQLException e ) {
            System.err.println( "Error executing SQL query on getAllOffersWithQuantityRelated: " + e.getMessage() );
            e.printStackTrace();
        }
        return offerUserMap;
    }
}
