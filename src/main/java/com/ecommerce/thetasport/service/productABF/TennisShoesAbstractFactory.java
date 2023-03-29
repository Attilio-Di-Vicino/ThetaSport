package com.ecommerce.thetasport.service.productABF;

/**
 * Interfaccia funzionale che definisce un'Abstract Factory per la creazione di prodotti
 * appartenenti alla categoria delle scarpe da tennis.
 */
@FunctionalInterface
public interface TennisShoesAbstractFactory {

    /**
     * Crea un'istanza di {@link AbstractProductTennis} per le scarpe da tennis.
     * @return un'istanza di {@link AbstractProductTennis} per le scarpe da tennis.
     */
    AbstractProductTennis createTennisShoes();
}
