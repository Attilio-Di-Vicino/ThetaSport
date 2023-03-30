package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che rappresenta il direttore per la creazione di prodotti del tipo "Tennis".
 */
public class DirectorTennis {

    /**
     * Metodo statico che crea un prodotto del tipo "Tennis" sulla base delle informazioni contenute in un oggetto
     * di tipo {@link ProductBean}.
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link Product} rappresentante il prodotto creato
     */
    public static Product createProduct(@NotNull ProductBean productBean) {
        // Viene istanziata la factory specifica per i prodotti di tipo "Tennis"
        TennisConcreteFactory tennisConcreteFactory = new TennisConcreteFactory();
        Product newProductTennis;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch (productBean.getSubCategory()){
            case SHOES: newProductTennis = tennisConcreteFactory.createShoes();
                break;
            case TSHIRT: newProductTennis = tennisConcreteFactory.createTShirt();
                break;
            default: newProductTennis = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct(newProductTennis,productBean);
        return newProductTennis;
    }
}
