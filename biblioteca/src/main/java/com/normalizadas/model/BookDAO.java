package com.normalizadas.model;

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

}
