package com.ecommerce.thetasport.service.productABF;

/**
 * Classe astratta che rappresenta un prodotto generico di tipo tennis.
 * Estende la classe astratta {@link AbstractProduct} e implementa il metodo {@link AbstractProduct#build()}.
 */
public abstract class AbstractProductTennis extends AbstractProduct {

    /**
     * Imposta la categoria del prodotto a {@link Category#TENNIS}.
     * @return l'istanza del prodotto.
     */
    @Override
    public AbstractProduct build(){
        this.category = Category.TENNIS;
        return this;
    }
}
