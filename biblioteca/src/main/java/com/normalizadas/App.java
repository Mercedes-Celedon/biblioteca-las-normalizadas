package com.normalizadas;

import com.normalizadas.dbConnection;

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

        int opc;

        do {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Ver catálogo entero.");
            System.out.println("2. Buscar un libro.");
            System.out.println("3. Añadir un libro.");
            System.out.println("4. Salir.");

            opc = scanner.nextInt();

            if (opc == 1) {
                showAll();

            } else if (opc == 2) {
                searchBooks();

            } else if (opc == 3) {
                addBook();
                
            } else if (opc == 4) {
                //dbConnection.connectionClose();
            }

        } while (opc != 4);
    }

    /* Function show all books - showAll */
    public static void showAll() {
        //System.out.println("Aquí tienes");
    }

    /* Function search books by filters - searchBooks */
    public static void searchBooks() {
        /*
         * Add functions:
         * searchByTitle()
         * searchByAuthor()
         * searchByGenre()
         */
        //System.out.println("A buscar");
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
    public void deleteBook(int id) {

    }
}
