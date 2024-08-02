package com.normalizadas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.normalizadas.config.DBManager;

public class AuthorDAO implements AuthorDAOInterface {
    private Connection conn;
    private PreparedStatement stmn;

    /**
     * Function name: getAuthors
     * @param id    book id
     * @return      List<Author>
     *              gets the id of a book and returns all of its authors
     */
    public List<Author> getAuthors(int id) {
        List<Author> authors = new ArrayList<>();
        String sql="SELECT name, id from authors\n" +
                    "JOIN books_authors ON books_authors.id_author = authors.id\n" +
                    "WHERE books_authors.id_book = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setInt(1, id);
            ResultSet result = stmn.executeQuery();
            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("id"));
                author.setName(result.getString("name"));
                authors.add(author);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }  
        return authors;
    }

    /**
     * Function name: findOrCreateAuthor
     * @param name      author's name
     * @return Author   (author's id)
     *                  The function send a query to find if the author 
     *                  is in the database already, and if it is, returns it's id
     *                  If it is not in the database, it updates it.
     */
    public Author findOrCreateAuthor(String name)  {
        String sql = "SELECT id FROM authors WHERE name = ?";
        Author author = new Author();
        try {
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, name);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                author.setId(rs.getInt("id"));
                author.setName(name);
            }else {
                sql = "INSERT INTO authors (name) VALUES (?)";
                stmn=conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmn.setString(1, name);
                stmn.executeUpdate();
                rs = stmn.getGeneratedKeys();
                if (rs.next()) {
                    author.setId(rs.getInt(1));
                    author.setName(name);
                }
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return author;
    }

    /**
     * Function name: updateAuthor
     * @param author an author object
     * @return String - a success or error message
     * Gets by parameter an author that has already the data updated and executes the query that updates the bd entry.
     */
    @Override
    public String updateAuthor(Author author) {
        String message = "";
        String sql="UPDATE authors SET name = ? WHERE id = ?";
        try{
            conn=DBManager.getDbConnection();
            stmn=conn.prepareStatement(sql);
            stmn.setString(1, author.getName());
            stmn.setInt(2, author.getId());
            int execution = stmn.executeUpdate();
            message = (execution == 0) ? "\nNo se ha podido modificar el autor" : "\nEl autor " + author.getName() +
                    " se ha actualizado correctamente";
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return message;
    }
}
