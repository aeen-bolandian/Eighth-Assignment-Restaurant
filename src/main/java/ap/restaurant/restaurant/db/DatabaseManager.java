package ap.restaurant.restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/Restaurant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL , USER , PASSWORD);
    }

    public static void createTables() throws SQLException {
        String userTable ="""
            CREATE TABLE IF NOT EXISTS users (
                id UUID PRIMARY KEY NOT NULL,
                name VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(200) NOT NULL,
                email VARCHAR(200) NOT NULL
                loggedIn BOOLEAN NOT NULL
                );
            """;
        String menuItemTable = """
                CREATE TABLE IF NOT EXISTS menuItems (
                id UUID PRIMARY KEY NOT NULL,
                name VARCHAR(50) UNIQUE NOT NULL,
                quantity INT NOT NULL,
                description TEXT,
                price DOUBLE PRECISION NOT NULL,
                category VARCHAR(50)
                );
                """;
        String orderTable = """
                CREATE TABLE IF NOT EXISTS orders (
                id UUID PRIMARY KEY NOT NULL,
                userId UUID NOT NULL REFERENCES users(id),
                createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                status TEXT NOT NULL,
                totalPrice DOUBLE PRECISION NOT NULL
                );
                """;
        String orderDetailTable = """
               CREATE TABLE IF NOT EXISTS orderDetails (
                id UUID PRIMARY KEY NOT NULL,
                orderId UUID NOT NULL REFERENCES orders(id),
                menuItemId UUID NOT NULL REFERENCES menuItems(id),
                quantity INTEGER NOT NULL,
                price DOUBLE PRECISION NOT NULL
                );
                """;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(menuItemTable);
            stmt.execute(orderTable);
            stmt.execute(orderDetailTable);

            System.out.println("all tables created successfully");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}