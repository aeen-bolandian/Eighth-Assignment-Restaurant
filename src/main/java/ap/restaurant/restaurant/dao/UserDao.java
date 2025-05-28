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
        String query = "INSERT INTO users (id , name , password , email) VALUES (?,?,?,?)";
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1 , user.getId());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
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
}