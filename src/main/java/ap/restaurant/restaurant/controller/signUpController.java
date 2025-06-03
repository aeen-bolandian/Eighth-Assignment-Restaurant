package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.service.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {
    @FXML
    private Button signUpButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField email;

    @FXML
    private Button moveToSignIn;

    @FXML
    public void signUpButtonClicked(ActionEvent event) throws IOException {
        if (UserDao.findUserByName(username.getText()) == null) {
            Authentication.signUp(username.getText(), password.getText(), email.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign Up");
            alert.setHeaderText(null);
            alert.setContentText("""
                     sign up successful\s
                    login to your account\s""");
            alert.showAndWait();
            moveToSignIn();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sign Up");
            alert.setHeaderText(null);
            alert.setContentText("Username already exists");
            alert.showAndWait();
        }
    }

    @FXML
    public void moveToSignIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sign In");
            stage.show();
            Stage currentStage = (Stage) moveToSignIn.getScene().getWindow();
            currentStage.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
