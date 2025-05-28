package ap.restaurant.restaurant.dao;

import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class MenuItemDao {
    public static void insert(MenuItem menuItem) {
        String query = "INSERT INTO menuItems (id , name , description , price , category) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, menuItem.getId());
            ps.setString(2, menuItem.getName());
            ps.setString(3, menuItem.getDescription());
            ps.setDouble(4, menuItem.getPrice());
            ps.setString(5, menuItem.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delete(MenuItem menuItem) {
        String query = "DELETE FROM menuItemTable WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, menuItem.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(MenuItem menuItem) {
        String query = "UPDATE FROM menuItemTable SET name = ?, description = ?, price = ?, category = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, menuItem.getName());
            ps.setString(2, menuItem.getDescription());
            ps.setDouble(3, menuItem.getPrice());
            ps.setString(4, menuItem.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
