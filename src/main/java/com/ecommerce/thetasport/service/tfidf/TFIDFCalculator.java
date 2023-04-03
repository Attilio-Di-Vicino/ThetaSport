package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.service.loginCor.Handler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;

/**
 * Il TF-IDF è un algoritmo di estrazione delle informazioni testuali da una base dati.<br>
 * è una funzione utilizzata per misurare l'importanza di un termine rispetto ad un documento o ad una collezione di documenti.<br>
 * Tale funzione aumenta proporzionalmente al numero di volte che il termine è contenuto nel documento,<br>
 * ma cresce in maniera inversamente proporzionale con la frequenza del termine nella collezione. L'idea alla base di questo<br>
 * comportamento è di dare più importanza ai termini che compaiono nel documento, ma che in generale sono poco frequenti.
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
        return Math.log( docs.size() / n );
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
        return tf( doc, term ) * idf( docs, term ) ;
    }

    /**
     * L'idea è stata quella di assumere in input una mail(chiave primaria) di un utente
     * da qui quindi prendere attraverso una query tutti gli ordini effettuatai da esso
     * a questo punto avremo una lista composta dai nomi dei prodotti ES: Air Jordan, Polo Nike, Shoes Nike
     * quindi andiamo a separare i nomi dei prodotti Es: "Shoes Nike" = "Shoes" + "Nike"
     * avremo cosi una lista di singoli termini -> term (paramentro passato in input all'algoritmo TF-IDF).
     * A questo punto andiamo a prendere tutti i prodotti presenti nel database
     * ed ogni singolo prodotto sara un singolo documento, quindi il totale di questi documenti
     * andra a comporre l'insieme dei documenti -> documents(paramentro passato in input all'algoritmo TF-IDF)
     * A questo punto abbiamo:
     *  - singleDocument: un singolo documento cioè gli ordini di un utente specifico
     *  - documents: insieme dei nomi dei prodotti presenti nel database
     *  - term: singolo termine presente nella lista di stringe singleDocument
     *  ESEMPIO 1:
     *  - supponiamo che in input assumiamo la email attilio@gmail.com
     *      i podotti acquistati da attilio sono:
     *      ["Air jordan","Air jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"]
     *      notiamo che attilio acquista due paia di Air jordan e tre paia di Mizuno.
     *      che usando la funzione per convertirli diventerrano:
     *      ["Air","jordan","Air","jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"]
     *  - supponiamo che i prodotti presenti nel database sono:
     *      1- ["Air jordan"] -> ["Air","jordan"]
     *      2- ["Air TShirt"] -> ["Air","TShirt"]
     *      3- ["Mizuno"] -> ["Mizuno"]
     *      4- ["Polo Air"] -> ["Polo", "Air"]
     *      5- ["Mizuno 100"] -> ["Mizuno", "100"]
     *      6- ["Polo"] -> ["Polo"]
     *  - supponendo che come termine consideriamo "Air" allora avremo:
     *      TF = 2/9 = 0,22
     *      IDF = log(6/3) = 0,301
     *      TF-IDF = 0,22 * 0,301 = 0,06622
     *  - supponendo che come termine consideriamo "Mizuno" allora avremo:
     *      TF = 3/9 = 0,33
     *      IDF = log(6/2) = 0.477
     *      TF-IDF = 0,33 * 0.477 = 0,157
     *  NOTIAMO CHE CON IL TERMINE "Mizuno" ABBIAMO UN RISULTATO PIÙ GRANDE
     *  ESEMPIO 2:
     *  - supponiamo che in input assumiamo la email attilio@gmail.com
     *      i podotti acquistati da attilio sono:
     *      ["Air jordan","Air jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"]
     *      notiamo che attilio acquista due paia di Air jordan e tre paia di Mizuno.
     *      che usando la funzione per convertirli diventerrano:
     *      ["Air","jordan","Air","jordan","Mizuno","Polo","Polo","Mizuno","Mizuno"]
     *  - supponiamo che i prodotti presenti nel database sono:
     *      1- ["Air jordan"] -> ["Air","jordan"]
     *      2- ["Air TShirt"] -> ["Air","TShirt"]
     *      3- ["Air Mizuno"] -> ["Air", "Mizuno"]
     *      4- ["Polo Air"] -> ["Polo", "Air"]
     *      5- ["Air Mizuno 100"] -> ["Air", "Mizuno", "100"]
     *  - supponendo che come termine consideriamo "Air" allora avremo:
     *      TF = 2/9 = 0,22
     *      IDF = log(5/5) = 0
     *      TF-IDF = 0,22 * 0 = 0 // TUTTI I PRODOTTI SONO AIR QUINDI NON È UNA PAROLA AL QUALE DARE IMPORTANZA
     *  - supponendo che come termine consideriamo "Mizuno" allora avremo:
     *      TF = 3/9 = 0,33
     *      IDF = log(5/2) = 0.69
     *      TF-IDF = 0,33 * 0.69 = 0,22
     *   NOTIAMO CHE PER "Air" ABBIAMO COME RISULTATO 0 PERCHÈ ESSENDO PRESENTE IN TUTTI I PRODOTTI
     *   HA POCO IMPORTANZA, MENTRE INVECE PER "Mizuno" ABBIAMO UN RISULTATO PIÙ GRANDE
     *   E QUINDI GLI ATTRIBUIAMO UNA MAGGIORE IMPORTANZA
     * @param email input mail user
     * @throws SQLException eccezione query
     */
    public static @NotNull CustomPriorityQueue< String, Double > TFIDFSingleUser( String email, int numberOfProducts ) throws SQLException {
        // lista dei prodotti acquistati di uno specifico utente con i nomi dei prodotti separati in singole parole
        List< String > singleDocument = ProductDAO.getProductListAllOrderSingleUser( email );
        singleDocument = HelperTFIDF.convertListStringToWordList( singleDocument );
        // quindi prendo tutti i nomi dei prodotti presenti nel database, ed andro ad dividere il nome
        // formando quindi per ogni nome una lista di stringe
        // ES: nome prodotto "Air Jordan" risultato doc1 = "Air" + "Jordan"
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
                maxQueue.add( new AbstractMap.SimpleEntry<>( term, tfIdf( singleDocument, documents, term ) ) );
            }
        }
        // inserisco i nome dei prodotti che hanno una valore maggiore
        CustomPriorityQueue< String, Double > result = new CustomPriorityQueue<>( new MyEntryComparator() );
        for ( int i = 0; i < numberOfProducts; i++ ) {
            result.add( maxQueue.poll() );
        }
        return result;
    }

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
        CustomPriorityQueue< String, Double > result = TFIDFSingleUser( "attilio@gmail.com", 2 );
        System.out.println( "\n*** TEST RESULT ***" );
        System.out.println( result );
    }
}
