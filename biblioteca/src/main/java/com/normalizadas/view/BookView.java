package com.normalizadas.view;

import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.Book;
import com.normalizadas.model.Genre;

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
        scanner.close();
        System.out.println("Libro añadido con éxito");
    }
    private BooksController booksController;
    private GenresController genresController;
    
    public BookView (BooksController booksController, GenresController genresController){
        this.booksController=booksController;
        this.genresController=genresController;
    }

    public void showBooks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el género");
        String genre=scanner.next(); 
        List<Book> books=booksController.getBooksbyGenres(genre);
        
        for (Book book : books) {
            List<Genre> genres=genresController.getBooksbyGenres(book.getId());
            for (Genre g : genres) {
                System.out.println(g.getGenre());
            }
            System.out.println(book.getTitle() +" - "+ book.getDescription() +" - "+book.getLanguage());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");
            
           }
        scanner.close();
    }
}
