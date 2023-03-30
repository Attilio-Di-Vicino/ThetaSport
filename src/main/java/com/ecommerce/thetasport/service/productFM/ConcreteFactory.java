package com.ecommerce.thetasport.service.productFM;

import com.ecommerce.thetasport.service.productABF.Category;
import com.ecommerce.thetasport.service.productABF.Product;

public class ConcreteFactory implements ShoesFactory, TShirtFactory, ShortsFactory, AccessoriesFactory {

    private Category category;

    public ConcreteFactory(Category category){
        this.category = category;
    }

    /**
     * Crea un'istanza della classe {@link Shoes}.
     * @return un oggetto di tipo {@link Product} rappresentante un paio di scarpe da calcio.
     */
    @Override
    public Product createShoes(){
        return new Shoes().build(this.category);
    }

    /**
     * Crea un'istanza della classe {@link TShirt}.
     * @return un oggetto di tipo {@link Product} rappresentante una maglia da calcio.
     */
    @Override
    public Product createTShirt(){
        return new TShirt().build(this.category);
    }

    /**
     * Crea un'istanza della classe {@link Shorts}.
     * @return un oggetto di tipo {@link Product} rappresentante un paio di scarpe da calcio.
     */
    @Override
    public Product createShorts(){
        return new Shorts().build(this.category);
    }

    /**
     * Crea un'istanza della classe {@link Accessories}.
     * @return un oggetto di tipo {@link Product} rappresentante una maglia da calcio.
     */
    @Override
    public Product createAccessories(){
        return new Accessories().build(this.category);
    }
}
