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
            stmn.setInt(1, id);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("id"));
                author.setName(result.getString("name"));
                authors.add(author);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }  
        return authors;        
    }
}
