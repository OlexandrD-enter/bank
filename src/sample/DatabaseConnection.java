package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mysql://bpv93sgjlzomygsxshmo-mysql.services.clever-cloud.com:3306/bpv93sgjlzomygsxshmo?allowPublicKeyRetrieval=True&sslMode=DISABLED&serverTimezone=UTC";
    private static final String USERNAME = "ucuza3zzqlto6cru";
    private static final String PASSWORD = "ARWPw1VqD9hU1coMMPgv";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Failed to close the connection: " + e.getMessage());
            }
        }
    }
}
