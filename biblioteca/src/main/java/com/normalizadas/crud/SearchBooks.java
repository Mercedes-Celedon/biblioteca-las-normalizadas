package com.normalizadas.crud;

import com.normalizadas.App;
import com.normalizadas.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/* Function search books by filters - searchBooks */
public class SearchBooks {
    
    public Scanner scanner;
    private Connection conn;
    public SearchBooks(Connection conn){
        this.conn = conn;
    
    /*
     * Add functions:
     * searchByTitle()
     * searchByAuthor()
     * searchByGenre()
     */
    // System.out.println("A buscar");
}


/* Function search a book by title - searchByTitle */
public void searchByTitle() {

}


/* Function search a book by author - searchByAuthor */
public void searchByAuthor() {
       /* private Connection conn;
        public searchByAuthor(Connection conn){
        this.conn = conn; */
        System.out.print("Introduce el nombre del Autor: ");
        scanner.nextLine();
        String authorFilter = scanner.nextLine();
        System.out.println(authorFilter);
        try {
            Statement stmn = App.conn.createStatement();
            ResultSet result = stmn.executeQuery("SELECT a.name, b.title FROM authors as a\n" + //
                                                "\tJOIN books_authors as ba ON a.id=ba.id_author\n" + //
                                                "\tJOIN books as b ON ba.id_book=b.id\n" + //
                                                "\tWHERE a.name='"+authorFilter+"';");

            System.out.println(" Author       |      Book ");
            while (result.next() ){ 
                String book = result.getString("title");

                System.out.println(authorFilter+ " | " +book);
                }
            stmn.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




/* Function search a book by genre - searchByGenre */
public void searchByGenre()throws SQLException{
    Statement stmt = App.conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM books ORDER BY id ASC");
        while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("title");
                System.out.println(id + " " + nombre);
            }
        stmt.close();
        rs.close();
}  

}