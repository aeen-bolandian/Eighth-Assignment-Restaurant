package ap.restaurant.restaurant.dao;

import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuItemDao {
    public static void insert(MenuItem menuItem) {
        String query = "INSERT INTO menuItems (id , name , quantity , description , price , category) VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (name) DO NOTHING";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, menuItem.getId());
            ps.setString(2, menuItem.getName());
            ps.setInt(3, menuItem.getQuantity());
            ps.setString(4, menuItem.getDescription());
            ps.setDouble(5, menuItem.getPrice());
            ps.setString(6, menuItem.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delete(MenuItem menuItem) {
        String query = "DELETE FROM menuItems WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, menuItem.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(MenuItem menuItem) {
        String query = "UPDATE menuItems SET name = ?, quantity = ?, description = ?, price = ?, category = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, menuItem.getName());
            ps.setInt(2, menuItem.getQuantity());
            ps.setString(3, menuItem.getDescription());
            ps.setDouble(4, menuItem.getPrice());
            ps.setString(5, menuItem.getCategory());
            ps.setObject(6, menuItem.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<MenuItem> getAll() {
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menuItems";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String desc = rs.getString("description");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                MenuItem item = new MenuItem(name, price, desc, category, quantity, id);
                menuItems.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return menuItems;
    }
}
