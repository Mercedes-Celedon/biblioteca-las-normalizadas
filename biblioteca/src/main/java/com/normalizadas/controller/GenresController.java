package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Author;
import com.normalizadas.model.Genre;
import com.normalizadas.model.GenreDAOInterface;

public class GenresController {
    private GenreDAOInterface genreDAOInterface;

    public GenresController(GenreDAOInterface genreDAOInterface){
        this.genreDAOInterface = genreDAOInterface;
    }

    //Esta función creo que debería llamarse getGenresByBook o algo así porque en verdad retorna los géneros de un libro
    public List<Genre> getBooksbyGenres(int id){
        List<Genre> genres = genreDAOInterface.getGenres(id);
        return genres;
    }

    public Genre findOrCreateGenre(String name){
        Genre newGenre = genreDAOInterface.findOrCreateGenre(name);
        return newGenre;
    }

    /**
     * TODO
     * @param updatedGenre
     */
    public void updateGenre(Genre updatedGenre) {
        genreDAOInterface.updateGenre(updatedGenre);
    }
}
