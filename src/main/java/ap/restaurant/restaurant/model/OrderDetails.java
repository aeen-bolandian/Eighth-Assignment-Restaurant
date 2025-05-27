package ap.restaurant.restaurant.model;

import java.util.UUID;

public class OrderDetails {
    private UUID id;
    private UUID orderId;
    private UUID menuItemId;
    private int quantity;
    private double price;

    public OrderDetails(Order order , MenuItem menuItem, int quantity, double price) {
        this.id = UUID.randomUUID();
        this.orderId = order.getId();
        this.menuItemId = menuItem.getId();
        this.quantity = quantity;
        this.price = menuItem.getPrice() * quantity;
    }

    // Getters :
    // --------------------------------
    public UUID getId() {
        return id;
    }

    public UUID getMenuItemId() {
        return menuItemId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    // --------------------------------
}
