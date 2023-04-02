package com.ecommerce.thetasport.service.tfidf;

import com.ecommerce.thetasport.dao.ProductDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.*;

public class TFIDFCalculator {

    /**
     * Questa componente misura la frequenza di una parola all'interno di un documento specifico.
     * Quante più occorrenze della parola si presentano nel documento,
     * tanto maggiore è il valore dell'indicatore TF.
     * TF = O(K,X) / D(X)
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
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
     * Questo indicatore misura la frequenza inversa di una parola tra tutti i documenti.
     * E' molto alto nei termini specifici, mentre è molto basso nelle parole comuni.
     * IDF = log N / n(k)
     * @param docs list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
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
     * TF-IDF = TF(K,X) · IDF(K)
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
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
    public static @NotNull Map< String, Double > TFIDFSingleUser( String email ) throws SQLException {
        // lista dei prodotti acquistati di uno specifico utente con i nomi dei prodotti separati in singole parole
        List< String > singleDocument = ProductDAO.getProductListAllOrderSingleUser( email );
        singleDocument = HelperTFIDF.convertListStringToWordList( singleDocument );


        System.out.println( "\n*** TEST SINGLE DOCUMENT ***" );
        int i = 0;
        for ( String word : singleDocument ) {
            System.out.println( "word: " + i++ + ": " + word );
        }


        // quindi prendo tutti i nomi dei prodotti presenti nel database, ed andro ad dividere il nome
        // formando quindi per ogni nome una lista di stringe
        // ES: nome prodotto "Air Jordan" risultato doc1 = "Air" + "Jordan"
        List< String > productNameList = ProductDAO.getAllProductList();
        List< List<String> > documents = new ArrayList<>();
        for ( String singleProductName: productNameList ) {
            List< String > doc = HelperTFIDF.convertStringToWordList( singleProductName );
            documents.add( doc );
        }


        System.out.println( "\n*** TEST ALL DOCUMENT ***" );
        i = 0;
        for ( List<String> list : documents ) {
            for ( String word : list ) {
                System.out.println( "word all product " + i++ + ": " + word );
            }
        }


        Map< String, Double > resultTreeMapTFIDF = new TreeMap<>();
        for ( String term : singleDocument ) {
            Double index = resultTreeMapTFIDF.get( term );
            if ( index == null ) {
                // TF-IDF non è stato ancora calcolato
                resultTreeMapTFIDF.put( term, tfIdf( singleDocument, documents, term ) );
            }
        }


        System.out.println( "\n*** TEST MAP ***" );
        System.out.println( resultTreeMapTFIDF );


        // da modificare
        Map<String, Double> result = new HashMap<>();
        Map.Entry<String, Double> maxEntry = Collections.max(resultTreeMapTFIDF.entrySet(), Map.Entry.comparingByValue());
        result.put(maxEntry.getKey(), maxEntry.getValue());
        resultTreeMapTFIDF.remove(maxEntry.getKey(),maxEntry.getValue());
        maxEntry = Collections.max(resultTreeMapTFIDF.entrySet(), Map.Entry.comparingByValue());
        result.put(maxEntry.getKey(), maxEntry.getValue());
        return result;
    }


    /**
     * Il parametro TF-IDF è elevato quando la parola è molto frequente in un documento e
     * il termine non è presente su tutti i documenti della banca dati.
     * Questo permette di ridurre l'importanza delle parole comuni ( es. "del", "della", "un", ecc. ) e
     * valorizzare gli altri termini della query dell'utente.
     * @param args main
     * @throws SQLException test
     */
    public static void main(String[] args) throws SQLException {

        System.out.println("ATTILIO");
        Map<String, Double> result = TFIDFSingleUser("attilio@gmail.com");
        System.out.println(result);
    }
}
