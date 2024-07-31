package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Genre;
import com.normalizadas.model.GenreDAOInterface;

public class GenresController {
    private GenreDAOInterface genreDAOInterface;

    public GenresController(GenreDAOInterface genreDAOInterface){
        this.genreDAOInterface = genreDAOInterface;
    }

    public List<Genre> getBooksbyGenres(int id){
        List<Genre> genres = genreDAOInterface.getGenres(id);
        return genres;
    }

    public Genre findOrCreateGenre(String name){
        Genre newGenre = genreDAOInterface.findOrCreateGenre(name);
        return newGenre;
    }
}
