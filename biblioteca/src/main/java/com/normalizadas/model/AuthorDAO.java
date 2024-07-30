package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.normalizadas.config.DBManager;

public class AuthorDAO implements AuthorDAOInterface{
    private Connection conn;
    private PreparedStatement stmn;

    public List<Author> getAuthors(int id){
        List<Author> authors = new ArrayList<>();
        String sql="SELECT name from authors\n" +
                    "JOIN books_authors ON books_authors.id_author = authors.id\n" +
                    "WHERE books_authors.id_book = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql,id);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("id"));
                author.setName(result.getString("name"));
            }
        }catch(Exception e){

        } finally {
            DBManager.closeConnection();
        }  
        return authors;        
    }

    public int findOrCreateAuthor(String name)  {
        String sql = "SELECT id FROM authors WHERE name = ?";
        try {
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, name);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

            sql = "INSERT INTO authors (name) VALUES (?)";
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, name);
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
