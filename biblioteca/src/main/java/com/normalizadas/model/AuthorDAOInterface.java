package com.normalizadas.model;

import java.util.List;

public interface AuthorDAOInterface {
    List<Author> getAuthors(int id);
    Author findOrCreateAuthor(String name);
    void updateAuthor(Author author);
}
