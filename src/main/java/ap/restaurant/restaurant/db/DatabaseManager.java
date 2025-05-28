package ap.restaurant.restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL , USER , PASSWORD);
    }

    public static void createTables() throws SQLException {
        String userTable ="""
            CREATE TABLE IF NOT EXISTS users (
                id PRIMARY KEY DEFAULT UNIQUE NOT NULL,
                name VARCHAR(50) NOT NULL,
                password VARCHAR(200) NOT NULL,
                email VARCHAR(200) NOT NULL,
                );
            """;
        String menuItemTable = """
                CREATE TABLE IF NOT EXISTS menuItems (
                id PRIMARY KEY DEFAULT UNIQUE NOT NULL,
                name VARCHAR(50) NOT NULL,
                quantity INT NOT NULL,
                description TEXT,
                price DOUBLE NOT NULL,
                category VARCHAR(50)
                );
                """;
        String orderDetailTable = """
                CREATE TABLE IF NOT EXISTS orderDetails (
                id PRIMARY KEY DEFAULT UNIQUE NOT NULL,
                orderId PRIMARY KEY DEFAULT NOT NULL REFERENCES order(id),
                menuItemId PRIMARY KEY DEFAULT NOT NULL REFERENCES menuItems(id),
                quantity INTEGER NOT NULL,
                price DOUBLE NOT NULL,
                );
                """;
        String orderTable = """
                CREATE TABLE IF NOT EXISTS orders (
                id PRIMARY KEY DEFAULT UNIQUE NOT NULL,
                userId PRIMARY KEY DEFAULT NOT NULL REFERENCES user(id),
                createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                status TEXT NOT NULL,
                totalPrice DOUBLE NOT NULL
                );
                """;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(menuItemTable);
            stmt.execute(orderDetailTable);
            stmt.execute(orderTable);

            System.out.println("all tables created successfully");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}