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
                    exit = true;
                    break;
                default:
                    System.out.println("No has elegido ninguna opción. Saliendo del programa.");
            }
        }
        scanner.close();
    }

    public void showAllBooks() {
        List<Book> books = booksController.getAllBooks();
        printBook(books, true);
    }

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

        // for (String genre : genres) {
        // int id_genre = findOrCreateGenre(genre.trim());
        // addBookGenre(bookId, id_genre);
        // }
        // scanner.close()();

        for (String ge : genres) {
            Genre genre = genresController.findOrCreateGenre(ge.trim());
            booksController.addBookGenre(bookId, genre.getId());
        }
        //
        System.out.println("Libro añadido con éxito");
    }

    /**
     * Function Name: showUpdateMenu
     * This function asks the user which book they want to modify, then gives them
     * the different modification options
     * and, depending on the choice, calls the different methods
     */
    public void showUpdateMenu() {
        scanner.nextLine();
        System.out.println("Introduce el título del libro que quieres modificar:");
        String title = scanner.nextLine();
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
                System.out.println(updateBookInfo(book));
                break;
            case 2:
                System.out.println(updateAuthor(authorsController.getBooksbyAuthors(book.getId()), book));
                break;
            case 3:
                System.out.println(updateGenre(genresController.getBooksbyGenres(book.getId()), book));
                break;
            default:
                System.out.println("Ninguna opción elegida. Saliendo al menú inicial.");
        }

    }

    /**
     * Function name: showDeleteMenu
     * 
     * @throws SQLException
     * 
     *    This function asks user to add the title of the book
     *    they want to delete, then asks again if they are sure
     *    they want to delete it; in the case of yes, it calls the
     *    pertinent method
     */
    public void showDeleteMenu() throws SQLException {
        scanner.nextLine();
        System.out.println("Introduce el título del libro que quieres eliminar:");
        String title = scanner.nextLine();
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

    public void askAuthorBook() {
        scanner.nextLine();
        System.out.print("Escribe el nombre del autor: ");
        String askAuthorFilter = scanner.nextLine();
        List<Book> books = booksController.getBooksbyAuthors(askAuthorFilter);
        printBook(books, true);// poner true si quieres el menú con descripción
    }

    public void askTitleBook() {
        scanner.nextLine();
        System.out.print("Escribe el Titulo del libro: ");
        String askTitleFilter = scanner.nextLine();
        Book book = booksController.getBookbyTitle(askTitleFilter);
        List<Book> books = new ArrayList<>();
        books.add(book);
        printBook(books, true);// poner true si quieres el menú con descripción
    }

    public void askGenreBook() {
        scanner.nextLine();
        System.out.println("Escribe el género");
        String askGenreFilter = scanner.nextLine();
        List<Book> books = booksController.getBooksbyGenres(askGenreFilter);
        printBook(books, false);// poner true si quieres el menú con descripción
    }

    public void printBook(List<Book> books, boolean printDescription) {
        if (printDescription) {// esta opción es para el menú con descripción. importante que pases el
                               // parametro como true en tu función
            System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s | %-25s |\n", "Titulo", "Autor", "Genero",
                    "ISBN", "Stock", "Idioma", "Descripción");
            System.out.println("=".repeat(165));
            for (Book book : books) {
                List<Genre> genres = genresController.getBooksbyGenres(book.getId());
                List<String> data = new ArrayList<>();
                for (Genre g : genres) {
                    data.add(g.getGenre());
                }
                List<Author> authors = authorsController.getBooksbyAuthors(book.getId());
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
                System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s |", title, authorList, genresString,
                        isbn, stock, language);
                printFormatDescription(description, 25);
                System.out.print("\n");
                System.out.println("-".repeat(165));
            }
        } else {
            System.out.printf("| %-32s | %-25s | %-35s | %-15s | %-5s | %-12s |\n", "Titulo", "Autor", "Genero", "ISBN",
                    "Stock", "Idioma");
            System.out.println("=".repeat(142));
            for (Book book : books) {
                List<Genre> genres = genresController.getBooksbyGenres(book.getId());
                List<String> data = new ArrayList<>();
                for (Genre g : genres) {
                    data.add(g.getGenre());
                }
                List<Author> authors = authorsController.getBooksbyAuthors(book.getId());
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
                System.out.printf("| %-32s | %-25s | %-35s | %-15s | %-5s | %-12s |", title, authorList, genresString,
                        isbn, stock, language);
                System.out.print("\n");
                System.out.println("-".repeat(142));
            }
        }

    }

    private void printFormatDescription(String description, int lineWidth) {
        int length = description.length();
        boolean firstLine = true;
        for (int i = 0; i < length; i += lineWidth) {
            if (!firstLine) {
                System.out.printf("| %-28s | %-25s | %-35s | %-15s | %-5s | %-12s | ", "", "", "", "", "", "");
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

    /**
     * Function name: updateBookInfo
     * 
     * @param book selected book
     * @return (String) message that says if the update was successful
     * Function that receives a book by parameter, asks the user what
     * values of the book wants to modify and
     * updates the object with the new data, finally calls the
     * controller that modifies the book record in the database
     */
    public String updateBookInfo(Book book) {
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

        return booksController.updateBook(book, getIdLanguage(book.getLanguage()));
    }

    /**
     * Function name: updateAuthor
     * 
     * @param authors list of authors from the selected book
     * @return (String) message that says if the update was successful
     * Function that receives a book and its authors by parameter,
     * asks the user the new name of the authors
     * and calls the controller that modifies each author record in
     * the database
     */
    public String updateAuthor(List<Author> authors, Book book) {
        String message = "";
        for (Author author : authors) {
            System.out.println("Nombre actual del autor -> " + author.getName());
            System.out.print("Autor corregido -> ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()){
                author.setName(newName);
                message += authorsController.updateAuthor(author);
            }
        }
        return message;
    }

    /**
     * Function name: updateGenre
     * 
     * @param genres list of genres from the selected book
     * @return (String) message that says if the update was successful
     * Function that receives a book and its genres by parameter, asks
     * the user the new name of the genres
     * and calls the controller that modifies each genre record in the
     * database
     */
    public String updateGenre(List<Genre> genres, Book book) {
        String message = "";
        for (Genre genre : genres) {
            System.out.println("Nombre actual del genero -> " + genre.getGenre());
            System.out.print("Genero corregido -> ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()){
                genre.setGenre(newName);
                message += genresController.updateGenre(genre);
            }
        }
        return message;
    }

    /**
     * Function name: getIdLanguage
     * 
     * @param language language name
     * @return (int) the id of the language
     *         Gets the name of a language by parameter and returns its id
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
