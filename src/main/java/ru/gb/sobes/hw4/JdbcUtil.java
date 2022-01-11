package ru.gb.sobes.hw4;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static Connection connection;

    private JdbcUtil() {
    }

    static {
        String url = "jdbc:postgresql://localhost:5432/hw4";
        String user = "postgres";
        String pass = "postgres";
        String defaultSchema = "cinema";

        Flyway flyway = Flyway.configure()
                .dataSource(url.concat("?currentSchema=cinema"), user, pass)
                .load();
        flyway.migrate();

        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setSchema(defaultSchema);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
