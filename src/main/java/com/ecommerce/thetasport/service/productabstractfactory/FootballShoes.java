package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Rappresenta una classe concreta che estende la classe astratta {@link Shoes} e che rappresenta
 * un paio di scarpe da calcio.
 * Questa classe sovrascrive il {@link Shoes#build()} per impostare la categoria del prodotto {@link Category#FOOTBALL}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Shoes
 * @see Product
 */
public class FootballShoes extends Shoes {

    /**
     * Richiama il metodo della superclasse ed imposta la categoria del prodotto {@link Category#FOOTBALL}
     * restituisce l'istanza corrente.
     *
     * @return l'istanza corrente di {@code FootballShoes} con la categoria impostata {@link Category#FOOTBALL}
     */
    @Override
    public Product build() {
        super.build();
        this.category = Category.FOOTBALL;
        return this;
    }
}
