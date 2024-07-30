package com.normalizadas.view;

import java.util.Scanner;

public class BookView {

    private static Scanner scanner;

    public void showMainMenu() {
        scanner = new Scanner(System.in);

        int opc;

        System.out.println("\nBienvenid@ a la biblioteca Divergente.");
        System.out.println("\n¿Qué quieres hacer?");
        System.out.println("1. Ver catálogo entero.");
        System.out.println("2. Buscar un libro.");
        System.out.println("3. Añadir un libro.");
        System.out.println("4. Modificar un libro.\n");
        System.out.println("5. Eliminar un libro.\n");
        System.out.println("6. Salir.\n");

        opc = scanner.nextInt();
        scanner.close();

        boolean exit = false;

        while (exit) {
            switch (opc) {
                case 1:
                    // allBooks = new AllBooks(conn);
                    // allBooks.showAll();
                    break;
                case 2:
                    // searchBooks = new SearchBooks(conn, scanner);
                    // searchBooks.TypeOfFilters();
                    break;
                case 3:
                    // newBook = new NewBook(conn, scanner);
                    // newBook.addBook();
                    break;
                case 4:
                    // Método modificar libro
                    break;
                case 5:
                    // deleteBook();
                    break;
                case 6:
                    exit = true;
                default:
                    System.out.println("No has elegido ninguna opción.");
            }
        }
    }

    public void showSearchMenu() {

        scanner = new Scanner(System.in);

        System.out.println("\n¿Selecciona una opción de búsqueda con el número: ");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por género.");

        int opc = scanner.nextInt();

        scanner.close();

        switch (opc) {
            case 1:
                // searchByTitle();
                break;
            case 2:
                // searchByAuthor();
                break;
            case 3:
                // searchByGenre();
                break;
            default:
                System.out.println("Ninguna opción elegida. Saliendo al menú inicial.");
                showMainMenu();
        }
    }

    public void showDeleteMenu() {

        scanner = new Scanner(System.in);

        int choice;

        System.out.println("¿Quieres borrar este libro?");
        System.out.println("1. Sí.");
        System.out.println("2. No.");

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // deleteBook();
                break;
            case 2:
                System.out.println("El libro no se ha eliminado. Volviendo al menú principal.");
                showMainMenu();
                break;
            default:
                System.out.println("Error. Volviendo al menú principal.");
        }
    }

}
