package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.model.Order;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewOrdersController {
    @FXML
    private ScrollPane orderListScrollPane;
    @FXML
    private Button backButton;
    @FXML
    private VBox orderListVBox;

    List<OrderInListController> orderInListControllers = new ArrayList<>();

    private User currentUser;

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        UpdateUI();
    }

    @FXML
    public void backButtonClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/profile.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            profileController profileController = fxmlLoader.getController();
            profileController.setCurrentUser(getCurrentUser());
            stage.setTitle("Profile");
            stage.show();
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateUI() {
        orderListVBox.getChildren().clear();
        List<Order> orders = UserDao.getUserOrders(currentUser);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/order-in-list.fxml"));
                    Parent root = fxmlLoader.load();
                    OrderInListController orderInListController = fxmlLoader.getController();
                    orderInListControllers.add(orderInListController);
                    orderInListController.setOrderRef(order);
                    orderListVBox.getChildren().add(root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No orders found");
            alert.showAndWait();
        }
    }
}
