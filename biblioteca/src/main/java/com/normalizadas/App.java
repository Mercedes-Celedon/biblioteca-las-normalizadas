package com.normalizadas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static dbConnection connection = new dbConnection();
    public static Connection conn = connection.getDbConnection();

    public static void main(String[] args) throws SQLException {

        // Si la variable objeto conex es diferente de nulo
        /*
         * if(conn != null){
         * System.out.println("Conexión a la base de datos exitosa");
         * 
         * Statement stmt = conn.createStatement();
         * ResultSet rs = stmt.executeQuery("SELECT * FROM books");
         * while (rs.next()) {
         * int id = rs.getInt("id");
         * String nombre = rs.getString("title");
         * System.out.println(id + " " + nombre);
         * }
         * stmt.close();
         * rs.close();
         * }
         */

        // para cerrar la conexión a BD

        /*
         * opening a loop (do while)
         * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta
         * se pregunta si quiere salir
         * tiene que enseñar una lista de lo que debe hacer (swtich)
         */
        showAll();
    }

    /* Function show all books - showAll */
    public static void showAll() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT b.id, b.title, b.isbn, b.stock, l.language FROM books as b\n" + //
                "JOIN languages as l ON b.id_language=l.id\n" +
                "ORDER BY b.id ASC");

        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String authors = getInfo(id, "authors");
            String genres = getInfo(id, "genres");
            String isbn = rs.getString("isbn");
            int stock = rs.getInt("stock");
            String language = rs.getString("language");

            System.out.println(title + authors + genres + isbn + stock + language);
        }
        stmt.close();
        rs.close();
    }

    public static String getInfo(int id, String dataType) throws SQLException {
        String data = "";
        String query = "";
        String column = "";
        switch (dataType) {
            case "authors":
                query = "SELECT name from authors\n" + //
                        "JOIN books_authors ON books_authors.id_author = authors.id\n" +
                        "WHERE books_authors.id_book = " + id;
                column = "name";
                break;
            case "genres":
                query = "SELECT genre from genres\n" + //
                        "JOIN books_genres ON books_genres.id_genre = genres.id\n" +
                        "WHERE books_genres.id_book = " + id;
                column = "genre";
                break;
            default:
                break;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            data += rs.getString(column) + ", ";
        }

        stmt.close();
        rs.close();

        return data.substring(0, data.length() - 2);
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

    }
}
