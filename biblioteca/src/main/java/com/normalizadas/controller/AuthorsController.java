package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Author;
import com.normalizadas.model.AuthorDAOInterface;

public class AuthorsController {
    private AuthorDAOInterface authorDAOInterface;

    /**
     * TODO
     * @param authorDAOInterface
     */
    public AuthorsController(AuthorDAOInterface authorDAOInterface){
        this.authorDAOInterface = authorDAOInterface;
    }

    /**
     * TODO
     * @param id
     * @return
     */
    public List<Author> getAuthorsByBook(int id){
        List<Author> authors = authorDAOInterface.getAuthors(id);
        return authors;
    }
    /**
     * Function name findOrCreateAuthor
     * @param name
     * @return Author ()
     * TODO
     */
    public Author findOrCreateAuthor(String name){
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }

    /**
     * TODO
     * @param updatedAuthor
     */
    public String updateAuthor(Author updatedAuthor) {
        return authorDAOInterface.updateAuthor(updatedAuthor);
    }
}

