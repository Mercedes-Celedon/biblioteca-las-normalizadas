package com.normalizadas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import com.normalizadas.crud.AllBooks;
import com.normalizadas.crud.NewBook;
import com.normalizadas.crud.SearchBooks;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    private static Connection conn;

    public static void main(String[] args) throws SQLException {

        conn = new dbConnection().getDbConnection();
        AllBooks allBooks;
        NewBook newBook;
        SearchBooks searchBooks;
    
        int opc;
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
                newBook = new NewBook(conn, scanner);
                newBook.addBook();

            } else if (opc == 4) {
                break;
            }

        } while (opc != 4);
    }

}
