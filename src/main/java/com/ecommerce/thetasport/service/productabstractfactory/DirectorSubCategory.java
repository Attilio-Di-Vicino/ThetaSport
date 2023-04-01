package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

public class DirectorSubCategory {

    /**
     * Metodo statico che crea un prodotto alla categoria che viene assunta in input
     * assume anche un {@link ProductBean} che verrà utilizzato per l'inizializzazione del prodotto
     * il productBean sarà preso in input dalla pagine dedicata all'admin
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link Product} rappresentante il prodotto creato
     */
    public static Product createProduct( @NotNull ProductBean productBean, AbstractFactory concreteFactory ) {
        Product newProductFootball;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch ( productBean.getSubCategory() ) {
            case SHOES: newProductFootball = concreteFactory.createShoes();
                break;
            case TSHIRT: newProductFootball = concreteFactory.createTShirt();
                break;
            default: newProductFootball = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct( newProductFootball, productBean );
        return newProductFootball;
    }
}
