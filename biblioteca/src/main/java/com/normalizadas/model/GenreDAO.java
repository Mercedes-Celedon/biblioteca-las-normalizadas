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

    /**
     * TODO
     * @param id
     * @return
     */
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

    /**
     * Function name: findOrCreateGenre
     * @param name
     * @return Genre   (genre's id)
     *                  The function send a query to find if the genre (or genres) 
     *                  is in the database already, and if it is, returns it's id
     *                  If it is not in the database, it updates it.
     */
    public Genre findOrCreateGenre(String genre)  {
        String sql = "SELECT id FROM genres WHERE genre = ?";
        Genre newGenre = new Genre();
        try {
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, genre);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                newGenre.setId(rs.getInt("id"));
                newGenre.setGenre(genre);
            }else {
                sql = "INSERT INTO genres (genre) VALUES (?)";
            stmn=conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmn.setString(1, genre);
            stmn.executeUpdate();
            rs = stmn.getGeneratedKeys();
            if (rs.next()) {
                newGenre.setId(rs.getInt(1));
                newGenre.setGenre(genre);
            }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return newGenre;
    }

    /**
     * TODO
     * @param genre
     * @return
     */
    @Override
    public String updateGenre(Genre genre) {
        String message = "";
        String sql="UPDATE genres SET genre = ? WHERE id = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, genre.getGenre());
            stmn.setInt(2, genre.getId());
            int execution = stmn.executeUpdate();
            message = (execution == 0) ? "\nNo se ha podido modificar el género" : "\nEl género " + genre.getGenre() +
                    " se ha actualizado correctamente";
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return message;
    }


}
