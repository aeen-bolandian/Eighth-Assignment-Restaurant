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

    public MenuItem(String name , double price , String description , String category) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.choiceNums = 0;
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
    // --------------------------------
}
