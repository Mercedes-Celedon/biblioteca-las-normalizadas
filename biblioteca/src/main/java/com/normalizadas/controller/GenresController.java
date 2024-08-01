package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Genre;
import com.normalizadas.model.GenreDAOInterface;

public class GenresController {
    private GenreDAOInterface genreDAOInterface;

    /**
     * TODO
     * @param genreDAOInterface
     * constructor
     */
    public GenresController(GenreDAOInterface genreDAOInterface){
        this.genreDAOInterface = genreDAOInterface;
    }

    /**
     * TODO
     * @param id
     * @return
     */
    public List<Genre> getGenresByBook(int id){
        List<Genre> genres = genreDAOInterface.getGenres(id);
        return genres;
    }

    /**
     * Function name findOrCreateGenre
     * @param name
     * @return Genre (genre's id)
     *                 Calls GenreDAO and returns a Genre  
     */
    public Genre findOrCreateGenre(String name){
        Genre newGenre = genreDAOInterface.findOrCreateGenre(name);
        return newGenre;
    }

    /**
     * Function name: updateGenre
     * @param updatedGenre The genre to update
     * @return (String) message
     * Gets a genre by parameter and returns a message with the update result
     */
    public String updateGenre(Genre updatedGenre) {
        return genreDAOInterface.updateGenre(updatedGenre);
    }
}
