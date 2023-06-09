package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Implementazione concreta delle interfacce {@link AbstractFactoryShoes} e {@link AbstractFactoryTShirt}.<br>
 * Questa fabbrica crea istanze delle classi {@link FootballShoes} e {@link FootballTShirt}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see AbstractFactoryShoes
 * @see AbstractFactoryTShirt
 * @see FootballShoes
 * @see FootballTShirt
 */
public class FootballConcreteFactory implements AbstractFactoryShoes, AbstractFactoryTShirt {

    /**
     * Crea un'istanza della classe {@link FootballShoes}.
     *
     * @return un oggetto di tipo {@link Product} rappresentante un paio di scarpe da calcio.
     */
    @Override
    public Product createShoes() { return new FootballShoes().build(); }

    /**
     * Crea un'istanza della classe {@link FootballTShirt}.
     *
     * @return un oggetto di tipo {@link Product} rappresentante una maglia da calcio.
     */
    @Override
    public Product createTShirt() { return new FootballTShirt().build(); }
}
