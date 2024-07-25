package com.normalizadas.crud;

import com.normalizadas.App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* Function search books by filters - searchBooks */
public class SearchBooks {

    private Scanner scanner;
    private Connection conn;
    public SearchBooks(Connection conn, Scanner scanner){
        this.conn = conn;
        this.scanner =scanner;
    }

    public void TypeOfFilters() throws SQLException{
        searchByGenre();
        /*Aquí se debe hacer switch y do while para mostrar opciones*/
    }

    /* Function search a book by title - searchByTitle */
    public void searchByTitle() {

    }

    /* Function search a book by author - searchByAuthor */
    public void searchByAuthor() {
        System.out.print("Introduce el nombre del Autor: ");
        scanner.nextLine();
        String authorFilter = scanner.nextLine();
        System.out.println(authorFilter);
        try {
            Statement stmn = conn.createStatement();
            ResultSet result = stmn.executeQuery("SELECT a.name, b.title FROM authors as a\n" + //
                    "\tJOIN books_authors as ba ON a.id=ba.id_author\n" + //
                    "\tJOIN books as b ON ba.id_book=b.id\n" + //
                    "\tWHERE a.name='" + authorFilter + "';");

            System.out.println(" Autor       |      Libro ");
            while (result.next()) {
                String book = result.getString("title");

                System.out.println(authorFilter + " | " + book);
            }
            stmn.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Function search a book by genre - searchByGenre */
    public void searchByGenre() throws SQLException {
        System.out.print("Introduce el nombre del Género: ");
        scanner.nextLine();
        String genreFilter = scanner.nextLine();

        try {
            Statement stmn = App.conn.createStatement();
            ResultSet result = stmn.executeQuery(
                    "SELECT a.genre, b.title, b.isbn, b.stock, b.id_language " +
                    "FROM genres as a " +
                    "JOIN books_genres as ba ON a.id = ba.id_genre " +
                    "JOIN books as b ON ba.id_book = b.id " +
                    "WHERE a.genre = '" + genreFilter + "';");

            if (!result.isBeforeFirst()) { // Verifica si el ResultSet está vacío
                System.out.println("No se encontró el género: " + genreFilter);
            } else {
                System.out.println(" Género       |      Libro       |      ISBN       |      Stock       |      Lenguaje ");
                System.out.println("_______________________________________________________________________________________________");
                while (result.next()) {
                    String genre = result.getString("genre");
                    String title = result.getString("title");
                    String isbn = result.getString("isbn");
                    int stock = result.getInt("stock");
                    int languageId = result.getInt("id_language");
                    
                    System.out.printf("%s | %s | %s | %d | %d%n", genre, title, isbn, stock, languageId);
                    System.out.println("_______________________________________________________________________________________________");
                }
            }

            result.close();
            stmn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}