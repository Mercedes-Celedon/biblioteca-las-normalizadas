package com.normalizadas;

import com.normalizadas.dbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*
         * opening a loop (do while)
         * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta
         * se pregunta si quiere salir
         * tiene que enseñar una lista de lo que debe hacer (swtich)
         */
    }

    /* Function show all books - showAll */
    public void showAll() {

    }

    /* Function search books by filters - searchBooks */
    public void searchBooks() {
        /*
         * Add functions:
         * searchByTitle()
         * searchByAuthor()
         * searchByGenre()
         */
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
    public void addBook() {
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
    public void deleteBook(int id) {

        try {
        PreparedStatement stmn = conn.prepareStatement("DELETE FROM books WHERE id = ?");
        stmn.setInt(1, id);

        int row = stmn.executeUpdate();

        if ( row == 0) {
            System.out.println("No se ha podido borrar el registro del libro con id: " + id);
        } else {
            System.out.println("Libro con id " + id + " se ha borrado correctamente.");
        }

        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }

        dbConnection.deleteBook(id);
        System.out.println("Ingresa id del libro que quieres eliminar.");
        int idLibro = scanner.nextInt();
        dbConnection.deleteBook(idLibro);

    }
}
