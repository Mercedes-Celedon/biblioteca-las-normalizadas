package com.normalizadas.crud;
import java.util.Scanner;

import com.normalizadas.config.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DeleteBooks {

    private Connection conn;
    private Scanner scanner;
    public DeleteBooks(Connection conn, Scanner scanner){
        this.conn = conn;
        this.scanner = scanner;
    }

    public void delete(int id) throws SQLException {

        int borrar;

        System.out.println("¿Quieres borrar este libro?");
        System.out.println("1. Sí.");
        System.out.println("2. No.");

        borrar = scanner.nextInt();

        if (borrar == 1) {
            
        try {
            
            conn.setAutoCommit(false);

            String deleteBooksAuthors = "DELETE FROM books_authors WHERE id_book = ?";
            try (PreparedStatement pst = conn.prepareStatement(deleteBooksAuthors)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            String deleteBooksGenres = "DELETE FROM books_genres WHERE id_book = ?";
            try (PreparedStatement pst = conn.prepareStatement(deleteBooksGenres)) {
                pst.setInt(1, id);
                pst.executeUpdate();
            }

            String deleteBooks = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement pst = conn.prepareStatement(deleteBooks)) {
                pst.setInt(1, id);
                int row = pst.executeUpdate();
                if (row == 0) {
                    System.out.println("No se ha podido borrar el registro.");
                } else {
                    System.out.println("Se ha borrado correctamente.");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // System.out.println("Error 1.");
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // System.out.println("Error 2.");
            } 
        }

        }


    }




}

