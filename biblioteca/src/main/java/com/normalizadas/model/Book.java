package com.normalizadas.model;

public class Book {
    private int id;
    private String title;
    private String description;
    private int stock;
    private int id_language;
    private String isbn;
    private int id_author;
    private int id_genre;

    public Book(){}

    public Book(String title, String description, int stock, String isbn, int id_language ){
        this.title = title;
        this.description = description;
        this.stock = stock;
        this.isbn = isbn;
        this.id_language = id_language;
        /*Preguntar si aquí va id_author y id_genre */
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

    public int getId_author() {
        return this.id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public int getId_genre() {
        return this.id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }
    

}
