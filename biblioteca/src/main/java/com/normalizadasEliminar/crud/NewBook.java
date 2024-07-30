package com.normalizadas.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NewBook {

    private Connection conn;
    private static Scanner scanner;
    
    public NewBook(Connection conn, Scanner scanner) {
        this.conn = conn;
        NewBook.scanner = scanner;
    }

    // public void addBook() throws SQLException {
    //     scanner.nextLine();
    //     System.out.println("Indica el título:");
    //     String title = scanner.nextLine();
    //     if (bookExists(title)) {
    //         System.out.print("Este título ya está registrado");
    //         return;
    //     }
    //     System.out.print("Añade una descripción (de menos de 200 caracteres):");
    //     String description = scanner.nextLine();
    //     System.out.print("Indica el ISBN:");
    //     String isbn = scanner.nextLine();
    //     System.out.print("Indica el stock:");
    //     int stock = scanner.nextInt();
    //     scanner.nextLine();
    //     System.out.println("Indica el idioma (escribe solo el número): "+
    //                                                             "\n\t1. Español "+
    //                                                             "\n\t2. Inglés "+
    //                                                             "\n\t3. Francés "+
    //                                                             "\n\t4. Catalán.");
    //     int id_language = scanner.nextInt();
    //     scanner.nextLine();

    //     int bookId = insertBook(title, description, isbn, stock, id_language);

    //     System.out.print("Indica el autor o autores (en este caso separados por comas): ");
    //     String[] authors = scanner.nextLine().split(",");

    //     for (String author : authors) {
    //         int id_author = findOrCreateAuthor(author.trim());
    //         addBookAuthor(bookId, id_author);
    //     }

    //     System.out.print("Indica el género o géneros (en este caso separados por comas): ");
    //     String[] genres = scanner.nextLine().split(",");
        
    //     for (String genre : genres) {
    //         int id_genre = findOrCreateGenre(genre.trim());
    //         addBookGenre(bookId, id_genre);
    //     }
        
    //     System.out.println("Libro añadido con éxito");
    // }

    // private boolean bookExists(String title) throws SQLException {
    //     try (PreparedStatement stmt = conn.prepareStatement("SELECT id FROM books WHERE title = ?")) {
    //         stmt.setString(1, title);
    //         ResultSet rs = stmt.executeQuery();
    //         return rs.next();
    //     }
    // }

    // private int insertBook(String title, String description, String isbn, int stock, int id_language) throws SQLException {
    //     try (PreparedStatement stmn = conn.prepareStatement("INSERT INTO books (title, description, isbn, stock, id_language) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
    //         stmn.setString(1, title);
    //         stmn.setString(2, description);
    //         stmn.setString(3, isbn);
    //         stmn.setInt(4, stock);
    //         stmn.setInt(5, id_language);
    //         stmn.executeUpdate();

    //         ResultSet rs = stmn.getGeneratedKeys();
    //         if (rs.next()) {
    //             return rs.getInt(1);
    //         }
    //     }
    //     return 0;
    // }

    // private int findOrCreateAuthor(String name) throws SQLException {
    //     try (PreparedStatement stmn = conn.prepareStatement("SELECT id FROM authors WHERE NAME = ?")) {
    //         stmn.setString(1, name);
    //         ResultSet rs = stmn.executeQuery();
    //         if (rs.next()) {
    //             return rs.getInt("id");
    //         }
    //     }

    //     try (PreparedStatement stmn = conn.prepareStatement("INSERT INTO authors (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
    //         stmn.setString(1, name);
    //         stmn.executeUpdate();

    //         ResultSet rs = stmn.getGeneratedKeys();
    //         if (rs.next()) {
    //             return rs.getInt(1);
    //         }
    //     }

    //     return 0;
    // }

    // private int findOrCreateGenre(String genre) throws SQLException {
    //     try (PreparedStatement stmn = conn.prepareStatement("SELECT id FROM genres WHERE genre = ?")) {
    //         stmn.setString(1, genre);
    //         ResultSet rs = stmn.executeQuery();
    //         if (rs.next()) {
    //             return rs.getInt("id");
    //         }
    //     }

    //     try (PreparedStatement stmn = conn.prepareStatement("INSERT INTO genres (genre) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
    //         stmn.setString(1, genre);
    //         stmn.executeUpdate();

    //         ResultSet rs = stmn.getGeneratedKeys();
    //         if (rs.next()) {
    //             return rs.getInt(1);
    //         }
    //     }

    //     return 0;
    // }

    // private void addBookAuthor(int bookId, int authorId) throws SQLException {
    //     try (PreparedStatement stmn = conn.prepareStatement("INSERT INTO books_authors (id_book, id_author) VALUES (?, ?)")) {
    //         stmn.setInt(1, bookId);
    //         stmn.setInt(2, authorId);
    //         stmn.executeUpdate();
    //     }
    // }

//     private void addBookGenre(int bookId, int genreId) throws SQLException {
//         try (PreparedStatement stmn = conn.prepareStatement("INSERT INTO books_genres (id_book, id_genre) VALUES (?, ?)")) {
//             stmn.setInt(1, bookId);
//             stmn.setInt(2, genreId);
//             stmn.executeUpdate();
//         }
//     }
 }