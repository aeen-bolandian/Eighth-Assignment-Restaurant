package ap.restaurant.restaurant.dao;

import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.Order;
import ap.restaurant.restaurant.model.OrderDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDao {

    public static void insert(Order order) {
        String query = "INSERT INTO orders (id , userId , createdAt , status , totalPrice) VALUES (?, ?, ?, ? , ?)";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, order.getId());
            ps.setObject(2, order.getUserId());
            ps.setTimestamp(3, order.getCreatedAt());
            ps.setString(4, order.getStatus().name());
            ps.setDouble(5, order.getTotalPrice());
            ps.executeUpdate();

            for (OrderDetails od : order.getOrderDetails()) {
                od.setOrderId(order.getId());
                OrderDetailsDao.insert(od);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(Order order) {
        String query = "UPDATE orders SET userId = ?, createdAt = ?, status = ?, totalPrice = ? WHERE id = ?";
        try(Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, order.getUserId());
            ps.setTimestamp(2, order.getCreatedAt());
            ps.setString(3, order.getStatus().name());
            ps.setDouble(4, order.getTotalPrice());
            ps.setObject(5, order.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(Order order) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<OrderDetails> getOrderDetails(UUID orderId) {
        String query = "SELECT * FROM orderDetails WHERE orderId = ?";
        List<OrderDetails> orderDetails = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetails1 = new OrderDetails(orderId , (UUID) rs.getObject("menuItemId") , rs.getInt("quantity") , rs.getDouble("price") , (UUID) rs.getObject("id"));
                orderDetails.add(orderDetails1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderDetails;
    }
}
