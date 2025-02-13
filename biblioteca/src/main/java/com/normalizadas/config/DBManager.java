package com.normalizadas.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/library?charSet=UTF8";
    private static final String user = dotenv.get("DB_USER");
    private static final String password = dotenv.get("DB_PASSWORD");
    private static Connection conn;

    /**
     * Function name: getDbConnection
     *
     * @return Connection
     * Opens connection to database and returns a Connection object
     */
    public static Connection getDbConnection() {

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    /**
     * Function name: getDbConnection
     * Closes the connection
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
