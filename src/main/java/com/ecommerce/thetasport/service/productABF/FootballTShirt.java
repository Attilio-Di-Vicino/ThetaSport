package com.ecommerce.thetasport.service.productABF;

/**
 * Classe che rappresenta una maglietta da calcio.
 * Estende la classe astratta {@link AbstractProductFootball} e implementa il metodo {@link AbstractProductFootball#build()}.
 */
public class FootballTShirt extends AbstractProductFootball {

    /**
     * Metodo che si occupa di settare la categoria ad un oggetto FootballTShirt a partire dai valori delle sue proprietà.
     * Una volta creato, viene settata la categoria a "Football" utilizzando
     * il metodo {@link AbstractProductFootball#build()} della superclasse, e la sotto-categoria a {@link SubCategory#TSHIRT}.
     * @return Un oggetto FootballTShirt.
     */
    @Override
    public AbstractProductFootball build(){
        super.build();
        this.subCategory = SubCategory.TSHIRT;
        return this;
    }
}
