package com.ecommerce.thetasport.service.cartvisitor;

import com.ecommerce.thetasport.service.productabstractfactory.Shoes;
import com.ecommerce.thetasport.service.productabstractfactory.TShirt;

/**
 * L'interfaccia ShoppingCartVisitor definisce il contratto per i visitatori del carrello della spesa.
 * Un visitatore può calcolare il prezzo totale dei prodotti nel carrello in base alle diverse implementazioni dei metodi visit.
 * Implementare questa interfaccia per ogni tipo di visitatore desiderato.
 *
 * @author [Nome]
 * @version 1.0
 * @see Shoes
 * @see TShirt
 */
public interface ShoppingCartVisitor {

    /**
     * Il metodo visit calcola il prezzo totale di un prodotto Shoes nel carrello.
     *
     * @param product il prodotto Shoes da visitare
     * @return il prezzo totale del prodotto Shoes visitato
     */
    double visit( Shoes product );

    /**
     * Il metodo visit calcola il prezzo totale di un prodotto TShirt nel carrello.
     *
     * @param product il prodotto TShirt da visitare
     * @return il prezzo totale del prodotto TShirt visitato
     */
    double visit( TShirt product );
}
