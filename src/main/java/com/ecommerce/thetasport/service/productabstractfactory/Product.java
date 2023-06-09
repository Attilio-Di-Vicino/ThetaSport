package com.ecommerce.thetasport.service.productabstractfactory;

import com.ecommerce.thetasport.service.cartvisitor.Cart;
import com.ecommerce.thetasport.service.cartvisitor.ItemElement;

/**
 * La classe astratta {@code Product} rappresenta una classe con gli attributi e metodi base
 * che deve avere un prodotto specifico.
 *
 * @author Theta Sport
 * @version 1.0
 * @see ItemElement
 */
public abstract class Product implements ItemElement {
    private int code;// Codice del prodotto
    private String name; // Nome del prodotto univoco
    private String description; // Descrizione del prodotto
    private int stock; // Quantità disponibile del prodotto
    private double price; // Prezzo del prodotto
    private String image; // Percorso dell'immagine del prodotto
    protected Category category; // Categoria del prodotto
    protected SubCategory subCategory; // Sottocategoria del prodotto

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStock() {
        return this.stock;
    }

    public double getPrice() {
        return this.price;
    }

    public String getImage() {
        return this.image;
    }

    public Category getCategory() {
        return this.category;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setCode( int code ) { this.code = code; }

    /**
     * Imposta il nome del prodotto.
     *
     * @param name Il nome del prodotto
     * @return L'oggetto {@code Product} corrente
     */
    public Product setName( String name ) {
        this.name = name;
        return this;
    }

    /**
     * Imposta la descrizione del prodotto.
     *
     * @param description La descrizione del prodotto
     * @return L'oggetto {@code Product} corrente
     */
    public Product setDescription( String description ) {
        this.description = description;
        return this;
    }

    /**
     * Imposta la descrizione del prodotto.
     *
     * @param stock La descrizione del prodotto
     * @return L'oggetto {@code Product} corrente
     */
    public Product setStock( int stock ) {
        this.stock = stock;
        return this;
    }

    /**
     * Imposta il prezzo del prodotto.
     *
     * @param price Il prezzo del prodotto
     * @return L'oggetto {@code Product} corrente
     */
    public Product setPrice( double price ) {
        this.price = price;
        return this;
    }

    /**
     * Imposta il percorso dell'immagine del prodotto.
     *
     * @param image Il percorso dell'immagine del prodotto
     * @return L'oggetto {@code Product} corrente
     */
    @SuppressWarnings( value = "Return value of the method is never used" )
    public Product setImage( String image ) {
        this.image = image;
        return this;
    }

    /**
     * Costruisce della categoria e sotto-categoria di un prodotto.
     *
     * @return Un oggetto prodotto
     */
    public abstract Product build();

    /**
     * Viene eseguito Override di {@link Object#equals(Object)}
     *
     * @param object Object da confrontare
     * @return il risultato del confronto di tipo boolean
     */
    @Override
    public boolean equals( Object object ) {
        if ( object == this ) {
            return true;
        }
        if ( !( object instanceof Product ) ) {
            return false;
        }
        Product product = ( Product ) object;
        return this.code == product.code &&
                this.name.equals( product.name ) &&
                this.description.equals( product.description ) &&
                this.stock == product.stock &&
                this.price == product.price &&
                this.image.equals( product.image ) &&
                this.category.equals( product.category ) &&
                this.subCategory.equals( product.subCategory );
    }

    /**
     * Viene eseguito Override di {@link Object#toString()}
     *
     * @return il nome del prodotto
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Viene eseguito Ovverride di {@link Object#hashCode()}
     * in modo tale da poter eseguire il controllo giusto tramite il metodo {@link Cart#add}
     * In questo caso, per il calcolo del valore hash, si utilizza una costante iniziale 17 e
     * un fattore di moltiplicazione 31 ( che è un numero primo e quindi aiuta a ridurre le collisioni ).
     * Si combinano poi tutti gli attributi della classe moltiplicandoli per il fattore di moltiplicazione e
     * sommando il risultato al valore hash.
     *
     * @return hashCode dell'oggetto rappresentato come un intero
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + code;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + stock;
        long temp = Double.doubleToLongBits( price );
        result = 31 * result + ( int ) ( temp ^ ( temp >>> 32 ) );
        result = 31 * result + image.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + subCategory.hashCode();
        return result;
    }
}
