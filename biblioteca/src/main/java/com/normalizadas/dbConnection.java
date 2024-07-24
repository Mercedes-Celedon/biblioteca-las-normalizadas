package com.normalizadas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
  /* to do database connection*/  
  
  


  public static void deleteBook(int id) {
    try {
      PreparedStatement stmn = conn.prepareStatement("DELETE FROM books WHERE id = ?");
      stmn.setInt(1, id);

      int row = stmn.executeUpdate();

      if ( row == 0) {
        System.out.println("No se ha podido borrar el registro del libro con id: " + id);
      } else {
        System.out.println("Libro con id " + id + " se ha borrado correctamente.");
      }

      
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
  
}
