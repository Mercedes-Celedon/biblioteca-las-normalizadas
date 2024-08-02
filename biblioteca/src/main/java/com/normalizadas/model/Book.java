package com.normalizadas.model;

/**
 * Represents a Book
 */
public class Book {
    private int id;
    private String title;
    private String description;
    private int stock;
    private String language;
    private String isbn;

    /**
     * Creates an empty Book
     */
    public Book() {
    }

    /**
     * Creates an Author with specified data
     * @param title Book title
     * @param description Book synopsis
     * @param stock Quantity in stock
     * @param isbn ISBN code
     * @param language Laguage
     */
    public Book(String title, String description, int stock, String isbn, String language ){
        this.title = title;
        this.description = description;
        this.stock = stock;
        this.isbn = isbn;
        this.language = language;
    }

    /**
     * Function name: getId
     * @return (int)
     * Gets book id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Function name: setId
     * @param id books id
     * Sets book id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Function name: getTitle
     * @return (String)
     * Gets book title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Function name: setTitle
     * @param title book title
     * Sets book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Function name: getDescription
     * @return (String)
     * Gets book description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Function name: setDescription
     * @param description book synopsis
     * Sets book description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Function name: getStock
     * @return (int)
     * Gets book stock
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Function name: setStock
     * @param stock quantity in stock
     * Sets book stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Function name: language
     * @return (String)
     * Gets books language
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Function name: setLanguage
     * @param language book language
     * Sets book language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Function name: getIsbn
     * @return (String)
     * Gets books ISBN
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Function name: setIsbn
     * @param isbn book ISBN
     * Sets book ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    

}
