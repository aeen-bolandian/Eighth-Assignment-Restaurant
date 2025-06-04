package ap.restaurant.restaurant.dao;

import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDetailsDao {
    public static void insert(OrderDetails od) {
        String query = "INSERT INTO orderDetails VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, od.getId());
            ps.setObject(2, od.getOrderId());
            ps.setObject(3, od.getMenuItemId());
            ps.setInt(4, od.getQuantity());
            ps.setDouble(5, od.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        MenuItem item = MenuItemDao.getById(od.getMenuItemId());
        if (item != null) {
            item.setQuantity(item.getQuantity() - od.getQuantity());
            MenuItemDao.update(item);
        }
    }
    public static void update(OrderDetails od) throws SQLException {
        String query = "UPDATE orderDetails SET quantity = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, od.getQuantity());
            ps.setObject(2, od.getId());
            ps.executeUpdate();
        }
    }
    public static void delete(OrderDetails od) throws SQLException {
        String query = "DELETE FROM orderDetails WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, od.getId());
            ps.executeUpdate();
        }
    }
    public static List<MenuItem> getMenuItems(OrderDetails od) throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM orderDetails WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, od.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                menuItems.add(new MenuItem(rs.getString("name") , rs.getDouble("price") , rs.getString("description") , rs.getString("category") , rs.getInt("quantity") , (UUID) rs.getObject("id")));
            }
        }
        return menuItems;
    }
}
