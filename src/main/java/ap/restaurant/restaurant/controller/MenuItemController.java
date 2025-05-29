package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.OrderDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuItemController {

    @FXML
    private Label menuItemName;

    @FXML
    private TextArea description;

    @FXML
    private Button menuItemOrderButton;

    @FXML
    private Button cancelMenuItemOrderButton;

    @FXML
    private Label choiceNumLabel;

    private MenuItem menuItemRef;

    private OrderDetails orderDetails;

    public OrderDetails getOrderDetails() { return orderDetails; }

    public MenuItem getMenuItemRef() { return menuItemRef; }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItemRef = menuItem;
        menuItemName.setText(menuItem.getName());
        description.setText(menuItem.getDescription());
        orderDetails = new OrderDetails(menuItem , 0 , menuItem.getPrice());
        choiceNumLabel.setText(String.valueOf(orderDetails.getQuantity()));
        menuItemOrderButton.setOnAction(event -> {
            orderDetails.setQuantity(menuItem.getQuantity() + 1);
            choiceNumLabel.setText(String.valueOf(orderDetails.getQuantity()));
        });
        cancelMenuItemOrderButton.setOnAction(event -> {
            orderDetails.setQuantity(menuItem.getQuantity() - 1);
            choiceNumLabel.setText(String.valueOf(orderDetails.getQuantity()));
        });
    }
}
