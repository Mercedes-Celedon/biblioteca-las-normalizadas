package com.normalizadas.controller;

import java.sql.SQLException;
import java.util.List;

import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAOInterface;

public class BooksController {

    private BookDAOInterface bookDAOInterface;

    /**
     * TODO
     * @param bookDAOInterface
     * Constructor
     */
    public BooksController(BookDAOInterface bookDAOInterface){
        this.bookDAOInterface = bookDAOInterface;
    }

    /**
     * Function name: getAllBooks
     * @return List<Book>
     */
    public List<Book> getAllBooks(){
        List<Book> books= bookDAOInterface.getAllBooks();
        return books;
    }

    /**
     * TODO
     * @param genre
     * @return
     */
    public List<Book> getBooksbyGenres(String genre){
        List<Book> books = bookDAOInterface.getBooksbyGenres(genre);
        return books;
    }

    /**
     * TODO
     * @param author
     * @return
     */
    public List<Book> getBooksbyAuthors(String author){
        List<Book> books = bookDAOInterface.getBooksbyAuthors(author);
        return books;
    }

    /**
     * TODO
     * @param title
     * @return
     */
    public Book getBookbyTitle(String title){
        Book book = bookDAOInterface.getBookbyTitle(title);
        return book;
    }

    /**
     * Function name: bookExists
     * @param title
     * @return boolean 
     *  TODO
     */
    public boolean bookExists(String title) {
        boolean bookExists = bookDAOInterface.bookExists(title);
        return bookExists;
    }

    /**
     * Function name: insertBook
     * @param title
     * @param description
     * @param isbn
     * @param stock
     * @param id_language
     * @return
     * TODO
     */
    public int insertBook(String title, String description, String isbn, int stock, int id_language){
        return bookDAOInterface.insertBook(title, description, isbn, stock, id_language);
    }

    /**
     * Function name:addBookAuthor
     * @param bookId
     * @param authorId
     * @throws SQLException
     * TODO
     */
    public void addBookAuthor(int bookId, int authorId) throws SQLException{
        bookDAOInterface.addBookAuthor(bookId, authorId);
    }
    /**
     * Function name:addBookGenre
     * @param bookId
     * @param genreId
     * @throws SQLException
     * TODO
     */
    public void addBookGenre(int bookId, int genreId) throws SQLException{
        bookDAOInterface.addBookGenre(bookId, genreId);
    }

    /**
     * TODO
     * @param updatedBook
     * @param id_language
     * @return
     */
    public String updateBook(Book updatedBook, int id_language) {
        return bookDAOInterface.updateBook(updatedBook, id_language);
    }


    /**
     * TODO
     * @param id
     */
    public void deleteBook(int id) {
        bookDAOInterface.deleteBook(id);
    }
}
