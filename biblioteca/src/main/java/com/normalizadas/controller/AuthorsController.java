package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Author;
import com.normalizadas.model.AuthorDAOInterface;

public class AuthorsController {
    private AuthorDAOInterface authorDAOInterface;

    /**
     * Function name: AuthorsController
     * @param authorDAOInterface Author Model
     *                           Constructor of the authors controller
     */
    public AuthorsController(AuthorDAOInterface authorDAOInterface) {
        this.authorDAOInterface = authorDAOInterface;
    }

    /**
     * Function name: getAuthorsByBook
     *
     * @param id book id
     * @return   List<Author>
     *           Gets the id of book and returns all of its authors
     */
    public List<Author> getAuthorsByBook(int id) {
        List<Author> authors = authorDAOInterface.getAuthors(id);
        return authors;
    }

    /**
     * Function name: findOrCreateAuthor
     *
     * @param name  Author name
     * @return      Author
     *              Calls AuthorkDAO and returns an Author
     */
    public Author findOrCreateAuthor(String name) {
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }

    /**
     * Function name: updateAuthor
     *
     * @param updatedAuthor The author to update
     * @return (String)     message
     *                      Gets an author by parameter and returns a message with the update result
     */
    public String updateAuthor(Author updatedAuthor) {
        return authorDAOInterface.updateAuthor(updatedAuthor);
    }
}

