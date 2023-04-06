package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * Implementazione della factory di prodotti per {@link Category#FOOTBALL}.
 * Questa classe implementa l'interfaccia {@link ProductCreator} per fornire un'implementazione specifica
 * per la creazione di prodotti di football.
 * Utilizza la classe {@link DirectorFootball} per la creazione di prodotti in base alle specifiche fornite.
 *
 * @author Theta Sport
 * @version 1.0
 * @see ProductCreator
 * @see DirectorFootball
 */
public class FootballProductCreator implements ProductCreator {

    /**
     * Metodo che crea un prodotto del tipo "Football" sulla base delle informazioni contenute in un oggetto
     * di tipo {@link ProductBean}.
     *
     * @param productBean oggetto di tipo {@link ProductBean} che contiene le informazioni del prodotto da creare
     * @return un nuovo oggetto di tipo {@link Product} rappresentante il prodotto creato
     */
    @Override
    public Product createProduct( ProductBean productBean ) {
        // Implementazione per la creazione di un prodotto di calcio
        return DirectorFootball.createProduct( productBean );
    }
}
