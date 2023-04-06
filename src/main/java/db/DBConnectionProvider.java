package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "Ashot2580456";
    private final static DBConnectionProvider INSTANCE = new DBConnectionProvider();
    private Connection connection;

    public   Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private DBConnectionProvider() {
    }

    public static DBConnectionProvider getInstance() {
        return INSTANCE;
    }


}
