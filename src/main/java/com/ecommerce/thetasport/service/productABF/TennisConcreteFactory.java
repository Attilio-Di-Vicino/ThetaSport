package com.ecommerce.thetasport.service.productABF;

/**
 * Implementazione concreta delle interfacce {@link TennisShoesAbstractFactory} e {@link TennisTShirtAbstractFactory}.
 * Questa fabbrica crea istanze delle classi {@link TennisShoes} e {@link TennisTShirt}.
 */
public class TennisConcreteFactory implements TennisShoesAbstractFactory, TennisTShirtAbstractFactory {

    /**
     * Crea un'istanza di {@link TennisShoes}.
     * @return un'istanza di {@link TennisShoes}.
     */
    @Override
    public AbstractProductTennis createTennisShoes(){
        return new TennisShoes();
    }

    /**
     * Crea un'istanza di {@link TennisTShirt}.
     * @return un'istanza di {@link TennisTShirt}.
     */
    @Override
    public AbstractProductTennis createTennisTShirt(){
        return new TennisTShirt();
    }
}
