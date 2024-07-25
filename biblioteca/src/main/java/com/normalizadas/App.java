package com.normalizadas;

import java.nio.channels.AcceptPendingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.normalizadas.crud.AllBooks;
import com.normalizadas.crud.SearchBooks;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    private static Connection conn;// Inicializando atributo(property or class member) de la clase app
    // con un constructor se tiene organizada y separada la lógica de inicialización
    // y ejecución. da claridad y organización del código.

    public App() {// implementando un constructor para la clase app (inicialización de recurso que
                  // podría usar en esa clase)
        App.conn = new dbConnection().getDbConnection();
    }

    public static void main(String[] args) throws SQLException {
        App myApp = new App();// creando una nueva variable con la instancia de la clase app que se llama
                              // myApp

        /*
         * opening a loop (do while)
         * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta
         * se pregunta si quiere salir
         * tiene que enseñar una lista de lo que debe hacer (swtich)
         */
        AllBooks allBooks;
        int opc;

        SearchBooks searchBooks;
    

        do {
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Ver catálogo entero.");
            System.out.println("2. Buscar un libro.");
            System.out.println("3. Añadir un libro.");
            System.out.println("4. Salir.\n");

            opc = scanner.nextInt();

            if (opc == 1) {
                allBooks = new AllBooks(conn);
                allBooks.showAll();

            } else if (opc == 2) {
                searchBooks = new SearchBooks(conn, scanner);
                searchBooks.TypeOfFilters();

            } else if (opc == 3) {

            } else if (opc == 4) {
                // dbConnection.closeConnection(conn);
                break;
            }

        } while (opc != 4);
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
}
