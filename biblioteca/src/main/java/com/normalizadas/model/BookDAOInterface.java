package com.normalizadas.model;

import java.sql.SQLException;
import java.util.List;

public interface BookDAOInterface {
    List<Book> getAllBooks();
    List<Book> getBooksbyGenres(String genre);
    List<Book> getBooksbyAuthors(String author);
    Book getBookbyTitle(String title);
    boolean bookExists(String title);
    int insertBook(String title, String description, String isbn, int stock, int id_language);
    void addBookAuthor(int bookId, int authorId) throws SQLException;
    void addBookGenre(int bookId, int genreId) throws SQLException;
    String updateBook(Book book, int id_language);
    void deleteBook(int id);
}