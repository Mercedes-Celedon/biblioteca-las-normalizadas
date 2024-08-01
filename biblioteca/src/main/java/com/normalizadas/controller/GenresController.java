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
     */
    public Genre findOrCreateGenre(String name){
        Genre newGenre = genreDAOInterface.findOrCreateGenre(name);
        return newGenre;
    }

    /**
     * TODO
     * @param updatedGenre
     */
    public String updateGenre(Genre updatedGenre) {
        return genreDAOInterface.updateGenre(updatedGenre);
    }
}
