package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.service.cartvisitor.ItemElement;
import com.ecommerce.thetasport.service.cartvisitor.ShoppingCartVisitor;
import org.jetbrains.annotations.NotNull;

/**
 * Questa classe rappresenta un prodotto di tipo TShirt.
 * Questa classe estende la classe {@link Product} e implementa il metodo {@link Product#build()}
 * per impostare la sottocategoria del prodotto come {@link SubCategory#T_SHIRT}.
 * Implementa anche il metodo accept per supportare il pattern Visitor,
 * accettando un visitatore {@link ShoppingCartVisitor}.
 *
 * @version 1.0
 * @see Product
 * @see SubCategory
 * @see ShoppingCartVisitor
 */
public class TShirt extends Product {

    /**
     * Imposta la sottocategoria del prodotto come {@link SubCategory#T_SHIRT} e restituisce l'istanza corrente.
     *
     * @return l'istanza corrente di TShirt con la sottocategoria impostata come {@link SubCategory#T_SHIRT}
     */
    @Override
    public Product build() {
        this.subCategory = SubCategory.T_SHIRT;
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
        return visitor.visit(this);
    }
}
