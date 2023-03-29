package com.ecommerce.thetasport.service.productABF;

/**
 * Classe che rappresenta un paio di scarpe da tennis.
 * Estende la classe astratta {@link AbstractProductTennis} e implementa il metodo {@link AbstractProductTennis#build()}.
 */
public class TennisShoes extends AbstractProductTennis {

    /**
     * Metodo che si occupa di settare la categoria ad un oggetto TennisShoes a partire dai valori delle sue proprietà.
     * Una volta creato, viene settata la categoria a "Tennis" utilizzando
     * il metodo {@link AbstractProductTennis#build()} della superclasse, e la sotto-categoria a {@link SubCategory#SHOES}.
     * @return Un oggetto TennisShoes.
     */
    @Override
    public AbstractProductTennis build(){
        super.build();
        this.subCategory = SubCategory.SHOES;
        return this;
    }
}
