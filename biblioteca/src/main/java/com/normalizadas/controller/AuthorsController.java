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
     * @return Author (author's id)
     *                 Calls AuthorkDAO and returns an Author  
     */
    public Author findOrCreateAuthor(String name){
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }

    /**
     * Function name: updateAuthor
     * @param updatedAuthor The author to update
     * @return (String) message
     * Gets an author by parameter and returns a message with the update result
     */
    public String updateAuthor(Author updatedAuthor) {
        return authorDAOInterface.updateAuthor(updatedAuthor);
    }
}

