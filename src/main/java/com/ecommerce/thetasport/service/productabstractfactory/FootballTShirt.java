package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Rappresenta una classe concreta che estende la classe astratta {@link TShirt} e che rappresenta
 * un paio di scarpe da calcio.
 * Questa classe sovrascrive il {@link TShirt#build()} per impostare la categoria del prodotto come FOOTBALL.
 *
 * @author Theta Sport
 * @version 1.0
 * @see TShirt
 * @see Product
 */
public class FootballTShirt extends TShirt {

    /**
     * Richiama il metodo della superclasse ed imposta la categoria del prodotto come FOOTBALL<br>
     * restituisce l'istanza corrente.
     *
     * @return l'istanza corrente di {@code FootballTShirt} con la categoria impostata come FOOTBALL
     */
    @Override
    public Product build() {
        super.build();
        this.category = Category.FOOTBALL;
        return this;
    }
}
