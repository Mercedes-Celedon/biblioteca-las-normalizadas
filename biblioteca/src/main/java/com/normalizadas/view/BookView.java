package com.normalizadas.view;

import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.BooksController;
import com.normalizadas.model.Book;

public class BookView {
    private BooksController booksController;
    
    public BookView (BooksController booksController){
        this.booksController=booksController;
    }

    public void showBooks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el género");
        String genre=scanner.next(); 
        List<Book> books=booksController.getBooksbyGenres(genre);
        for (Book book : books) {
            System.out.println(book.getTitle());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");
            
           }
        scanner.close();
    }
}
