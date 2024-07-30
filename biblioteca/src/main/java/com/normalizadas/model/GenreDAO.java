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
        String sql="SELECT genre from genres\n" + //
                    "JOIN books_genres ON books_genres.id_genre = genres.id\n" +
                    "WHERE books_genres.id_book = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql,id);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Genre genre = new Genre();
                genre.setId(result.getInt("id"));
                genre.setGenre(result.getString("genre"));
            }
        }catch(Exception e){

        } finally {
            DBManager.closeConnection();
        }  
        return genres;        
    }
}
