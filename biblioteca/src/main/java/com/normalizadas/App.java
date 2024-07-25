package com.normalizadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static dbConnection connection = new dbConnection();
    public static Connection conn = connection.getDbConnection();

    public static void main(String[] args) throws SQLException {

        // Si la variable objeto conex es diferente de nulo

        // para cerrar la conexión a BD

        /*
         * opening a loop (do while)
         * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta
         * se pregunta si quiere salir
         * tiene que enseñar una lista de lo que debe hacer (swtich)
         */

        int opc;

        do {
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Ver catálogo entero.");
            System.out.println("2. Buscar un libro.");
            System.out.println("3. Añadir un libro.");
            System.out.println("4. Salir.\n");

            opc = scanner.nextInt();

            if (opc == 1) {
                showAll();

            } else if (opc == 2) {
                deleteBook(23);

            } else if (opc == 3) {
                addBook();

            } else if (opc == 4) {
                connection.closeConnection(conn);
            }

        } while (opc != 4);
    }

    /* Function show all books - showAll */
    public static void showAll() {

    }

    /* Function search books by filters - searchBooks */
    public static void searchBooks() {
        /*
         * Add functions:
         * searchByTitle()
         * searchByAuthor()
         * searchByGenre()
         */
        // System.out.println("A buscar");
    }

    /* Function search a book by title - searchByTitle */
    public void searchByTitle() {

    }

    /* Function search a book by author - searchByAuthor */
    public void searchByAuthor() {

    }

    /* Function search a book by genre - searchByGenre */
    public void searchByGenre() {

    }

    /* Function add a book - addBook */
    public static void addBook() {
        /*
         * Add title
         * addAuthor(bookId)
         * addGenre(bookId)
         */
    }

    /**
     * Function add an author - addAuthor
     * 
     * @param id (int)
     */
    public void addAuthor(int id) {

    }

    /**
     * Function add a genre - addGenre
     * 
     * @param id (int)
     */
    public void addGenre(int id) {

    }

    /**
     * Function edit a book - editBook
     * 
     * @param id (int)
     */
    public void editBook(int id) {

    }

    /**
     * Function delete a book - deleteBook
     * 
     * @param id
     */
    public static void deleteBook(int id) {

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
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }

        } 
    }

    // public static void deleteBook() {
    //     // System.out.println("¿Quieres eliminar este libro?");
    //     // int idLibro = scanner.nextInt();
    //     logicDeleteBook();
    // }
}
