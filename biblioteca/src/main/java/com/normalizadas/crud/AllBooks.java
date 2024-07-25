package com.normalizadas.crud;

import com.normalizadas.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AllBooks {

    private Connection conn;
    public AllBooks(Connection conn){
        this.conn = conn;
    }
    public void showAll() throws SQLException {
        System.out.println("""
                Listado de Libros disponibles: \

                ID | Título | Autor | Género | ISBN | Stock | Idioma\

                -----------------------------------------------------------------------------------------------------------------------------------------------------------
                
                """);
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

            System.out.println(id + " | " + title + " | " + authors + " | " + genres + " | " + isbn + " | " + stock + " | " + language +
                    "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        stmt.close();
        rs.close();
    }

    public String getInfo(int id, String dataType) throws SQLException {
        String data = "";
        String query = "";
        String column = "";
        switch (dataType) {
            case "authors":
                query = "SELECT name from authors\n" + //
                        "JOIN books_authors ON books_authors.id_author = authors.id\n" +
                        "WHERE books_authors.id_book = " + id;
                column = "name";
                break;
            case "genres":
                query = "SELECT genre from genres\n" + //
                        "JOIN books_genres ON books_genres.id_genre = genres.id\n" +
                        "WHERE books_genres.id_book = " + id;
                column = "genre";
                break;
            default:
                break;
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            data += rs.getString(column) + ", ";
        }

        stmt.close();
        rs.close();

        return data.substring(0, data.length() - 2);
    }
}
