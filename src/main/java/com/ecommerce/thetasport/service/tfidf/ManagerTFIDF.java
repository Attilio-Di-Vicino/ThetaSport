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
     * È il numero massimo di valori forniti dall'algoritmo TF-IDF al fine di fornire prodotti correlati
     */
    private static final int BEST_TFIDF_VALUES = 2;

    /**
     * L'idea è stata quella di assumere in input una mail ( chiave primaria ) di un utente <br>
     * da qui quindi prendere attraverso una query tutti gli ordini effettuatai da esso <br>
     * a questo punto avremo una lista composta dai nomi dei prodotti <i>ES: Air Jordan, Polo Nike, Shoes Nike </i><br>
     * quindi andiamo a separare i nomi dei prodotti <i>Es: "Shoes Nike" = "Shoes" , "Nike" </i><br>
     * avremo cosi una lista di singoli termini ->
     * <strong> term (paramentro passato in input all'algoritmo TF-IDF). </strong><br><br>
     *
     * A questo punto andiamo a prendere tutti i prodotti presenti nel database <br>
     * ed ogni singolo prodotto sara un singolo documento, quindi il totale di questi documenti <br>
     * andra a comporre l'insieme dei documenti ->
     * <strong> documents(paramentro passato in input all'algoritmo TF-IDF) </strong><br><br>
     *
     * A questo punto abbiamo: <br>
     *  - <strong>singleDocument:</strong> un singolo documento cioè gli ordini di un utente specifico <br>
     *  - <strong>documents:</strong> insieme dei nomi dei prodotti presenti nel database <br>
     *  - <strong>term:</strong> singolo termine presente nella lista di stringe singleDocument <br><br><br>
     *
     *  <strong>ESEMPIO 1: </strong><br><br>
     *
     *  - supponiamo che in input assumiamo la email <i>attilio@gmail.com</i> <br>
     *      i podotti acquistati da attilio sono: <br>
     *      <i>["Air jordan","Air jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"]</i> <br>
     *      notiamo che attilio acquista due paia di Air jordan e tre paia di Mizuno. <br>
     *      che usando la funzione per convertirli diventerrano: <br>
     *      <strong>["Air","jordan","Air","jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"] <br>
     *      notiamo che ad esempio Mizuno compare tre volte nel singolo documento <br>
     *      questo perchè nel calcolo del TF deve risultare 3 volte </strong><br><br>
     *
     *  - supponiamo che i prodotti presenti nel database sono: <br>
     *      <strong>
     *      1- ["Air jordan"] -> ["Air","jordan"] <br>
     *      2- ["Air TShirt"] -> ["Air","TShirt"] <br>
     *      3- ["Mizuno"] -> ["Mizuno"] <br>
     *      4- ["Polo Air"] -> ["Polo", "Air"] <br>
     *      5- ["Mizuno 100"] -> ["Mizuno", "100"] <br>
     *      6- ["Polo"] -> ["Polo"] </strong><br><br>
     *  - supponendo che come termine consideriamo "Air" allora avremo: <br>
     *      <strong>
     *      TF = 2/9 = 0,22 <br>
     *      IDF = log(6/3) = 0,301 <br>
     *      TF-IDF = 0,22 * 0,301 = 0,06622 </strong><br><br>
     *  - supponendo che come termine consideriamo "Mizuno" allora avremo: <br>
     *      <strong>
     *      TF = 3/9 = 0,33 <br>
     *      IDF = log(6/2) = 0.477 <br>
     *      TF-IDF = 0,33 * 0.477 = 0,157 <br>
     *  NOTIAMO CHE CON IL TERMINE "Mizuno" ABBIAMO UN RISULTATO PIÙ GRANDE </strong><br><br><br>
     *
     *  <strong>ESEMPIO 2: </strong><br><br>
     *
     *  - supponiamo che in input assumiamo la email <i>lorenzo@gmail.com </i><br>
     *      i podotti acquistati da lorenzo sono: <br>
     *      <i>["Air jordan","Air jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"] </i><br>
     *      notiamo che lorenzo acquista due paia di Air jordan e tre paia di Mizuno. <br>
     *      che usando la funzione per convertirli diventerrano: <br>
     *      <strong>["Air","jordan","Air","jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"] </strong><br><br>
     *
     *  - supponiamo che i prodotti presenti nel database sono: <br>
     *      <strong>
     *      1- ["Air jordan"] -> ["Air","jordan"] <br>
     *      2- ["Air TShirt"] -> ["Air","TShirt"] <br>
     *      3- ["Air Mizuno"] -> ["Air", "Mizuno"] <br>
     *      4- ["Polo Air"] -> ["Polo", "Air"] <br>
     *      5- ["Air Mizuno 100"] -> ["Air", "Mizuno", "100"] </strong><br><br>
     *
     *  - supponendo che come termine consideriamo "Air" allora avremo: <br>
     *      <strong>
     *      TF = 2/9 = 0,22 <br>
     *      IDF = log(5/5) = 0 <br>
     *      TF-IDF = 0,22 * 0 = 0 <br>
     *      TUTTI I PRODOTTI SONO AIR QUINDI NON È UNA PAROLA AL QUALE DARE IMPORTANZA </strong><br><br>
     *
     *  - supponendo che come termine consideriamo "Mizuno" allora avremo: <br>
     *      <strong>
     *      TF = 3/9 = 0,33 <br>
     *      IDF = log(5/2) = 0.69 <br>
     *      TF-IDF = 0,33 * 0.69 = 0,22 <br>
     *   NOTIAMO CHE PER "Air" ABBIAMO COME RISULTATO 0 PERCHÈ ESSENDO PRESENTE IN TUTTI I PRODOTTI <br>
     *   HA POCO IMPORTANZA, MENTRE INVECE PER "Mizuno" ABBIAMO UN RISULTATO PIÙ GRANDE <br>
     *   E QUINDI GLI ATTRIBUIAMO UNA MAGGIORE IMPORTANZA </strong><br>
     *
     * @param email input mail user
     * @throws SQLException Definisce un'eccezione generale che si può generare
     */
    public static @NotNull CustomPriorityQueue< String, Double > TFIDFSingleUser( String email ) throws SQLException {
        // lista dei prodotti acquistati di uno specifico utente con i nomi dei prodotti separati in singole parole
        List< String > singleDocument = ProductDAO.getProductListAllOrderSingleUser( email );
        singleDocument = HelperTFIDF.convertListStringToWordList( singleDocument );
        // quindi prendo tutti i nomi dei prodotti presenti nel database, ed andrò ad dividere il nome
        // formando quindi per ogni nome una lista di stringe
        // ES: nome prodotto "Air Jordan" risultato doc1 = "Air" , "Jordan"
        List< String > productNameList = ProductDAO.getAllProductList();
        List< List<String> > documents = new ArrayList<>();
        for ( String singleProductName: productNameList ) {
            List< String > doc = HelperTFIDF.convertStringToWordList( singleProductName );
            documents.add( doc );
        }
        // Creazione di una nuova istanza della classe CustomPriorityQueue
        CustomPriorityQueue< String, Double > maxQueue = new CustomPriorityQueue<>( new MyEntryComparator() );
        for ( String term : singleDocument ) {
            if ( !maxQueue.containsKey( term ) ) {
                // TF-IDF non è stato ancora calcolato rispetto al term corrente
                maxQueue.add( new AbstractMap.SimpleEntry<>( term, TFIDFCalculator.tfIdf( singleDocument, documents, term ) ) );
            }
        }
        // inserisco i nome dei prodotti che hanno una valore maggiore
        CustomPriorityQueue< String, Double > result = new CustomPriorityQueue<>( new MyEntryComparator() );
        // questo controllo viene eseguito nel momento in cui il valore preso in input
        // è maggiore del size della coda di massima priorità, di conseguenza non potrà
        // essere formato da un numero maggiore rispetto al size della maxQueue
        int bestValues = BEST_TFIDF_VALUES;
        if ( bestValues > maxQueue.size() ) {
            bestValues = maxQueue.size();
        }
        for ( int i = 0; i < bestValues; i++ ) {
            result.add( maxQueue.poll() );
        }
        return result;
    }

    public static void getAllRelatedOffers() throws SQLException {
        // lista di prodotti
        //List< String > productNameList = ProductDAO.getProductListAllOrderSingleUser( "attilio@gmail.com" );
        // productNameList = HelperTFIDF.convertListStringToWordList( productNameList );

        /*for ( String singleProductName: orderSingleUser ) {
            List< String > doc = HelperTFIDF.convertStringToWordList( singleProductName );
            documents.add( doc );
        }*/
        /*List< String > userBeanList = UserDAO.getUsersMail();
        List< List<String> > documents = new ArrayList<>();
        for ( String email : userBeanList ) {
            List< String > orderSingleUser = ProductDAO.getProductListAllOrderSingleUser( email );
            documents.add( orderSingleUser );
        }*/
        CustomPriorityQueue< String, Double > maxQueue = new CustomPriorityQueue<>( new MyEntryComparator() );
        List< String > userBeanList = UserDAO.getUsersMailMinusOne( "admin" );
        // per tutti gli utente meno l'admin
        String email = "attilio@gmail.com";
        //for ( String email : userBeanList ) {
        // prendi gli acquisti di un utente corrente
        List< String > singleDocument = ProductDAO.getProductListAllOrderSingleUser( email );
        //Map< String, List<String> > documents = new HashMap<>();
        // lista di tutti gli acquisti da tutti gli utenti
        List< List<String> > documents = new ArrayList<>();
        List< String > userBeanListMinusOne = UserDAO.getUsersMailMinusOne( email );
        for ( String emailMinusOne : userBeanListMinusOne ) {
            List< String > orderSingleUser = ProductDAO.getProductListAllOrderSingleUser( emailMinusOne );
            // costruzione dei documenti
            documents.add( orderSingleUser );
            //documents.put( emailMinusOne, orderSingleUser );
        }
        //System.out.println( "\n\n" + email + " : " + singleDocument );
        //System.out.println( "\n\n" + documents );

            /*List< List<String> > documents1 = new ArrayList<>();
            for ( String key : documents.keySet() ) {
                documents1.add( documents.get(key) );
            }*/
        maxQueue = new CustomPriorityQueue<>( new MyEntryComparator() );
        // per ogni prodotto acquistato dall'utente corrente
        for ( String term : singleDocument ) {
            for ( String emailMinusOne : userBeanListMinusOne ) {
                //if ( !maxQueue.containsKey( term ) ) {
                // TF-IDF non è stato ancora calcolato rispetto al term corrente
                System.out.println( "TF-IDF: " + term + " : " + TFIDFCalculator.tfIdf( singleDocument, documents, term )  );
                maxQueue.add( new AbstractMap.SimpleEntry<>( emailMinusOne, TFIDFCalculator.tfIdf( singleDocument, documents, term ) ) );
                //}
            }
        }
        //}
        System.out.println( "\n\n\n\n" + "users: " + email + " maxQueue: " + maxQueue );
    }

    public static void prova(){
        List<List<String>> acquisti = new ArrayList<>();
        acquisti.add(Arrays.asList("Scarpe", "Maglietta", "Pantaloni")); // Attilio
        acquisti.add(Arrays.asList("Scarpe", "Maglietta")); // Mario
        acquisti.add(Arrays.asList("Scarpe", "Pantaloni")); // Lorenzo
        acquisti.add(Arrays.asList("Scarpe", "Maglietta", "Giacca")); // Giuseppe

        Map<String, Map<Integer, Double>> tfIdfMap = new HashMap<>();

        for (int i = 0; i < acquisti.size(); i++) {
            List<String> acquisto = acquisti.get(i);
            for (String term : acquisto) {
                double tfIdf = TFIDFCalculator.tfIdf(acquisto, acquisti, term);
                Map<Integer, Double> userMap = tfIdfMap.getOrDefault(term, new HashMap<>());
                userMap.put(i, tfIdf);
                tfIdfMap.put(term, userMap);
            }
        }
        System.out.println( tfIdfMap );
    }


    /**
     * Calcola i prodotti correlati basati sul TF-IDF.
     *
     * @param acquisti lista degli acquisti effettuati dagli utenti
     * @param prodotto prodotto di riferimento per il calcolo dei prodotti correlati
     * @return lista dei prodotti correlati
     */
    private static List<String> getProdottiCorrelati(List<List<String>> acquisti, String prodotto) {
        List<String> prodottiCorrelati = new ArrayList<>();

        // Calcola il TF-IDF per ogni prodotto nei documenti di acquisto
        for (List<String> acquisto : acquisti) {
            double tfIdf = TFIDFCalculator.tfIdf(acquisto, acquisti, prodotto);
            if (tfIdf > 0) {
                // Aggiungi il prodotto alla lista dei prodotti correlati se ha un TF-IDF maggiore di 0
                for (String prodottoAcquisto : acquisto) {
                    if (!prodottoAcquisto.equals(prodotto) && !prodottiCorrelati.contains(prodottoAcquisto)) {
                        prodottiCorrelati.add(prodottoAcquisto);
                    }
                }
            }
        }

        return prodottiCorrelati;
    }

    /**
     * Propone offerte basate sui prodotti correlati.
     *
     * @param prodottiCorrelati lista dei prodotti correlati
     * @return lista delle offerte consigliate
     */
    private static List<String> getOfferte(List<String> prodottiCorrelati) {
        List<String> offerte = new ArrayList<>();

        // Aggiungi offerte basate sui prodotti correlati
        for (String prodotto : prodottiCorrelati) {
            offerte.add("Acquista " + prodotto + " e ricevi uno sconto del 10% sul prezzo totale!");
        }

        return offerte;
    }


    public static void main(String[] args) throws SQLException {
        // Lista degli acquisti effettuati dagli utenti
        List<List<String>> acquisti = new ArrayList<>();
        acquisti.add(Arrays.asList("maglia", "pallone", "scarpe"));
        acquisti.add(Arrays.asList("pallone", "borraccia"));
        acquisti.add(Arrays.asList("scarpe", "borraccia"));
        acquisti.add(Arrays.asList("pallone", "scarpe"));

        // Calcola i prodotti correlati basati sul TF-IDF
        List<String> prodottiCorrelati = getProdottiCorrelati(acquisti, "scarpe");

        // Stampa i prodotti correlati
        System.out.println("Prodotti correlati per 'scarpe':");
        for (String prodotto : prodottiCorrelati) {
            System.out.println("- " + prodotto);
        }

        // Propone offerte basate sui prodotti correlati
        List<String> offerte = getOfferte(prodottiCorrelati);

        // Stampa le offerte
        System.out.println("\nOfferte consigliate:");
        for (String offerta : offerte) {
            System.out.println("- " + offerta);
        }
        //prova();
    }
}
