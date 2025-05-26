package ap.restaurant.restaurant.model;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private List<String> feedbacks;
    private List<Order> orders;

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
}
