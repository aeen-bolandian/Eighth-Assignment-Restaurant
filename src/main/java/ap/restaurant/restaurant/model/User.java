package ap.restaurant.restaurant.model;

import ap.restaurant.restaurant.dao.OrderDao;
import ap.restaurant.restaurant.dao.OrderDetailsDao;
import ap.restaurant.restaurant.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private boolean loggedin;
    private List<Order> orders = new ArrayList<>();
    // search constructor
    public User(String username , String password , String email , UUID id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }
    // create constructor
    public User(String username , String password , String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.loggedin = false;
    }

    // getters :
    // --------------------------------
    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedin() { return loggedin; }
    // --------------------------------
    // Setters :
    // --------------------------------
    public void setEmail(String email) {
        this.email = email;
        UserDao.update(this);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setPassword(String password) {
        this.password = password;
        UserDao.update(this);
    }

    public void setUsername(String username) {
        this.username = username;
        UserDao.update(this);
    }

    public void setLoggedin(boolean loggedin) { this.loggedin = loggedin; }
    // --------------------------------
    public void order(List<OrderDetails> orderDetails) {
        Order order = new Order(orderDetails , this);
        for (OrderDetails orderDetail : orderDetails) {
            orderDetail.setOrderId(order.getId());
            OrderDetailsDao.insert(orderDetail);
        }
        orders.add(order);
        OrderDao.insert(order);
    }

    public void cancelOrder(Order order) {
        orders.remove(order);
    }
}
