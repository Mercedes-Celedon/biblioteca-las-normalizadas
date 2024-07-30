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

    public List<Book> getBooksbyGenres(String genre) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT ge.genre, b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n" +
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

    @Override
    public void addBookAuthor(int bookId, int authorId) throws SQLException {
        // // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'addBookAuthor'");
        String sql = "INSERT INTO books_authors (id_book, id_author) VALUES (?, ?)";
        try{
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setInt(1, bookId);
            stmn.setInt(2, authorId);
            stmn.executeUpdate();
        }finally{
            DBManager.closeConnection();
        }
    }

    @Override
    public void addBookGenre(int bookId, int genreId) throws SQLException {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'addBookGenre'");
        String sql = "INSERT INTO books_genres (id_book, genre) VALUES (?, ?)";
        try{
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setInt(1, bookId);
            stmn.setInt(2, genreId);
            stmn.executeUpdate();
        }finally{
            DBManager.closeConnection();
        }
    }

    
}
