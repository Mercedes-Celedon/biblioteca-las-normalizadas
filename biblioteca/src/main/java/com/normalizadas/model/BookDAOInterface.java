package com.normalizadas.model;

import java.util.List;

public interface BookDAOInterface {
    List<Book> getBooksbyGenres(String genre);
} 