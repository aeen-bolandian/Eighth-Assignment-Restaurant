package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.service.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button signInButton;

    @FXML
    private Button moveToSignUp;

    @FXML
    public void signInButtonClicked(ActionEvent event) throws IOException {
        Authentication.logIn(username.getText(), password.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        MenuController controller = fxmlLoader.getController();
        controller.setCurrentUser(UserDao.findUserByName(username.getText()));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void moveToSignUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/sign-up.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) signInButton.getScene().getWindow();
            currentStage.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
