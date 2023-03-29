package com.ecommerce.thetasport.service.productABF;

import com.ecommerce.thetasport.model.ProductBean;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che rappresenta il direttore per la creazione di prodotti del tipo "Football".
 */
public class DirectorFootball {

    /**
     * Metodo statico che crea un prodotto del tipo "Football" sulla base delle informazioni contenute in un oggetto
     * di tipo {@link ProductBean}.
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link AbstractProductFootball} rappresentante il prodotto creato
     */
    public static AbstractProductFootball createProduct(@NotNull ProductBean productBean) {
        // Viene istanziata la factory specifica per i prodotti di tipo "Football"
        FootballConcreteFactory footballConcreteFactory = new FootballConcreteFactory();
        AbstractProductFootball newProductFootball;
        // In base alla sottocategoria del prodotto da creare viene invocato il metodo corretto della factory
        switch (productBean.getSubCategory()){
            case SHOES: newProductFootball = footballConcreteFactory.createFootballShoes();
                break;
            case TSHIRT: newProductFootball = footballConcreteFactory.createFootballTShirt();
                break;
            default: newProductFootball = null;
                break;
        }
        // Vengono inizializzate le informazioni comuni a tutti i prodotti, utilizzando l'helper "HelperProduct"
        HelperProduct.initPorduct(newProductFootball,productBean);
        return newProductFootball;
    }
}
