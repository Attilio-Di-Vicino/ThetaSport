package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.service.cartvisitor.ItemElement;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitor;
import org.jetbrains.annotations.NotNull;

/**
 * Questa classe rappresenta un prodotto di tipo scarpe.
 * Questa classe estende la classe {@link Product} e implementa il metodo {@link Product#build()}
 * per impostare la sottocategoria del prodotto come SHOES.
 * Implementa anche il metodo accept per supportare il pattern Visitor,
 * accettando un visitatore {@link ShoppingCartVisitor}.
 *
 * @version 1.0
 * @see Product
 * @see SubCategory
 * @see ShoppingCartVisitor
 */
public class Shoes extends Product {

    /**
     * Imposta la sottocategoria del prodotto come {@link SubCategory#SHOES} e restituisce l'istanza corrente.
     *
     * @return l'istanza corrente di Shoes con la sottocategoria impostata come {@link SubCategory#SHOES}
     */
    @Override
    public Product build() {
        this.subCategory = SubCategory.SHOES;
        return this;
    }

    /**
     * Implementa il metodo {@link ItemElement#accept(ShoppingCartVisitor)} per supportare il pattern Visitor,
     * accettando un visitatore {@link ShoppingCartVisitor} e restituendo il valore restituito dal metodo visit()
     * del visitatore.
     *
     * @param visitor il visitatore del carrello
     * @return il valore restituito dal metodo visit() del visitatore
     * @throws NullPointerException se il parametro visitor è null
     */
    @Override
    public double accept( @NotNull ShoppingCartVisitor visitor ){
        return visitor.visit( this );
    }
}
