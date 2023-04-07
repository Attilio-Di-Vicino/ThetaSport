package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.dao.ProductDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class BackupTFIDF {

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
    public static @NotNull CustomPriorityQueue< String, Double > TFIDFSingleUser(String email ) throws SQLException {
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
}
