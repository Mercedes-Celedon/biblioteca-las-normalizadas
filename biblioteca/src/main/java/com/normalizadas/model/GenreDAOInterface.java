package com.normalizadas.model;

import java.util.List;

public interface GenreDAOInterface {
    List<Genre> getGenres(int id);
    Genre findOrCreateGenre(String genre);
    String updateGenre(Genre genre);
}