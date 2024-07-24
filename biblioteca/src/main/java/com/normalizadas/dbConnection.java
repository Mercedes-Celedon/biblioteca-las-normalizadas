package com.normalizadas;
import com.normalizadas.dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
  /* to do database connection*/  
  private static String user = "postgres";
  private static String pass = "";
  public static Connection connection;

  public static void initConnection(){
    try{
      connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",user,pass);
      System.out.println("Conectado");
    }catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  public static void insertBook(String title, String description, String isbn, int stock, int id_language) throws SQLException{
    PreparedStatement stmn = connection.prepareStatement("INSERT INTO books (title, description, isbn, stock, id_language) VALUES (?,?,?,?,?)");
    stmn.setString(1, title);
    stmn.setString(2, description);
    stmn.setString(3, isbn);
    stmn.setInt(4, stock);
    stmn.setInt(5, id_language);
    
    stmn.execute();
  }

}

