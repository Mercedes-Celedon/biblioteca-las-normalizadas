package com.normalizadas.model;

public class Book {
    private int id;
    private String title;
    private String description;
    private int stock;
    private int id_language;
    private String isbn;

    public Book(){}

    public Book(String title, String description, int stock, String isbn, int id_language ){
        this.title = title;
        this.description = description;
        this.stock = stock;
        this.isbn = isbn;
        this.id_language = id_language;
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

    public int getId_language() {
        return this.id_language;
    }

    public void setId_language(int id_language) {
        this.id_language = id_language;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    

}
