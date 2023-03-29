package com.ecommerce.thetasport.service.productABF;


/**
 * Classe astratta che rappresenta un prodotto generico di tipo calcio.
 * Estende la classe astratta {@link AbstractProduct} e implementa il metodo {@link AbstractProduct#build()}.
 */
public abstract class AbstractProductFootball extends AbstractProduct {

    /**
     * Imposta la categoria del prodotto a {@link Category#FOOTBALL}.
     * @return l'istanza del prodotto.
     */
    @Override
    public AbstractProduct build(){
        this.category = Category.FOOTBALL;
        return this;
    }
}
