package com.ecommerce.thetasport.service.productABF;

/**
 * Classe che rappresenta una maglietta da tennis.
 * Estende la classe astratta {@link AbstractProductTennis} e implementa il metodo {@link AbstractProductTennis#build()}.
 */
public class TennisTShirt extends AbstractProductTennis {

    /**
     * Metodo che si occupa di settare la categoria ad un oggetto TennisTShirt a partire dai valori delle sue proprietà.
     * Una volta creato, viene settata la categoria a "Tennis" utilizzando
     * il metodo {@link AbstractProductTennis#build()} della superclasse, e la sotto-categoria a {@link SubCategory#TSHIRT}.
     * @return Un oggetto TennisTShirt.
     */
    @Override
    public AbstractProductTennis build(){
        super.build();
        this.subCategory = SubCategory.TSHIRT;
        return this;
    }
}
