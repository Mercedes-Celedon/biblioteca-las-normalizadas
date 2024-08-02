package com.normalizadas.model;

/**
 * Represents an Author
 */
public class Author {
    private int id;
    private String name;

    /**
     * Creates an empty Author
     */
    public Author(){}

    /**
     * Creates an Author with specified name
     * @param name Author's name
     */
    public Author( String name) {
        this.name = name;
    }

    /**
     * Function name: getId
     * @return (int)
     * Gets author's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Function name: setId
     * @param id author's id
     * Sets author's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Function name: getName
     * @return (String)
     * Gets author's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Function name: setName
     * @param name author's name
     * Sets author's name
     */
    public void setName(String name) {
        this.name = name;
    }

}
