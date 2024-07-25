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