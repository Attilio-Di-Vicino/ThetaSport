package com.ecommerce.thetasport.service.productABF;

/**
 * Interfaccia funzionale che definisce il metodo per la creazione di una maglia da calcio.
 */
@FunctionalInterface
public interface FootballTShirtAbstractFactory {

    /**
     * Crea un'istanza di {@link AbstractProductTennis} per le magliette da calcio.
     * @return un'istanza di {@link AbstractProductTennis} per le magliette da calcio.
     */
    AbstractProductFootball createFootballTShirt();
}
