package com.ecommerce.thetasport.service.productABF;

/**
 * Interfaccia funzionale che rappresenta una factory astratta per la creazione di scarpe da calcio.
 */
@FunctionalInterface
public interface FootballShoesAbstractFactory {

    /**
     * Crea un'istanza di {@link AbstractProductTennis} per le scarpe da calcio.
     * @return un'istanza di {@link AbstractProductTennis} per le scarpe da calcio.
     */
    AbstractProductFootball createFootballShoes();
}
