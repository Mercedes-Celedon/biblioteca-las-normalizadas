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

    public List<Book> getBooksbyGenres(String genreFilter){
        List<Book> books = new ArrayList<>();
        String sql= "SELECT ge.genre, b.title, b.isbn, b.stock, l.language FROM books as b\n"+
                "JOIN books_genres as ba ON b.id=ba.id_book\n"+
                "JOIN genres as ge ON ba.id_genre=ge.id\n"+
                "JOIN languages as l ON l.id=b.id_language\n"+
                "WHERE ge.genre = '" + genreFilter + "';";
        try{
            conn = DBManager.getDbConnection();
            stmn = conn.prepareStatement(sql);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
            }

        }catch(Exception e){
            System.out.println("Conexión fallida");
        }finally{
            DBManager.closeConnection();
        }
        return books;
   
    }
}
