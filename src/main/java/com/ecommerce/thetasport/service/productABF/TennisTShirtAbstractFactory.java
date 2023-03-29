package com.ecommerce.thetasport.service.productABF;

/**
 * Interfaccia funzionale che definisce un'Abstract Factory per la creazione di prodotti
 * appartenenti alla categoria maglie da tennis.
 */
@FunctionalInterface
public interface TennisTShirtAbstractFactory {

    /**
     * Crea un'istanza di {@link AbstractProductTennis} per le magliette da tennis.
     * @return un'istanza di {@link AbstractProductTennis} per le magliette da tennis.
     */
    AbstractProductTennis createTennisTShirt();
}
