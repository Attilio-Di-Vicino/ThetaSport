package com.ecommerce.thetasport.service.productABF;

/**
 * Classe astratta che rappresenta un prodotto generico.
 */
public abstract class AbstractProduct {
    private int code;// Codice del prodotto
    private String name; // Nome del prodotto
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

    public String getImage(){
        return this.image;
    }

    public Category getCategory() {
        return this.category;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    /**
     * Imposta il nome del prodotto.
     * @param name Il nome del prodotto
     * @return L'oggetto {@code AbstractProduct} corrente
     */
    public AbstractProduct setName(String name){
        this.name = name;
        return this;
    }

    /**
     * Imposta la descrizione del prodotto.
     * @param description La descrizione del prodotto
     * @return L'oggetto {@code AbstractProduct} corrente
     */
    public AbstractProduct setDescription(String description){
        this.description = description;
        return this;
    }

    /**
     * Imposta la descrizione del prodotto.
     * @param stock La descrizione del prodotto
     * @return L'oggetto {@code AbstractProduct} corrente
     */
    public AbstractProduct setStock(int stock){
        this.stock = stock;
        return this;
    }

    /**
     * Imposta il prezzo del prodotto.
     * @param price Il prezzo del prodotto
     * @return L'oggetto {@code AbstractProduct} corrente
     */
    public AbstractProduct setPrice(double price){
        this.price = price;
        return this;
    }

    /**
     * Imposta il percorso dell'immagine del prodotto.
     * @param image Il percorso dell'immagine del prodotto
     * @return L'oggetto {@code AbstractProduct} corrente
     */
    public AbstractProduct setImage(String image){
        this.image = image;
        return this;
    }

    /**
     * Costruisce della categoria di un prodotto.
     * @return Un oggetto prodotto
     */
    public abstract AbstractProduct build();
}
