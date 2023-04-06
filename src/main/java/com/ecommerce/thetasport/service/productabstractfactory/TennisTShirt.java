package com.ecommerce.thetasport.service.productabstractfactory;

/**
 * Rappresenta una classe concreta che estende la classe astratta {@link TShirt} e che rappresenta
 * una maglietta da tennis.
 * Questa classe sovrascrive il {@link TShirt#build()} per impostare la categoria del prodotto {@link Category#TENNIS}.
 *
 * @author Theta Sport
 * @version 1.0
 * @see TShirt
 * @see Product
 */
public class TennisTShirt extends TShirt {

    /**
     * Override del metodo build della superclasse per creare un prodotto di maglietta da tennis.
     *
     * @return un oggetto {@link Product} di maglietta da tennis con la categoria impostata a {@link Category#TENNIS}.
     */
    @Override
    public Product build() {
        super.build();
        this.category = Category.TENNIS;
        return this;
    }
}
