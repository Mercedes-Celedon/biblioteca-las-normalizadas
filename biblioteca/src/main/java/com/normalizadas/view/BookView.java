package com.normalizadas.view;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.normalizadas.controller.AuthorsController;
import com.normalizadas.controller.BooksController;
import com.normalizadas.controller.GenresController;
import com.normalizadas.model.Author;
import com.normalizadas.model.Book;
import com.normalizadas.model.Genre;

public class BookView {

    private static Scanner scanner;

    private BooksController booksController;
    private GenresController genresController;
    private AuthorsController authorsController;

    public BookView(BooksController booksController, GenresController genresController,
                    AuthorsController authorsController) {
        this.booksController = booksController;
        this.genresController = genresController;
        this.authorsController = authorsController;
    }

    /**
     * Function name: showMainMenu
     * This function initiates the scanner and prints the main
     * menu with the different options of the program to choose
     * from
     */
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
                    showAllBooks();
                    break;
                case 2:
                    showSearchMenu();
                    break;
                case 3:
                    showAddBook();
                    break;
                case 4:
                    showUpdateMenu();
                    break;
                case 5:
                    showDeleteMenu();
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    exit = true;
                    break;
                default:
                    System.out.println("No has elegido ninguna opción. Saliendo del programa.");
            }
        }
        scanner.close();
    }

    /**
     * Function name: showAllBooks
     * Gets all books and calls the function that prints them
     */
    public void showAllBooks() {
        List<Book> books = booksController.getAllBooks();
        printBook(books, true);
    }

    /**
     * Function name: showAllBooks
     * Shows the search submenu and calls the function of the option chosen by the user
     */
    public void showSearchMenu() throws SQLException {

        System.out.println("\n¿Selecciona una opción de búsqueda con el número: ");
        System.out.println("1. Buscar por título.");
        System.out.println("2. Buscar por autor.");
        System.out.println("3. Buscar por género.");

        int opcFilter = scanner.nextInt();

        switch (opcFilter) {
            case 1:
                askTitleBook();
                break;
            case 2:
                askAuthorBook();
                break;
            case 3:
                askGenreBook();
                break;
            default:
                System.out.println("Ninguna opción elegida. Saliendo al menú inicial.");
                showMainMenu();
        }

    }

    /**
     * Function name: showAddBook
     * The function asks the user for the data of the new book
     * and calls the corresponding functions to update the database
     */
    public void showAddBook() throws SQLException {
        scanner.nextLine();
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

        System.out.println("Libro añadido con éxito");
    }

    /**
     * Function Name: showUpdateMenu
     * This function asks the user which book they want to modify, if it exists then gives them
     * the different modification options and, depending on the choice, calls the different methods
     */
    public void showUpdateMenu() {
        scanner.nextLine();
        System.out.println("Introduce el título del libro que quieres modificar:");
        String title = scanner.nextLine();

        if (!booksController.bookExists(title)) {
            System.out.println("\nError, el libro no existe. \nSaliendo al menú principal.");
        } else {
            Book book = booksController.getBookbyTitle(title);
            System.out.println("Vas a modificar el libro -> " + book.getTitle() +
                    "\nPor favor selecciona el número de la opción deseada:" +
                    "\n1. Modificar características del libro (Título, Descripción, ISBN, Stock o Idioma" +
                    "\n2. Corregir el nombre del autor." +
                    "\n3. Corregir el género." +
                    "\n\n¡Atención, no cambiar autores o géneros! si deseas hacerlo deberás eliminar el libro y " +
                    "volver a introducirlo con los nuevos datos.");
            int opc = 0;
            opc = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Si no quieres modificar algún dato, deja el campo en blanco y pulsa enter.");
            switch (opc) {
                case 1:
                    updateBookInfo(book);
                    break;
                case 2:
                    updateAuthor(authorsController.getAuthorsByBook(book.getId()), book);
                    break;
                case 3:
                    updateGenre(genresController.getGenresByBook(book.getId()), book);
                    break;
                default:
                    System.out.println("\nNinguna opción elegida. \nSaliendo al menú principal.");
            }
        }
    }

    /**
     * Function name: showDeleteMenu
     * This function asks the user to introduce the book title they want to delete, then asks to confirm they want to
     * delete it; if the choice is yes, it calls the pertinent method
     */
    public void showDeleteMenu() throws SQLException {
        scanner.nextLine();
        System.out.println("Introduce el título del libro que quieres eliminar:");
        String title = scanner.nextLine();
        if (!booksController.bookExists(title)) {
            System.out.print("\nError, el libro no existe. \nVolviendo al menú principal. ");
        } else {
            Book book = booksController.getBookbyTitle(title);

            System.out.println("¿Seguro que quieres eliminar el libro: " + book.getTitle() + "?");
            System.out.println("1. Sí.");
            System.out.println("2. No.");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    booksController.deleteBook(book.getId());
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

    /**
     * Function name: askAuthorBook
     * Function that receives the author name as a parameter, asks the user for the name of the author of the book
     * and calls the controller that generates a list of books according to the author searched in the database
     * then call the printBook function to print a table with the book
     */
    public void askAuthorBook() {
        scanner.nextLine();
        System.out.print("Escribe el nombre del autor: ");
        String askAuthorFilter = scanner.nextLine();
        List<Book> books = booksController.getBooksbyAuthors(askAuthorFilter);
        printBook(books, true);
    }

    /**
     * Function name: askTitleBook
     * Asks the user for the title of the book he wants to search for and if it exists,
     * passes it to the function that prints it, if not it shows an information message.
     */
    public void askTitleBook() {
        scanner.nextLine();
        System.out.print("Escribe el Título del libro: ");
        String title = scanner.nextLine();
        if (!booksController.bookExists(title)) {
            System.out.print("\nError, el libro no existe. \nVolviendo al menú principal. ");
        } else {
            Book book = booksController.getBookbyTitle(title);
            List<Book> books = new ArrayList<>();
            books.add(book);
            printBook(books, true);
        }
    }

    /**
     * Function name: askGenreBook
     * Asks the user for the genre of books he wants to search for and if it exists,
     * passes the list of books to the function that prints them.
     */
    public void askGenreBook() {
        scanner.nextLine();
        System.out.println("Escribe el género");
        String askGenreFilter = scanner.nextLine();
        List<Book> books = booksController.getBooksbyGenres(askGenreFilter);
        printBook(books, false);
    }

    /**
     * Function name: printBook
     *
     * @param books            list of books
     * @param printDescription should print description?
     *                         Function that receives a list of books and a boolean
     *                         by parameter and print the books with format
     */
    public void printBook(List<Book> books, boolean printDescription) {
        if (printDescription) {
            System.out.printf("| %-15s | %-15s | %-18s | %-15s | %-5s | %-10s | %-50s |\n", "Titulo", "Autor", "Genero", "ISBN", "Stock", "Idioma", "Descripción");
            System.out.println("=".repeat(150));
            for (Book book : books) {
                List<Genre> genres = genresController.getGenresByBook(book.getId());
                List<String> data = new ArrayList<>();
                for (Genre g : genres) {
                    data.add(g.getGenre());
                }
                List<Author> authors = authorsController.getAuthorsByBook(book.getId());
                List<String> dataAuthor = new ArrayList<>();
                for (Author auth : authors) {
                    dataAuthor.add(auth.getName());
                }
                String genresString = String.join(", ", data);
                String authorList = String.join(", ", dataAuthor);
                String title = book.getTitle();
                String description = book.getDescription();
                String language = book.getLanguage();
                String isbn = book.getIsbn();
                int stock = book.getStock();
                printTableFormat(title, authorList, genresString, isbn, stock, language, description);
            }
        } else {
            System.out.printf("| %-15s | %-15s | %-18s | %-15s | %-5s | %-10s |\n", "Titulo", "Autor", "Genero", "ISBN", "Stock", "Idioma");
            System.out.println("=".repeat(150));
            for (Book book : books) {
                List<Genre> genres = genresController.getGenresByBook(book.getId());
                List<String> data = new ArrayList<>();
                for (Genre g : genres) {
                    data.add(g.getGenre());
                }
                List<Author> authors = authorsController.getAuthorsByBook(book.getId());
                List<String> dataAuthor = new ArrayList<>();
                for (Author auth : authors) {
                    dataAuthor.add(auth.getName());
                }
                String genresString = String.join(", ", data);
                String authorList = String.join(", ", dataAuthor);
                String title = book.getTitle();
                String language = book.getLanguage();
                String isbn = book.getIsbn();
                int stock = book.getStock();

                printTableFormat(title, authorList, genresString, isbn, stock, language, "");
            }
        }
    }

    /**
     * Function name: printTableFormat
     *
     * @param title       book title
     * @param author      book author
     * @param genre       book genre
     * @param isbn        book isbn
     * @param stock       quantity in stock
     * @param language    book language
     * @param description book description
     *                    Prints a formated table
     */
    private void printTableFormat(String title, String author, String genre, String isbn, int stock, String language, String description) {
        String[] titleLines = splitIntoLines(title, 15);
        String[] authorLines = splitIntoLines(author, 15);
        String[] genreLines = splitIntoLines(genre, 15);
        String[] descriptionLines = splitIntoLines(description, 50);

        int maxLines = Math.max(titleLines.length, Math.max(authorLines.length, genreLines.length));

        for (int i = 0; i < maxLines; i++) {
            String titlePart = i < titleLines.length ? titleLines[i] : "";
            String authorPart = i < authorLines.length ? authorLines[i] : "";
            String genrePart = i < genreLines.length ? genreLines[i] : "";

            if (i == 0) {
                System.out.printf("| %-15s | %-15s | %-18s | %-15s | %-5s | %-10s |\n", titlePart, authorPart, genrePart, isbn, stock, language);
            } else {
                System.out.printf("| %-15s | %-15s | %-18s | %-15s | %-5s | %-10s |\n", titlePart, authorPart, genrePart, "", "", "");
            }
        }
        for (String line : descriptionLines) {
            System.out.printf("| %-15s | %-15s | %-18s | %-15s | %-5s | %-10s | %-50s |\n", "", "", "", "", "", "", line);
        }
        System.out.println("=".repeat(150));
    }

    /**
     * Function name: splitIntoLines
     *
     * @param text      the text to format
     * @param lineWidth the width of the column
     *                  Function that receives a text and a width to return the text adapted to that width
     */
    private String[] splitIntoLines(String text, int lineWidth) {
        int length = text.length();
        int lines = (length + lineWidth - 1) / lineWidth;
        String[] result = new String[lines];

        for (int i = 0; i < lines; i++) {
            int start = i * lineWidth;
            int end = Math.min(start + lineWidth, length);
            result[i] = text.substring(start, end);
        }
        return result;
    }


    /**
     * Function name: updateBookInfo
     *
     * @param book selected book
     *             Function that receives a book by parameter, asks the user what
     *             values of the book wants to modify and
     *             updates the object with the new data, finally calls the
     *             controller that modifies the book record in the database
     */
    public void updateBookInfo(Book book) {
        String data;
        book.setId(book.getId());
        System.out.print("\nTítulo actual-> " + book.getTitle() + "\nNuevo título-> ");
        data = scanner.nextLine();
        book.setTitle(!data.isEmpty() ? data : book.getTitle());

        System.out.print("\nDescripción actual-> " + book.getDescription() + "\nNueva descripción-> ");
        data = scanner.nextLine();
        book.setDescription(!data.isEmpty() ? data : book.getDescription());

        System.out.print("\nStock actual-> " + book.getStock() + "\nNuevo stock-> ");
        data = scanner.nextLine();
        book.setStock(!data.isEmpty() ? Integer.parseInt(data) : book.getStock());

        System.out.print("\nISBN actual-> " + book.getIsbn() + "\nNuevo ISBN-> ");
        data = scanner.nextLine();
        book.setIsbn(!data.isEmpty() ? data : book.getIsbn());

        System.out.print(
                "\nIdioma actual-> " + book.getLanguage() + "\nNuevo idioma (Español, Inglés, Francés o Catalán)-> ");
        data = scanner.nextLine();
        book.setLanguage(!data.isEmpty() ? data : book.getLanguage());

        booksController.updateBook(book, getIdLanguage(book.getLanguage()));
    }

    /**
     * Function name: updateAuthor
     *
     * @param authors list of authors from the selected book
     * @param book    selected book
     *                Function that receives a book and its authors by parameter,
     *                asks the user the new name of the authors
     *                and calls the controller that modifies each author record in
     *                the database
     */
    public void updateAuthor(List<Author> authors, Book book) {

        for (Author author : authors) {
            System.out.println("Nombre actual del autor -> " + author.getName());
            System.out.print("Autor corregido -> ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                author.setName(name);
                authorsController.updateAuthor(author);
            }
        }
    }

    /**
     * Function name: updateGenre
     *
     * @param genres list of genres from the selected book
     * @param book   selected book
     *               Function that receives a book and its genres by parameter, asks
     *               the user the new name of the genres
     *               and calls the controller that modifies each genre record in the
     *               database
     */
    public void updateGenre(List<Genre> genres, Book book) {
        for (Genre genre : genres) {
            System.out.println("Nombre actual del genero -> " + genre.getGenre());
            System.out.print("Genero corregido -> ");
            String genreName = scanner.nextLine();
            if (!genreName.isEmpty()) {
                genre.setGenre(genreName);
                genresController.updateGenre(genre);
            }
        }
    }

    /**
     * Function name: getIdLanguage
     *
     * @param language language name
     * @return (int) the id of the language
     * Gets the name of a language by parameter and returns its id
     */
    public int getIdLanguage(String language) {
        return switch (language) {
            case "Español" -> 1;
            case "Inglés" -> 2;
            case "Francés" -> 3;
            case "Catalán" -> 4;
            default -> 0;
        };
    }
}
