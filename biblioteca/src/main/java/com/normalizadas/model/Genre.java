package com.normalizadas.model;

public class Genre {
    private int id;
    private String genre;

    public Genre(){}

    public Genre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    
}
