package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * La classe {@code Director} si occupa di creare e restituire i prodotti, utilizzando il creatore
 * di prodotti associato alla categoria del prodotto. <br>
 *
 * Il metodo principale della classe è {@link #createProduct(ProductBean)}, che
 * prende in input un oggetto {@link ProductBean} <br>
 * e restituisce un oggetto {@link Product} creato dal creatore di prodotti associato alla categoria del prodotto.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Category
 * @see Product
 */
public class Director {

    /**
     * Crea e restituisce un prodotto utilizzando il creatore di prodotti associato alla categoria del prodotto.
     *
     * @param productBean l'oggetto {@code ProductBean} che contiene le informazioni del prodotto
     * @return un oggetto {@code Product} creato dal creatore di prodotti associato alla categoria del prodotto
     * @throws IllegalArgumentException se la categoria del prodotto non ha un creatore di prodotti associato
     */
    public static Product createProduct( ProductBean productBean ) {
        if ( productBean == null ) {
            throw new NullPointerException( "ProductBean in Director/createProduct is null." );
        }
        ProductCreator creator = productBean.getCategory().getCreator();
        if ( creator == null ) {
            throw new IllegalArgumentException( "Invalid product category in Director/createProduct." );
        }
        return creator.createProduct( productBean );
    }
}
