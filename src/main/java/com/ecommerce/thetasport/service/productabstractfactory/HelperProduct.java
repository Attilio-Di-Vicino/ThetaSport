package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * La classe HelperProduct contiene metodi utili per la gestione deli prodotti.<br>
 *
 * @author Theta Sport
 * @version 1.0
 */
public class HelperProduct {

    /**
     * Inizializza il prodotto tramite il builder, impostando i valori del ProductBean.
     *
     * @param newProduct il prodotto da inizializzare.
     * @param productBean i dati del prodotto.
     * @throws NullPointerException viene lanciata nel momento in cui
     * l'oggetto in questione risulta essere null, viene passata una stringa
     * in input per identificare l'eccezione quando viene lanciata
     */
    public static void initPorduct( Product newProduct, ProductBean productBean ) {
        try {
            if ( newProduct == null ) {
                throw new NullPointerException( "Product in HelperProduct is null." );
            }
            newProduct.setName( productBean.getName() )
                    .setDescription( productBean.getDescription() )
                    .setStock( productBean.getStock() )
                    .setPrice( productBean.getPrice() )
                    .setImage( productBean.getImage() )
                    .setCode(productBean.getCode() );
        } catch ( NullPointerException e ) {
            e.printStackTrace();
        }
    }
}
