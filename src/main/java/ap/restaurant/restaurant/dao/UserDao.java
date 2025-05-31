package ap.restaurant.restaurant.dao;

import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.Order;
import ap.restaurant.restaurant.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao {
    public static void insert(User user) {
        String query = "INSERT INTO users (id , name , password , email , loggedIn) VALUES (?,?,?,?,?) ON CONFLICT (name) DO NOTHING";
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1 , user.getId());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setBoolean(5,user.isLoggedin());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void update(User user) {
        String query = "UPDATE users SET name = ?, password = ?, email = ?, loggedIn = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setObject(4, user.getId());
            ps.setBoolean(5, user.isLoggedin());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static List<Order> getUserOrders(User user) {
        String query = "SELECT * FROM orders WHERE userId = ?";
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1 , user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(OrderDao.getOrderDetails((UUID) rs.getObject("id")) , user.getId() , (UUID) rs.getObject("id") , Order.Status.valueOf(rs.getString("status")) );
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public static User findUserByName(String username) {
        String query = "SELECT * FROM users WHERE name = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name") , rs.getString("password") , rs.getString("email") , (UUID) rs.getObject("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}