package com.normalizadas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
  /* to do database connection*/
    public Connection getDbConnection() { 
        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String user = "postgres";
        String password = "user615";

        try (Connection conn = DriverManager.getConnection( url, user, password)) {
          if (conn != null) {
            // check connection status
            // conn.isOpen()
            System.out.println("Connected to the database!");
            return conn;
          } else {
            System.out.println("Failed to make connection!");
            return null;
          }
          } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            return null;
          } catch (Exception e) {
            e.printStackTrace();
            return null;
          }
      } 

}

