package com.normalizadas;

import com.normalizadas.controller.AuthorsController;
import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.AuthorDAO;
import com.normalizadas.model.AuthorDAOInterface;
import com.normalizadas.model.BookDAO;
import com.normalizadas.model.BookDAOInterface;
import com.normalizadas.model.GenreDAO;
import com.normalizadas.model.GenreDAOInterface;
import com.normalizadas.view.BookView;

public class App {

    public static void main (String [] args){
        BookDAOInterface bookDao = new BookDAO();
        BooksController bookController = new BooksController(bookDao);
        GenreDAOInterface genreDao= new GenreDAO();
        GenresController genresController= new GenresController(genreDao);
        AuthorDAOInterface authorDAO = new AuthorDAO();
        AuthorsController authorsController = new AuthorsController(authorDAO);
        BookView bookView = new BookView(bookController, genresController, authorsController);
        bookView.searchByAuthor();
    }
 }




