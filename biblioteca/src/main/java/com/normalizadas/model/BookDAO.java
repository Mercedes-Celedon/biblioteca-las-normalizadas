package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.normalizadas.config.DBManager;

public class BookDAO implements BookDAOInterface {

    private Connection conn;
    private PreparedStatement stmn;

    /**
     * Function name: getAllBooks
     * @return  List<Book>
     *          gets all books in db and returns a list of books
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n" +
                "JOIN languages as l ON b.id_language=l.id\n" +
                "ORDER BY b.id ASC";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setIsbn(result.getString("isbn"));
                book.setStock(result.getInt("stock"));
                book.setLanguage(result.getString("language"));
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return books;
    }

    /**
     * Function name: getBookbyTitle
     *
     * @param title book title
     * @return      book
     *              The function sends a query to the db and returns the data of
     *              the book if the book (title) exists
     */
    public Book getBookbyTitle(String title) {
        Book book = new Book();
        String sql = "SELECT b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n" +
                "JOIN books_authors as ba ON b.id=ba.id_book\n" +
                "JOIN authors as au ON ba.id_author=au.id\n" +
                "JOIN languages as l ON l.id=b.id_language\n" +
                "WHERE b.title = ?";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, title);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setIsbn(result.getString("isbn"));
                book.setStock(result.getInt("stock"));
                book.setLanguage(result.getString("language"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return book;
    }

    /**
     * Function name: getBooksbyGenres
     * @param genre     genre name
     * @return          List<Book>
     *                  Gets by parameter a genre and returns all books that have said genre
     */
    public List<Book> getBooksbyGenres(String genre) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n" +
                "JOIN books_genres as ba ON b.id=ba.id_book\n" +
                "JOIN genres as ge ON ba.id_genre=ge.id\n" +
                "JOIN languages as l ON l.id=b.id_language\n" +
                "WHERE ge.genre = ?";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, genre);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setIsbn(result.getString("isbn"));
                book.setStock(result.getInt("stock"));
                book.setLanguage(result.getString("language"));
                books.add(book);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return books;

    }

    /**
     * Function name: getBooksByAuthors
     * @param author    author name
     * @return          List<Book>
     *                  Function that receives the author name as a parameter,
     *                  and generates a list of books by connecting to the database,
     *                  brings all the data associated with the search for the book by author.
     */
    public List<Book> getBooksbyAuthors(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n" +
                "JOIN books_authors as ba ON b.id=ba.id_book\n" +
                "JOIN authors as au ON ba.id_author=au.id\n" +
                "JOIN languages as l ON l.id=b.id_language\n" +
                "WHERE au.name = ?";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, author);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setIsbn(result.getString("isbn"));
                book.setStock(result.getInt("stock"));
                book.setLanguage(result.getString("language"));
                books.add(book);
                for (Book b : books) {
                    System.out.println(b.getTitle());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return books;

    }

    /**
     * Function name: bookExists
     * 
     * @param title book's title
     * @return      boolean
     *              The function sends a query to the database, and it returns if the
     *              searched book exists or not
     */
    public boolean bookExists(String title) {
        String sql = "SELECT id FROM books WHERE title = ?";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, title);
            ResultSet rs = stmn.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return false;
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
     *         This function inserts a book in the database
     *         then returns the corresponding id
     */
    public int insertBook(String title, String description, String isbn, int stock, int id_language) {
        String sql = "INSERT INTO books (title, description, isbn, stock, id_language) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmn.setString(1, title);
            stmn.setString(2, description);
            stmn.setString(3, isbn);
            stmn.setInt(4, stock);
            stmn.setInt(5, id_language);
            stmn.executeUpdate();

            ResultSet rs = stmn.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return 0;
    }

    /**
     * Function name addBookAuthor
     * 
     * @param bookId    book id
     * @param authorId  author id
     *                  The function inserts a relationship between book and author in the associative table with the
     *                  corresponding ids
     */
    @Override
    public void addBookAuthor(int bookId, int authorId) throws SQLException {

        String sql = "INSERT INTO books_authors (id_book, id_author) VALUES (?, ?)";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setInt(1, bookId);
            stmn.setInt(2, authorId);
            stmn.executeUpdate();
        } finally {
            DBManager.closeConnection();
        }
    }

    /**
     * Function name addBookAuthor
     * 
     * @param bookId    book id
     * @param genreId   genre id
     *                  The function inserts a relationship between book and author in the associative table with the
     *                  corresponding ids
     */
    @Override
    public void addBookGenre(int bookId, int genreId) throws SQLException {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'addBookGenre'");
        String sql = "INSERT INTO books_genres (id_book, id_genre) VALUES (?, ?)";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setInt(1, bookId);
            stmn.setInt(2, genreId);
            stmn.executeUpdate();
        } finally {
            DBManager.closeConnection();
        }
    }

    /**
     * Function name: updateGenre
     * @param book          a book
     * @param id_language   the language id of the book
     * @return String -     a success or error message
     *                      Gets by parameter a book that has already the data updated and its language and executes the query that updates
     *                      the bd entry.
     */
    @Override
    public String updateBook(Book book, int id_language) {
        String message = "";
        String sql = "UPDATE books SET title = ?, description = ?, isbn = ?, " +
                "stock = ?, id_language = ? WHERE id = ?";
        try {
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, book.getTitle());
            stmn.setString(2, book.getDescription());
            stmn.setString(3, book.getIsbn());
            stmn.setInt(4, book.getStock());
            stmn.setInt(5, id_language);
            stmn.setInt(6, book.getId());
            int execution = stmn.executeUpdate();
            message = (execution == 0) ? "\nNo se ha podido modificar el libro"
                    : "\nEl libro se ha actualizado correctamente";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return message;
    }

    /**
     * Function name: deleteBook
     *
     * @param id book id
     *           This function deletes a book first from the table books_authors,
     *           then from the table books_genres and finally from the main table
     *           books
     */
    public void deleteBook(int id) {

        try {
            conn = DBManager.getDbConnection();
            String deleteBooksAuthors = "DELETE FROM books_authors WHERE id_book = ?";
            try (PreparedStatement stmn = conn.prepareStatement(deleteBooksAuthors)) {
                stmn.setInt(1, id);
                stmn.executeUpdate();
            }

            String deleteBooksGenres = "DELETE FROM books_genres WHERE id_book = ?";
            try (PreparedStatement stmn = conn.prepareStatement(deleteBooksGenres)) {
                stmn.setInt(1, id);
                stmn.executeUpdate();
            }

            String deleteBooks = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement stmn = conn.prepareStatement(deleteBooks)) {
                stmn.setInt(1, id);
                int row = stmn.executeUpdate();
                if (row == 0) {
                    System.out.println("No se ha podido borrar el registro.");
                } else {
                    System.out.println("Se ha borrado correctamente.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Conexión fallida. " + e.getMessage());

        } finally {
            DBManager.closeConnection();
        }
    }
}
