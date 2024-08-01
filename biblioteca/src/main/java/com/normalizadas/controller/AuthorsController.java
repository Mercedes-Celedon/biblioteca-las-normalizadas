package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Author;
import com.normalizadas.model.AuthorDAOInterface;
import com.normalizadas.model.Book;

public class AuthorsController {
    private AuthorDAOInterface authorDAOInterface;

    public AuthorsController(AuthorDAOInterface authorDAOInterface){
        this.authorDAOInterface = authorDAOInterface;
    }

    //Esta función creo que debería llamarse getAuthorsByBook o algo así porque en verdad retorna los autores de un libro
    public List<Author> getBooksbyAuthors(int id){
        List<Author> authors = authorDAOInterface.getAuthors(id);
        return authors;
    }
    /**
     * Function name findOrCreateAuthor
     * @param name
     * @return Author ()
     */
    public Author findOrCreateAuthor(String name){
        Author author = authorDAOInterface.findOrCreateAuthor(name);
        return author;
    }

    /**
     * TODO
     * @param updatedAuthor
     */
    public void updateAuthor(Author updatedAuthor) {
        authorDAOInterface.updateAuthor(updatedAuthor);
    }
}

