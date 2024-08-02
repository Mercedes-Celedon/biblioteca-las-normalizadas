package com.normalizadas.controller;

import java.sql.SQLException;
import java.util.List;

import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAOInterface;

public class BooksController {

    private BookDAOInterface bookDAOInterface;

    /**
     * Function name: BooksController
     *
     * @param bookDAOInterface Book Model
     *                         Constructor of the books controller
     */
    public BooksController(BookDAOInterface bookDAOInterface) {
        this.bookDAOInterface = bookDAOInterface;
    }

    /**
     * Function name: BooksController
     *
     * @return List<Book>
     * Returns all books on the db
     */
    public List<Book> getAllBooks() {
        List<Book> books = bookDAOInterface.getAllBooks();
        return books;
    }

    /**
     * Function name: getBooksbyGenres
     *
     * @param genre genre name
     * @return List<Book>
     * Gets by parameter a genre and returns all books that have said genre
     */
    public List<Book> getBooksbyGenres(String genre) {
        List<Book> books = bookDAOInterface.getBooksbyGenres(genre);
        return books;
    }

    /**
     * Function name: getBooksByAuthors
     *
     * @param author author name
     * @return List<Book>
     * Function that receives the author name as a parameter,
     * and calls the interfaceDAO for generates a list of books
     * according to the author searched in the database
     */
    public List<Book> getBooksbyAuthors(String author) {
        List<Book> books = bookDAOInterface.getBooksbyAuthors(author);
        return books;
    }

    /**
     * Function name: getBookbyTitle
     *
     * @param title book title
     * @return      Book
     *              Calls the class BookDAO and returns a book object with the data from
     *              the database.
     */
    public Book getBookbyTitle(String title) {
        Book book = bookDAOInterface.getBookbyTitle(title);
        return book;
    }

    /**
     * Function name: bookExists
     *
     * @param title book title
     * @return      boolean (if books exists or not)
     *              Calls BookDAO and returns a boolean that indicates if the book exists
     */
    public boolean bookExists(String title) {
        boolean bookExists = bookDAOInterface.bookExists(title);
        return bookExists;
    }

    /**
     * Function name: insertBook
     *
     * @param title         book title
     * @param description   book description
     * @param isbn          book isbn
     * @param stock         quantity in stock
     * @param id_language   book language id
     * @return int          (book's id)
     *                      Calls BookDAO and returns an int
     */
    public int insertBook(String title, String description, String isbn, int stock, int id_language) {
        return bookDAOInterface.insertBook(title, description, isbn, stock, id_language);
    }

    /**
     * Function name:addBookAuthor
     *
     * @param bookId    book id
     * @param authorId  author id
     *                  sends the id of a book and an author to the model that inserts them in the associative table
     */
    public void addBookAuthor(int bookId, int authorId) throws SQLException {
        bookDAOInterface.addBookAuthor(bookId, authorId);
    }

    /**
     * Function name:addBookGenre
     *
     * @param bookId    book id
     * @param genreId   genre id
     *                  sends the id of a book and a genre to the model that inserts them in the associative table
     */
    public void addBookGenre(int bookId, int genreId) throws SQLException {
        bookDAOInterface.addBookGenre(bookId, genreId);
    }

    /**
     * Function name: updateBook
     *
     * @param updatedBook   the book to update
     * @param id_language   book language
     * @return (String)     message
     *                      Gets a book and its language by parameter and returns a message with the update result
     */
    public String updateBook(Book updatedBook, int id_language) {
        return bookDAOInterface.updateBook(updatedBook, id_language);
    }

    /**
     * Function name: deleteBook
     *
     * @param id    book id
     *              Calls the method deleteBook from BookDAOInterface
     */
    public void deleteBook(int id) {
        bookDAOInterface.deleteBook(id);
    }
}
