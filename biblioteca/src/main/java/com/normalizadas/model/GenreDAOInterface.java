package com.normalizadas.model;

import java.util.List;

public interface GenreDAOInterface {

    List<Genre> getGenres(int id);

    int findOrCreateGenre(String genre);
}