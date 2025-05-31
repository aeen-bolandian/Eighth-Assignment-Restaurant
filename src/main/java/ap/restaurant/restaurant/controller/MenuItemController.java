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
    private Button orderMenuItemButton;

    @FXML
    private Button cancelOrderMenuItemButton;

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
        orderMenuItemButton.setOnAction(event -> {
            if (orderDetails.getQuantity() < menuItem.getQuantity()) {
                orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                choiceNumLabel.setText(String.valueOf(orderDetails.getQuantity()));
            }
            else {
                choiceNumLabel.setText("not enough material");
            }
        });
        cancelOrderMenuItemButton.setOnAction(event -> {
            if (orderDetails.getQuantity() > 0) {
                orderDetails.setQuantity(orderDetails.getQuantity() - 1);
                choiceNumLabel.setText(String.valueOf(orderDetails.getQuantity()));
            }
        });
    }
}
