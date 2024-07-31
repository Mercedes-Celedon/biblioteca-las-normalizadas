package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Author;
import com.normalizadas.model.AuthorDAOInterface;

public class AuthorsController {
    private AuthorDAOInterface authorDAOInterface;

    public AuthorsController(AuthorDAOInterface authorDAOInterface){
        this.authorDAOInterface = authorDAOInterface;
    }

    public List<Author> getBooksbyAuthors(int id){
        List<Author> authors = authorDAOInterface.getAuthors(id);
        return authors;
    }

    public Author findOrCreateAuthor(String name){
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }
}

