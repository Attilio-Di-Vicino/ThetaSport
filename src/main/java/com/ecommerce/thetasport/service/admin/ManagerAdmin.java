package com.ecommerce.thetasport.service.admin;

import com.ecommerce.thetasport.dao.OrderDAO;
import com.ecommerce.thetasport.dao.ProductDAO;
import com.ecommerce.thetasport.model.ProductBean;
import com.ecommerce.thetasport.model.TotalOrdersUsersBean;
import com.ecommerce.thetasport.service.tfidf.CustomPriorityQueue;
import com.ecommerce.thetasport.service.tfidf.HelperTFIDF;
import com.ecommerce.thetasport.service.tfidf.ManagerTFIDF;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManagerAdmin {

    /*private static final int NUMBER_RELATED_OFFERS = 3;

    public static @NotNull List< ProductBean > getOfferSingleUser(String email ) throws SQLException {
        List< ProductBean > productBeanListOffer = new ArrayList<>();
        CustomPriorityQueue< String, Double > resultTFIDF = BackupTFIDF.TFIDFSingleUser( email );
        System.out.println( resultTFIDF );
        List< ProductBean > productBeanList = ProductDAO.getProductBeanList();
        // qui c'è da capire se è meglio eliminare il term dalla queue o no
        while ( !resultTFIDF.isEmpty() && productBeanListOffer.size() < NUMBER_RELATED_OFFERS ) {
            Map.Entry< String, Double > amap = resultTFIDF.poll();
            for ( ProductBean productBean : productBeanList ) {
                if ( productBeanListOffer.size() >= NUMBER_RELATED_OFFERS ) {
                    break;
                }
                List< String > wordList = HelperTFIDF.convertStringToWordList( productBean.getName() );
                for ( String word : wordList ) {
                    if ( word.equals( amap.getKey() ) ) {
                        productBeanListOffer.add( productBean );
                    }
                    if ( productBeanListOffer.size() >= NUMBER_RELATED_OFFERS ) {
                        break;
                    }
                }
            }
        }
        return productBeanListOffer;
    }

    public static void main( String[] args ) throws SQLException {
        List< ProductBean > productBeanList = getOfferSingleUser( "attilio@gmail.com" );
        System.out.println( "\n*** TEST TF-IDF OFFER ***" );
        System.out.println( "Number of products for offer is: " + productBeanList.size() );
        System.out.println( productBeanList );
    }*/

    public static void main(String[] args) {
        System.out.println();
    }
}
