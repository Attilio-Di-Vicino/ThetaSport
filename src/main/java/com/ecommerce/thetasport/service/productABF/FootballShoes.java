package com.ecommerce.thetasport.service.productABF;

/**
 * Classe che rappresenta un paio di scarpe da calcio.
 * Estende la classe astratta {@link AbstractProductFootball} e implementa il metodo {@link AbstractProductFootball#build()}.
 */
public class FootballShoes extends AbstractProductFootball {

    /**
     * Metodo che si occupa di settare la categoria ad un oggetto FootballShoes a partire dai valori delle sue proprietà.
     * Una volta creato, viene settata la categoria a "Football" utilizzando
     * il metodo {@link AbstractProductFootball#build()} della superclasse, e la sotto-categoria a {@link SubCategory#SHOES}.
     * @return Un oggetto FootballShoes.
     */
    @Override
    public AbstractProductFootball build(){
        super.build();
        this.subCategory = SubCategory.SHOES;
        return this;
    }
}
