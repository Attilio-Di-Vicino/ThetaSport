package com.ecommerce.thetasport.service.productABF;

/**
 * Implementazione concreta delle interfacce {@link FootballShoesAbstractFactory} e {@link FootballTShirtAbstractFactory}.
 * Questa fabbrica crea istanze delle classi {@link FootballShoes} e {@link FootballTShirt}.
 */
public class FootballConcreteFactory implements FootballShoesAbstractFactory, FootballTShirtAbstractFactory {

    /**
     * Crea un'istanza della classe {@link FootballShoes}.
     * @return un oggetto di tipo {@link AbstractProductFootball} rappresentante un paio di scarpe da calcio.
     */
    @Override
    public AbstractProductFootball createFootballShoes(){
        return new FootballShoes();
    }

    /**
     * Crea un'istanza della classe {@link FootballTShirt}.
     * @return un oggetto di tipo {@link AbstractProductFootball} rappresentante una maglia da calcio.
     */
    @Override
    public AbstractProductFootball createFootballTShirt(){
        return new FootballTShirt();
    }
}
