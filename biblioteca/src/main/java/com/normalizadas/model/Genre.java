package com.normalizadas.model;

/**
 * Represents a Genre
 */
public class Genre {
    private int id;
    private String genre;

    /**
     * Creates an empty Genre
     */
    public Genre(){}

    /**
     * Creates a Genrse with specified name
     * @param genre genre's name
     */
    public Genre(String genre) {
        this.genre = genre;
    }

    /**
     * Function name: getId
     * @return (int)
     * Gets genre id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Function name: setId
     * @param id genre id
     * Sets genre id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Function name: getGenre
     * @return (String)
     * Gets genres name
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Function name: setGenre
     * @param genre genres name
     * Sets genres name
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }    
}
