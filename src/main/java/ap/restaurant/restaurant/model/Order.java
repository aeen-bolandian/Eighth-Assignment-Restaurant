package ap.restaurant.restaurant.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    public enum Status {Pending , Canceled , Completed}
    private Status status;

    private UUID id;
    private UUID userId;
    private Timestamp createdAt;
    private double totalPrice = 0.0;
    private List<OrderDetails> orderDetails;

    // search constructor
    public Order(List<OrderDetails> orderDetails , UUID userID , UUID id , Status status) {
        this.orderDetails = orderDetails;
        this.userId = userID;
        this.status = status;
    }
    // create constructor
    public Order(List<OrderDetails> orderDetails , User user) {
        this.id = UUID.randomUUID();
        this.userId = user.getId();
        this.createdAt = new Timestamp(new Date().getTime());
        this.status = Status.Pending;
        this.orderDetails = orderDetails;
        for (OrderDetails orderDetail : orderDetails) {
            totalPrice += orderDetail.getPrice();
        }
    }

    // Getters :
    // --------------------------------
    public Timestamp getCreatedAt() {
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

    public List<OrderDetails> getOrderDetails() { return orderDetails; }

    public Status getStatus() { return status; }
    // --------------------------------
    // Setters :
    // --------------------------------
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    // --------------------------------
}
