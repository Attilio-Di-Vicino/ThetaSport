package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Rappresenta una classe concreta che estende la classe astratta {@link Shoes} e che rappresenta
 * un paio di scarpe da tennis.
 * Questa classe sovrascrive il {@link Shoes#build()} per impostare la categoria del prodotto {@link Category#TENNIS}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see Shoes
 * @see Product
 */
public class TennisShoes extends Shoes {

    /**
     * Override del metodo build della superclasse per creare un prodotto di scarpe da tennis.
     *
     * @return un oggetto {@link Product} di scarpe da tennis con la categoria impostata a {@link Category#TENNIS}.
     */
    @Override
    public Product build() {
        super.build();
        this.category = Category.TENNIS;
        return this;
    }
}
