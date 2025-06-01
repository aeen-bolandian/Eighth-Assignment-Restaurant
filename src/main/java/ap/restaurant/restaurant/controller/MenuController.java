package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.dao.MenuItemDao;
import ap.restaurant.restaurant.dao.OrderDao;
import ap.restaurant.restaurant.dao.OrderDetailsDao;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.Order;
import ap.restaurant.restaurant.model.OrderDetails;
import ap.restaurant.restaurant.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button cancelButton;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private Button orderButton;

    @FXML
    private VBox menuItemVbox;

    private User currentUser;

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) { this.currentUser = user; }

    List<MenuItemController> menuItemControllers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<MenuItem> menuItems = MenuItemDao.getAll();

        for (MenuItem menuItem : menuItems) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/menu-item.fxml"));
                Parent menuItemNode = fxmlLoader.load();
                MenuItemController menuItemController = fxmlLoader.getController();
                menuItemControllers.add(menuItemController);
                menuItemController.setMenuItem(menuItem);
                menuItemVbox.getChildren().add(menuItemNode);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void openProfilePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/profile.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            profileController profileController = fxmlLoader.getController();
            profileController.setCurrentUser(getCurrentUser());
            stage.setTitle("Profile");
            stage.show();
            Stage currentStage = (Stage) cancelButton.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void order(ActionEvent event) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (MenuItemController menuItemController : menuItemControllers) {
            if (menuItemController.getOrderDetails().getQuantity() > 0)
                orderDetailsList.add(menuItemController.getOrderDetails());
        }
        if (!orderDetailsList.isEmpty()) {
            currentUser.order(orderDetailsList);
            openProfilePage();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("please at least choose an order");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancelButtonAction(ActionEvent event) {
        openProfilePage();
    }
}
