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
        
        System.out.println("\n¿Selecciona una opción de búsqueda con el número: ");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por género.");

        int opc = scanner.nextInt();

        switch (opc){
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByAuthor();
                break;
            case 3:
                searchByGenre();
                break;
            default:
                System.out.println("Ninguna opción elegida. Saliendo al menú inicial.");
        }
            

    }

    /* Function search a book by title - searchByTitle */
    public void searchByTitle() {
        System.out.print("Introduce el nombre del Título: ");
        scanner.nextLine();
        String titleFilter = scanner.nextLine();
        System.out.println(titleFilter);
        try {
            Statement stmn = conn.createStatement();
            ResultSet result = stmn.executeQuery("SELECT a.name, b.title, b.description, b.isbn, b.stock, l.language FROM books as b\n" + //
            "JOIN books_authors as ba ON b.id=ba.id_book\n" + //
            "JOIN authors as a ON ba.id_author=a.id\n" + //
            "JOIN languages as l ON l.id=b.id_language\n" + //
            "WHERE b.title = '"+ titleFilter + "';");

            if (!result.isBeforeFirst()) {
                System.out.println("No se encontró el libro: " + titleFilter);
            } else {
                System.out.println(" Título      |      Autor       |      Descripción       |      ISBN       |      Stock       |      Idioma ");
                System.out.println("_______________________________________________________________________________________________");
                while (result.next()) {
                    String author = result.getString("name");
                    String title = result.getString("title");
                    String description=result.getString("description");
                    String isbn = result.getString("isbn");
                    int stock = result.getInt("stock");
                    String language = result.getString("language");
                    
                    System.out.println( title + " | " + author + " | "+ description + " | " + isbn + " | " + stock + " | " + language +
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            stmn.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Function search a book by author - searchByAuthor */
    public void searchByAuthor() {
        System.out.print("Introduce el nombre del Autor: ");
        scanner.nextLine();
        String authorFilter = scanner.nextLine();
        System.out.println(authorFilter);
        try {
            Statement stmn = conn.createStatement();
            ResultSet result = stmn.executeQuery("SELECT a.name, b.title, b.description, b.isbn, b.stock, l.language FROM books as b\n" + //
            "JOIN books_authors as ba ON b.id=ba.id_book\n" + //
            "JOIN authors as a ON ba.id_author=a.id\n" + //
            "JOIN languages as l ON l.id=b.id_language\n" + //
            "WHERE a.name = '"+ authorFilter + "';");

            if (!result.isBeforeFirst()) { // Verifica si el ResultSet está vacío
                System.out.println("No se encontró el autor: " + authorFilter);
            } else {
                System.out.println(" Autor      |      Título       |      Descripción       |      ISBN       |      Stock       |      Idioma ");
                System.out.println("_______________________________________________________________________________________________");
                while (result.next()) {
                    String author = result.getString("name");
                    String title = result.getString("title");
                    String description=result.getString("description");
                    String isbn = result.getString("isbn");
                    int stock = result.getInt("stock");
                    String language = result.getString("language");
                    
                    System.out.println( author + " | " + title + " | "+ description + " | " + isbn + " | " + stock + " | " + language +
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
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
                System.out.println(" Género       |      Título       |      ISBN       |      Stock       |      Idioma ");
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