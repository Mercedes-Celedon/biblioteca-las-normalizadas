package com.normalizadas.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllBooks {

    private final String[] QUERYS = {
            "SELECT name from authors\n" +
                    "JOIN books_authors ON books_authors.id_author = authors.id\n" +
                    "WHERE books_authors.id_book = ",
            "SELECT genre from genres\n" + //
                    "JOIN books_genres ON books_genres.id_genre = genres.id\n" +
                    "WHERE books_genres.id_book = "
    };
    private final String[] COLUMNS = {"name", "genre"};

    private Connection conn;


    public AllBooks(Connection conn){
        this.conn = conn;
    }

    public void showAll() throws SQLException{
        System.out.println("""
                
                Listado de Libros disponibles:

                ID | Título | Autor | Género | ISBN | Stock | Idioma\

                -----------------------------------------------------------------------------------------------------------------------------------------------------------""");

        getBooks();
    }

    public void getBooks() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT b.id, b.title, b.isbn, b.stock, l.language FROM books as b\n" + //
                "JOIN languages as l ON b.id_language=l.id\n" +
                "ORDER BY b.id ASC");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String authors = getInfo(id, "authors");
            String genres = getInfo(id, "genres");
            String isbn = rs.getString("isbn");
            int stock = rs.getInt("stock");
            String language = rs.getString("language");
            printBook(title, authors, genres, isbn, stock, language);
        }
        stmt.close();
        rs.close();
    }

    public String getInfo(int id, String dataType) throws SQLException {
        List<String> data = new ArrayList<>();
        int position = 0;
        if (dataType.equals("genres")) {
            position = 1;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERYS[position]+id);

        while (rs.next()) {
            data.add(rs.getString(COLUMNS[position]));
        }

        stmt.close();
        rs.close();

        return String.join(", ", data);
    }

    public void printBook(String title, String authors, String genres, String isbn, int stock, String language) {
        System.out.println(title + " | " + authors + " | " + genres + " | " + isbn + " | " + stock + " | " + language +
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
