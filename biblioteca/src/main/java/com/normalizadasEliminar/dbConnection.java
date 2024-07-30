package com.normalizadas;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.normalizadas.config.DBManager;


public class dbConnection {
  /* to do database connection*/
    public Connection getDbConnection() { 
      Dotenv dotenv= Dotenv.load();
        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    } 

    public void closeConnection(Connection conn){
      if (conn != null) {
        try {
            conn.close();
            System.out.println("Conexión cerrada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
    }

    public PreparedStatement prepareStatement(String string) {
      throw new UnsupportedOperationException("Unimplemented method 'prepareStatement'");
    }

}

