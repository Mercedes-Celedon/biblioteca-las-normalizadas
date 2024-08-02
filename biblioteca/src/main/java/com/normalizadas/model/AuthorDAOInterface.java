package com.normalizadas.model;

import java.util.List;

public interface AuthorDAOInterface {
    List<Author> getAuthors(int id);
    Author findOrCreateAuthor(String name);
    String updateAuthor(Author author);
}
