package com.ecommerce.thetasport.service.productABF;

/**
 * Implementazione concreta delle interfacce {@link ShoesFactory} e {@link TShirtFactory}.
 * Questa fabbrica crea istanze delle classi {@link Shoes} e {@link TShirt}.
 */
public class FootballConcreteFactory implements ShoesFactory, TShirtFactory {

    /**
     * Crea un'istanza della classe {@link Shoes}.
     * @return un oggetto di tipo {@link Product} rappresentante un paio di scarpe da calcio.
     */
    @Override
    public Product createShoes(){
        return new Shoes().build(Category.FOOTBALL);
    }

    /**
     * Crea un'istanza della classe {@link TShirt}.
     * @return un oggetto di tipo {@link Product} rappresentante una maglia da calcio.
     */
    @Override
    public Product createTShirt(){
        return new TShirt().build(Category.FOOTBALL);
    }
}
