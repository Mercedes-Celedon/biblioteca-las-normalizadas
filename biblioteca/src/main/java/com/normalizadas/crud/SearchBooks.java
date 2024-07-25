package com.normalizadas.crud;

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
            Statement stmn = conn.createStatement();
            ResultSet result = stmn.executeQuery(
                "SELECT ge.genre, b.title, b.isbn, b.stock, l.language FROM books as b\n"+
                "JOIN books_genres as ba ON b.id=ba.id_book\n"+
                "JOIN genres as ge ON ba.id_genre=ge.id\n"+
                "JOIN languages as l ON l.id=b.id_language\n"+
                "WHERE ge.genre = '" + genreFilter + "';");

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
                    String language = result.getString("language");
                    
                    System.out.println(genre + " | " + title + " | " + isbn + " | " + stock + " | " + language +
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }

            result.close();
            stmn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}