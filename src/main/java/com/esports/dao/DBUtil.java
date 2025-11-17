package com.esports.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database utility class for managing database connections
 */
public class DBUtil {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;
    private static final String CONFIG_FILE = "/app.properties";

    static {
        loadConfiguration();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load database configuration from properties file
     */
    private static void loadConfiguration() {
        Properties props = new Properties();
        try (InputStream input = DBUtil.class.getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                props.load(input);
                dbUrl = props.getProperty("db.url", "jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC");
                dbUser = props.getProperty("db.user", "esports_user");
                dbPass = props.getProperty("db.pass", "Esports@2025");
            } else {
                // Fallback to defaults if file not found
                dbUrl = "jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC";
                dbUser = "esports_user";
                dbPass = "Esports@2025";
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Use defaults
            dbUrl = "jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC";
            dbUser = "esports_user";
            dbPass = "Esports@2025";
        }
    }

    /**
     * Get a database connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    /**
     * Close resources
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
