package com.normalizadas.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.normalizadas.config.DBManager;
import com.normalizadas.controller.AuthorsController;
import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.Author;
import com.normalizadas.model.Book;
import com.normalizadas.model.BookDAO;
import com.normalizadas.model.Genre;

public class BookView {

    private static Scanner scanner;

    private BooksController booksController;
    private GenresController genresController;
    private AuthorsController authorsController;

    public BookView(BooksController booksController, GenresController genresController, AuthorsController authorsController) {
        this.booksController = booksController;
        this.genresController = genresController;
        this.authorsController = authorsController;
    }

    public void showMainMenu() throws SQLException {
        scanner = new Scanner(System.in);
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
    
            int opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    prueba();
                    break;
                case 2:
                    showSearchMenu();
                    showSearchMenu();
                    break;
                case 3:
                    showAddBook();
                    break;
                case 4:
                    // Método modificar libro
                    break;
                case 5:
                    showDeleteMenu();
                    showDeleteMenu();
                    break;
                case 6:
                    exit = true;
                    scanner.close();
                    break;
                default:
                    System.out.println("No has elegido ninguna opción. Saliendo del programa.");
            }
        }
        
    }
    public void prueba(){
        System.out.println("prueba");
    }
    public void showSearchMenu() throws SQLException {

        scanner = new Scanner(System.in);

        System.out.println("\n¿Selecciona una opción de búsqueda con el número: ");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por género.");

        int opc = scanner.nextInt();
        scanner.nextLine();

        switch (opc) {
            case 1:
                // searchByTitle();
                break;
            case 2:
                searchByAuthor();
                break;
            case 3:
                // searchByGenre();
                showBooks();
                break;
            default:
                System.out.println("Ninguna opción elegida. Saliendo al menú inicial.");
                showMainMenu();
        }

        scanner.close();
    }

    public void showDeleteMenu() throws SQLException {

        scanner = new Scanner(System.in);

        int choice;

        System.out.println("¿Quieres borrar este libro?");
        System.out.println("1. Sí.");
        System.out.println("2. No.");

        choice = scanner.nextInt();
        scanner.close();

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

    public void showAddBook() throws SQLException {
        scanner = new Scanner(System.in);

        
        System.out.println("Indica el título:");
        String title = scanner.nextLine();
            if (booksController.bookExists(title)) {
                System.out.print("Este título ya está registrado");
                return;
            }
            System.out.print("Añade una descripción (de menos de 200 caracteres):");
            String description = scanner.nextLine();
            System.out.print("Indica el ISBN:");
            String isbn = scanner.nextLine();
            System.out.print("Indica el stock:");
            int stock = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Indica el idioma (escribe solo el número): " +
                                                                        "\n\t1. Español " +
                                                                        "\n\t2. Inglés " +
                                                                        "\n\t3. Francés " +
                                                                        "\n\t4. Catalán.");
            int id_language = scanner.nextInt();
            scanner.nextLine();

            int bookId = booksController.insertBook(title, description, isbn, stock, id_language);

            System.out.print("Indica el autor o autores (en este caso separados por comas): ");
            String[] authors = scanner.nextLine().split(",");

            for (String au : authors) {
                Author author = authorsController.findOrCreateAuthor(au.trim());
                int authorId = author.getId();
                booksController.addBookAuthor(bookId, authorId);
            }

            System.out.print("Indica el género o géneros (en este caso separados por comas): ");
            String[] genres = scanner.nextLine().split(",");

            for (String ge : genres) {
                Genre genre = genresController.findOrCreateGenre(ge.trim());
                booksController.addBookGenre(bookId, genre.getId());
            }
            scanner.close();
            System.out.println("Libro añadido con éxito");
    }

    public void showBooks() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el género");
        String genre = scanner.next();
        List<Book> books = booksController.getBooksbyGenres(genre);

        for (Book book : books) {
            List<Genre> genres = genresController.getBooksbyGenres(book.getId());
            for (Genre g : genres) {
                System.out.println(g.getGenre());
            }
            System.out.println(book.getTitle() + " - " + book.getDescription() + " - " + book.getLanguage());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");

        }
        scanner.close();
    }

    public void searchByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el Autor");
        String author = scanner.nextLine();
        List<Book> books = booksController.getBooksbyAuthors(author);

        for (Book book : books) {
            List<Author> authors = authorsController.getBooksbyAuthors(book.getId());
            for (Author a : authors) {
                System.out.println(a.getName());
            }
            System.out.println(book.getTitle() + " - " + book.getDescription() + " - " + book.getLanguage());
            System.out.println(book.getIsbn());
            System.out.println("-------------------");
            
        }
        scanner.close();
    }
}
