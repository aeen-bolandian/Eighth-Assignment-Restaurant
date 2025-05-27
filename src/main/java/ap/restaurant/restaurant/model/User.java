package ap.restaurant.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private List<String> feedbacks;
    private List<Order> orders;

    public User(String username , String password , String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.feedbacks = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    // getters :
    // --------------------------------
    public String getEmail() {
        return email;
    }

    public List<String> getFeedbacks() {
        return feedbacks;
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
    // --------------------------------
    // Setters :
    // --------------------------------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedbacks(List<String> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    // --------------------------------
}
