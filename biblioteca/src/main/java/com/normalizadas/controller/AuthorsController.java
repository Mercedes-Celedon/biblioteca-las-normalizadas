package com.normalizadas.controller;


import com.normalizadas.model.Author;
// import com.normalizadas.model.Author;
import com.normalizadas.model.AuthorDAOInterface;

public class AuthorsController {
    private AuthorDAOInterface authorDAOInterface;

    public AuthorsController(AuthorDAOInterface authorDAOInterface) {
        this.authorDAOInterface = authorDAOInterface;
    }

    public Author findOrCreateAuthor(String name){
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }
        
    }


