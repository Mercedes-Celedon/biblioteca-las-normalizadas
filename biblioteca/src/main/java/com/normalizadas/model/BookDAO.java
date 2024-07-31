package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.normalizadas.config.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

import com.normalizadas.config.DBManager;

public class BookDAO implements BookDAOInterface {

    private Connection conn;


    public void deleteBook(int id) {

        try {

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
            System.out.println("Conexión fallida");
        } finally {
            DBManager.closeConnection();
        }
    }

    private PreparedStatement stmn;

    public List<Book> getBooksbyGenres(String genre){
        List<Book> books = new ArrayList<>();
        String sql= "SELECT ge.genre, b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n"+
                "JOIN books_genres as ba ON b.id=ba.id_book\n"+
                "JOIN genres as ge ON ba.id_genre=ge.id\n"+
                "JOIN languages as l ON l.id=b.id_language\n"+                
                "WHERE ge.genre = ?";
        try{
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

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBManager.closeConnection();
        }
        return books;
   
    }
}
