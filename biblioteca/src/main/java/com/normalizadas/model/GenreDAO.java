package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.normalizadas.config.DBManager;

public class GenreDAO implements GenreDAOInterface{
    private Connection conn;
    private PreparedStatement stmn;

    public List<Genre> getGenres(int id){
        List<Genre> genres = new ArrayList<>();
        String sql="SELECT genre, id from genres\n" + //
                    "JOIN books_genres ON books_genres.id_genre = genres.id\n" +
                    "WHERE books_genres.id_book = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setInt(1, id);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Genre genre = new Genre();
                genre.setId(result.getInt("id"));
                genre.setGenre(result.getString("genre"));
                genres.add(genre);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }  
        return genres;        
    }

    public int findOrCreateGenre(String genre)  {
        String sql = "SELECT id FROM genres WHERE genre = ?";
        try {
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, genre);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

            sql = "INSERT INTO genres (genre) VALUES (?)";
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, genre);
            stmn.executeUpdate();
            rs = stmn.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return 0;
    }
}
