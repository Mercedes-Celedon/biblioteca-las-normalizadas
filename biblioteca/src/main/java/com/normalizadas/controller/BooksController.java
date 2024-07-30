package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAOInterface;

public class BooksController {

    private BookDAOInterface bookDAOInterface;

    public BooksController(BookDAOInterface BookDAOInterface){
        this.bookDAOInterface = bookDAOInterface;
    }

    public List<Book> getBooksbyGenres(String genreFilter){
        List<Book> books = bookDAOInterface.getBooksbyGenres(genreFilter);
        return books;
    }
}
