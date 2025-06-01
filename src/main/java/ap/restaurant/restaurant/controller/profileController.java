package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class profileController implements Initializable {
    @FXML
    private Label welcomeTextField;
    @FXML
    private Button newOrderButton;
    @FXML
    private Button orderListButton;
    @FXML
    private Button logoutButton;

    private User currentUser;

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) {
        currentUser = user;
        System.out.println("Current user: " + currentUser.getUsername());
        welcomeTextField.setText("Welcome " + currentUser.getUsername());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentUser != null) {
            welcomeTextField.setText("Welcome " + currentUser.getUsername());
        }
    }

    @FXML
    public void newOrderButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/menu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New Order");
            Scene scene = new Scene(fxmlLoader.load());
            MenuController controller = fxmlLoader.getController();
            controller.setCurrentUser(currentUser);
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) welcomeTextField.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
