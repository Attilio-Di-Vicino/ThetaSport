package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.model.ProductBean;

/**
 * Implementazione della factory di prodotti per {@link Category#TENNIS}.
 * Questa classe implementa l'interfaccia {@link ProductCreator} per fornire un'implementazione specifica
 * per la creazione di prodotti di tennis.
 * Utilizza la classe {@link DirectorTennis} per la creazione di prodotti in base alle specifiche fornite.
 *
 * @author Theta Sport
 * @version 1.0
 * @see ProductCreator
 * @see DirectorTennis
 */
public class TennisProductCreator implements ProductCreator {

    /**
     * Crea un prodotto di tennis utilizzando le specifiche fornite nel {@link ProductBean}.
     *
     * @param productBean l'oggetto {@link ProductBean} contenente le specifiche del prodotto da creare.
     * @return un oggetto {@link Product} di tennis creato utilizzando le specifiche fornite.
     */
    @Override
    public Product createProduct( ProductBean productBean ) {
        // Implementazione per la creazione di un prodotto di calcio
        return DirectorTennis.createProduct( productBean );
    }
}
