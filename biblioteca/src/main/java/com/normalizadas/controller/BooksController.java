package com.normalizadas.controller;

import java.util.List;

import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAOInterface;

public class BooksController {

    private BookDAOInterface bookDAOInterface;

    public BooksController(BookDAOInterface bookDAOInterface){
        this.bookDAOInterface = bookDAOInterface;
    }

    public List<Book> getBooksbyGenres(String genre){
        List<Book> books = bookDAOInterface.getBooksbyGenres(genre);
        return books;
    }
}
