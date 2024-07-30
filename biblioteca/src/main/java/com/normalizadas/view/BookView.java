package com.normalizadas.view;

import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.BooksController;
import com.normalizadas.model.Book;

public class BookView {
    private BooksController booksController;
    
    private BookView (BooksController booksController){
        this.booksController=booksController;
    }

    public void showBooks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el género");
        String genreFilter=scanner.next(); 
        List<Book> books=booksController.getBooksbyGenres(genreFilter);
        for (Book book : books) {
            System.out.println(book.getDescription());
            System.out.println(book.getTitle());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");
            
           }
        scanner.close();
    }
}
