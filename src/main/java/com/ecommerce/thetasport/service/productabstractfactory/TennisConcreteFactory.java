package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Implementazione concreta delle interfacce {@link AbstractFactoryShoes} e {@link AbstractFactoryTShirt}.
 * Questa fabbrica crea istanze delle classi {@link TennisShoes} e {@link TennisTShirt}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see AbstractFactoryShoes
 * @see AbstractFactoryTShirt
 * @see TennisShoes
 * @see TennisTShirt
 */
public class TennisConcreteFactory implements AbstractFactoryShoes, AbstractFactoryTShirt {

    /**
     * Crea un'istanza della classe {@link TennisShoes}.
     *
     * @return un oggetto di tipo {@link Product} rappresentante un paio di scarpe da tennis.
     */
    @Override
    public Product createShoes() { return new TennisShoes().build(); }

    /**
     * Crea un'istanza della classe {@link TennisTShirt}.
     *
     * @return un oggetto di tipo {@link Product} rappresentante una maglia da tennis.
     */
    @Override
    public Product createTShirt() { return new TennisTShirt().build(); }
}
