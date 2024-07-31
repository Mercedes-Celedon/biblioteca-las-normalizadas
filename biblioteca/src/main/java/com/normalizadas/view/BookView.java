package com.normalizadas.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.Book;
import com.normalizadas.model.Genre;

public class BookView {

    private static Scanner scanner = new Scanner(System.in);
    private  String askGenreFilter;
    private BooksController booksController;
    private GenresController genresController;
    
    public BookView (BooksController booksController, GenresController genresController){
        this.booksController=booksController;
        this.genresController=genresController;
    }

    public void showMainMenu() {
        //scanner = new Scanner(System.in);

        int opc;

        
        //scanner.close()();

        boolean exit = false;

        while (!exit) {
            System.out.println("\nBienvenid@ a la biblioteca Divergente.");
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Ver catálogo entero.");
            System.out.println("2. Buscar un libro.");
            System.out.println("3. Añadir un libro.");
            System.out.println("4. Modificar un libro.");
            System.out.println("5. Eliminar un libro.");
            System.out.println("6. Salir.");

            opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    break;
                case 2:
                    showSearchMenu();
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
        System.out.println("\n¿Selecciona una opción de búsqueda con el número: ");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por género.");
        System.out.println("4. Salir de búsqueda");

        int opcFilter = scanner.nextInt();

        switch (opcFilter) {
            case 1:
                // searchByTitle();
                break;
            case 2:
                // searchByAuthor();
                break;
            case 3:
                askGenreBook();
                break;
            case 4:
                showMainMenu();
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

    public void showAddBook() {
        scanner = new Scanner(System.in);

        scanner.nextLine();
        System.out.println("Indica el título:");
        String title = scanner.nextLine();
        // if (bookExists(title)) {
        //     System.out.print("Este título ya está registrado");
        //     return;
        // }
        System.out.print("Añade una descripción (de menos de 200 caracteres):");
        String description = scanner.nextLine();
        System.out.print("Indica el ISBN:");
        String isbn = scanner.nextLine();
        System.out.print("Indica el stock:");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indica el idioma (escribe solo el número): "+
                                                                "\n\t1. Español "+
                                                                "\n\t2. Inglés "+
                                                                "\n\t3. Francés "+
                                                                "\n\t4. Catalán.");
        int id_language = scanner.nextInt();
        scanner.nextLine();

        // int bookId = insertBook(title, description, isbn, stock, id_language);

        System.out.print("Indica el autor o autores (en este caso separados por comas): ");
        String[] authors = scanner.nextLine().split(",");

        // for (String author : authors) {
        //     int id_author = findOrCreateAuthor(author.trim());
        //     addBookAuthor(bookId, id_author);
        // }

        System.out.print("Indica el género o géneros (en este caso separados por comas): ");
        String[] genres = scanner.nextLine().split(",");
        
        // for (String genre : genres) {
        //     int id_genre = findOrCreateGenre(genre.trim());
        //     addBookGenre(bookId, id_genre);
        // }
        //scanner.close()();
        System.out.println("Libro añadido con éxito");
    }


    public void askGenreBook(){
        scanner.nextLine();
        System.out.println("Escribe el género");
        askGenreFilter=scanner.nextLine(); 
        List<Book> books=booksController.getBooksbyGenres(askGenreFilter);
        printBook(books, true);
        
    }

    public void printBook(List<Book> books, boolean printDescription){
        if (printDescription){
            System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s | %-25s |\n","Titulo","Autor","Genero","ISBN","Stock", "Idioma", "Descripción" );
        System.out.println("=".repeat(165));
        for (Book book : books) {
            List<Genre> genres=genresController.getBooksbyGenres(book.getId());
            List<String> data = new ArrayList<>();
            for (Genre g : genres) {
                //System.out.println(g.getGenre());
                data.add(g.getGenre());
            }
            String genresString = String.join(", ", data);
            String title = book.getTitle();
            String description = book.getDescription();
            String language = book.getLanguage();
            String isbn = book.getIsbn();
            int stock = book.getStock();
            System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s |", title, "author", genresString, isbn, stock, language);
            printFormatDescription(description, 25);
            System.out.print("\n");
            System.out.println("-".repeat(165));
           }
        }
        
    }

    private void printFormatDescription(String description, int lineWidth) {
        int length = description.length();
        boolean firstLine = true;
        for (int i = 0; i < length; i += lineWidth) {
            if (!firstLine) {
                System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s | ", "", "", "", "","","");
            }
    
            if (i + lineWidth < length) {
                System.out.printf("%-25s", description.substring(i, i + lineWidth));
            } else {
                System.out.printf("%-25s", description.substring(i));
            }
    
            firstLine = false;
    
            // Check if it's the last part of the description to avoid extra lines
            if (i + lineWidth < length - 1) {
                System.out.printf("\n");
            }
        }
    }
}
