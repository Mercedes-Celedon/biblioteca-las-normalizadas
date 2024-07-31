package com.normalizadas.model;

import java.util.List;

public interface BookDAOInterface {
    void deleteBook(int id);    
    List<Book> getBooksbyGenres(String genre);
} 