package com.normalizadas.model;

import java.util.List;

public interface AuthorDAOInterface {

    List<Author> getAuthors(int id);
    
    int findOrCreateAuthor(String name);
} 
