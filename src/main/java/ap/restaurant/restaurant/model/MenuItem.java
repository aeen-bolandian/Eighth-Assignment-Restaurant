package ap.restaurant.restaurant.model;

import java.util.UUID;

public class MenuItem {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private double rating;
    private int choiceNums;
    private String category;
    private int quantity;
    // constructor for searching item
    public MenuItem(String name , double price, String description, double rating, int choiceNums, String category, int quantity , UUID id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.choiceNums = choiceNums;
        this.category = category;
        this.quantity = quantity;
        this.id = id;
    }
    // constructor for creating item
    public MenuItem(String name , double price , String description , String category , int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.choiceNums = 0;
        this.quantity = quantity;
    }

    // Getters :
    // --------------------------------
    public int getChoiceNums() {
        return choiceNums;
    }

    public String getDescription() {
        return description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getCategory() { return category; }

    public int getQuantity() { return quantity; }
    // --------------------------------
    // Setters :
    // --------------------------------
    public void setCategory(String category) {
        this.category = category;
    }

    public void setChoiceNums(int choiceNums) {
        this.choiceNums = choiceNums;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    // --------------------------------
}
