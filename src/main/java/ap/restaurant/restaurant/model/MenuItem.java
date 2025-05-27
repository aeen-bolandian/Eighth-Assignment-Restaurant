package ap.restaurant.restaurant.model;

import java.util.UUID;

public class MenuItem {
    private UUID id;
    private String name;
    private double price;
    private String description;
    private double rating;
    private int choiceNums;

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
    // --------------------------------
}
