package com.birthdayreminder.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Simple DB utility to obtain a JDBC connection.
 * Priority for configuration:
 * 1) Environment variables: DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD
 * 2) src/main/resources/db.properties (db.host, db.port, ...)
 * 3) Defaults: localhost, 3306, birthday_db, root, empty password
 */
public class DBUtil {
    private static final String PROPS = "/db.properties";

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        // Try loading properties file but do not fail if missing
        try (InputStream in = DBUtil.class.getResourceAsStream(PROPS)) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            System.err.println("Warning: could not load db.properties: " + e.getMessage());
        }

        String host = getenvOrProp("DB_HOST", "db.host", props, "localhost");
        String port = getenvOrProp("DB_PORT", "db.port", props, "3306");
        String name = getenvOrProp("DB_NAME", "db.name", props, "birthday_db");
        String user = getenvOrProp("DB_USER", "db.user", props, "root");
        String password = getenvOrProp("DB_PASSWORD", "db.password", props, "");

        String url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", host, port, name);

        try {
            // Optional: ensure driver class is available (no-op if driver auto-registers)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ignored) {
            }

            return DriverManager.getConnection(url, user, "shihab@123");
        } catch (SQLException e) {
            String msg = String.format("Cannot connect to database '%s' at %s using user '%s'. Check that MySQL is running and credentials are correct. Original error: %s",
                    name, url, user, e.getMessage());
            throw new SQLException(msg, e);
        }
    }

    private static String getenvOrProp(String envName, String propName, Properties props, String defaultVal) {
        String v = System.getenv(envName);
        if (v != null && !v.isEmpty()) return v;
        v = props.getProperty(propName);
        if (v != null && !v.isEmpty()) return v;
        return defaultVal;
    }
}
