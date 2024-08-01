package com.normalizadas.model;

public class Book {
    private int id;
    private String title;
    private String description;
    private int stock;
    private String language;
    private String isbn;

    public Book() {
    }

    public Book(String title, String description, int stock, String isbn, String language ){
        this.title = title;
        this.description = description;
        this.stock = stock;
        this.isbn = isbn;
        this.language = language;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    

}
