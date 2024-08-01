package com.normalizadas.controller;

import java.sql.SQLException;
import java.util.List;

import com.normalizadas.model.AuthorDAOInterface;
import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAOInterface;
import com.normalizadas.model.GenreDAOInterface;

public class BooksController {

    private BookDAOInterface bookDAOInterface;
    private AuthorDAOInterface authorDAOInterface;
    private GenreDAOInterface genreDAOInterface;

    public BooksController(BookDAOInterface bookDAOInterface){
        this.bookDAOInterface = bookDAOInterface;
        // this.authorDAOInterface = authorDAOInterface;
        // this.genreDAOInterface = genreDAOInterface;
    }
    public List<Book> getAllBooks(){
        List<Book> books= bookDAOInterface.getAllBooks();
        return books;
    }

    public List<Book> getBooksbyGenres(String genre){
        List<Book> books = bookDAOInterface.getBooksbyGenres(genre);
        return books;
    }

    public List<Book> getBooksbyAuthors(String author){
        List<Book> books = bookDAOInterface.getBooksbyAuthors(author);
        return books;
    }

    public List<Book> getBooksbyTitle(String title){
        List<Book> books = bookDAOInterface.getBooksbyTitles(title);
        return books;
    }

    public boolean bookExists(String title) {
        boolean bookExists = bookDAOInterface.bookExists(title);
        return bookExists;
    }

    public int insertBook(String title, String description, String isbn, int stock, int id_language){
        return bookDAOInterface.insertBook(title, description, isbn, stock, id_language);
    }
    public void addBookAuthor(int bookId, int authorId) throws SQLException{
        bookDAOInterface.addBookAuthor(bookId, authorId);
    }
    
    public void addBookGenre(int bookId, int genreId) throws SQLException{
        bookDAOInterface.addBookGenre(bookId, genreId);
    }

    public void updateBook(Book updatedBook, int id_language) {
        bookDAOInterface.updateBook(updatedBook, id_language);;
    }
    
}
