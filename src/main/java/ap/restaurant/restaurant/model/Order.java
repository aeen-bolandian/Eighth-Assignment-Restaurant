package ap.restaurant.restaurant.model;

import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID userId;
    private Date createdAt;
    private double totalPrice;

    // Getters :
    // --------------------------------

    public Date getCreatedAt() {
        return createdAt;
    }

    public UUID getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public UUID getUserId() {
        return userId;
    }
}
