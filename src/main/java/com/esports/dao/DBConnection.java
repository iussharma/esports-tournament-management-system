package com.esports.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Lightweight DBConnection helper reading credentials from classpath /app.properties
 * Safe to use from DAOs. This complements the older DBUtil class.
 */
public class DBConnection {
    private static final String CONFIG_FILE = "/app.properties";
    private static String dbUrl = "jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC";
    private static String dbUser = "esports_user";
    private static String dbPass = "Esports@2025";

    static {
        loadConfig();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Driver not found; let DriverManager throw later
            e.printStackTrace();
        }
    }

    private static void loadConfig() {
        Properties p = new Properties();
        try (InputStream in = DBConnection.class.getResourceAsStream(CONFIG_FILE)) {
            if (in != null) {
                p.load(in);
                dbUrl = p.getProperty("db.url", dbUrl);
                dbUser = p.getProperty("db.user", dbUser);
                dbPass = p.getProperty("db.pass", dbPass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    public static void closeQuietly(Connection c) {
        if (c != null) {
            try { c.close(); } catch (SQLException ignored) {}
        }
    }
}
