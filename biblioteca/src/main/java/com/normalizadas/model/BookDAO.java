package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.normalizadas.config.DBManager;

public class BookDAO implements BookDAOInterface{
    private Connection conn;
    private PreparedStatement stmn;

    public List<Book> getBooksbyGenres(String genre){
        List<Book> books = new ArrayList<>();
        String sql= "SELECT b.title, b.description, b.isbn, b.stock, b.id, l.language FROM books as b\n"+
                "JOIN books_genres as ba ON b.id=ba.id_book\n"+
                "JOIN genres as ge ON ba.id_genre=ge.id\n"+
                "JOIN languages as l ON l.id=b.id_language\n"+                
                "WHERE ge.genre = ?";
        try{
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            stmn.setString(1, genre);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setIsbn(result.getString("isbn"));
                book.setStock(result.getInt("stock"));
                book.setLanguage(result.getString("language"));
                books.add(book);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            DBManager.closeConnection();
        }
        return books;
   
    }
}
