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

    /**
     * Function name: AllBooks
     * @param conn (Connection)
     *
     * Creates an AllBooks instance with the connection to database.
     */
    public AllBooks(Connection conn){
        this.conn = conn;
    }

    /**
     * Function name: AllBooks
     * @throws SQLException
     *
     * Prints the heading of the table and then calls the method that gets the books
     */
    public void showAll() throws SQLException{
        System.out.println("""
                
                Listado de Libros disponibles:

                ID | Título | Autor | Género | ISBN | Stock | Idioma\

                -----------------------------------------------------------------------------------------------------------------------------------------------------------""");

        getBooks();
    }

    /**
     * Function name: getBooks
     * @throws SQLException
     *
     * Executes a query that return all books, then calls functions that get all authors and genres from each book
     * when all the info is retrieved calls printBook with the data as a parameter
     */
    public void getBooks() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT b.id, b.title, b.isbn, b.stock, l.language FROM books as b\n" + //
                "JOIN languages as l ON b.id_language=l.id\n" +
                "ORDER BY b.id ASC");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String authors = getInfo(id, QUERYS[0] + id, COLUMNS[0]);
            String genres = getInfo(id,  QUERYS[1] + id, COLUMNS[1]);
            String isbn = rs.getString("isbn");
            int stock = rs.getInt("stock");
            String language = rs.getString("language");
            printBook(title, authors, genres, isbn, stock, language);
        }
        stmt.close();
        rs.close();
    }

    /**
     * Function name: getInfo
     * @param id
     * @param query
     * @param column
     * @return String
     * @throws SQLException
     *
     * Executes the query received by parameter, then executes it to get the authors or the genres
     * then returns all the items as a string separated by commas
     */
    public String getInfo(int id, String query, String column) throws SQLException {
        List<String> data = new ArrayList<>();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            data.add(rs.getString(column));
        }

        stmt.close();
        rs.close();

        return String.join(", ", data);
    }

    /**
     * Function name: getInfo
     * @param title
     * @param authors
     * @param genres
     * @param isbn
     * @param stock
     * @param language
     *
     * Receives all the data from a book and prints it
     */
    public void printBook(String title, String authors, String genres, String isbn, int stock, String language) {
        System.out.println(title + " | " + authors + " | " + genres + " | " + isbn + " | " + stock + " | " + language +
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
