package com.normalizadas.view;

import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.Book;
import com.normalizadas.model.Genre;

public class BookView {
    private BooksController booksController;
    private GenresController genresController;
    
    public BookView (BooksController booksController, GenresController genresController){
        this.booksController=booksController;
        this.genresController=genresController;
    }

    public void showBooks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el género");
        String genre=scanner.next(); 
        List<Book> books=booksController.getBooksbyGenres(genre);
        
        for (Book book : books) {
            List<Genre> genres=genresController.getBooksbyGenres(book.getId());
            for (Genre g : genres) {
                System.out.println(g.getGenre());
            }
            System.out.println(book.getTitle() +" - "+ book.getDescription() +" - "+book.getLanguage());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");
            
           }
        scanner.close();
    }
}
