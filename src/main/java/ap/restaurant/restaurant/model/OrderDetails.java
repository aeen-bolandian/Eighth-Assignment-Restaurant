package ap.restaurant.restaurant.model;

import java.util.UUID;

public class OrderDetails {
    private UUID id;
    private UUID orderId;
    private UUID menuItemId;
    private int quantity;
    private double price;

    // search constructor
    public OrderDetails(UUID order , UUID menuItem , int quantity , double price , UUID id ) {
        this.id = id;
        this.orderId = order;
        this.menuItemId = menuItem;
        this.quantity = quantity;
        this.price = price;
    }
    // create constructor
    public OrderDetails(MenuItem menuItem, int quantity, double price) {
        this.id = UUID.randomUUID();
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
    // Setters :
    // --------------------------------
    public void setId(UUID id) {
        this.id = id;
    }

    public void setMenuItemId(UUID menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // --------------------------------
}
