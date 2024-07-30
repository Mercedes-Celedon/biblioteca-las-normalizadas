package com.normalizadas;

import com.normalizadas.controller.BooksController;
import com.normalizadas.model.BookDAO;
import com.normalizadas.model.BookDAOInterface;
import com.normalizadas.view.BookView;

public class App {

    public static void main (String [] args){
        BookDAOInterface bookDao = new BookDAO();
        BooksController bookController = new BooksController(bookDao);
        BookView bookView = new BookView(bookController);
        bookView.showBooks();
    }
 }




