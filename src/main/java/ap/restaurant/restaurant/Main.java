package ap.restaurant.restaurant;

import ap.restaurant.restaurant.controller.MenuController;
import ap.restaurant.restaurant.dao.MenuItemDao;
import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.db.DatabaseManager;
import ap.restaurant.restaurant.model.MenuItem;
import ap.restaurant.restaurant.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.UUID;

public class Main extends Application {
    public void seedData() {
        // Users
        UserDao.insert(new User("admin", "admin", "adminpass"));
        UserDao.insert(new User("aeen", "admin", "adminpass"));
        UserDao.insert(new User("bolandian", "admin", "adminpass"));

// MenuItems
        MenuItemDao.insert(new MenuItem(
                "Margherita Pizza", 8.99, "Classic cheese and tomato pizza", "Pizza", 10));
        MenuItemDao.insert(new MenuItem(
                "Pepperoni Pizza", 10.99, "Pepperoni and cheese pizza", "Pizza", 8));
        MenuItemDao.insert(new MenuItem(
                "Veggie Burger", 7.50, "Burger with lettuce, tomato and cheese", "Burger", 15));
        MenuItemDao.insert(new MenuItem(
                "Chicken Sandwich", 6.99, "Grilled chicken with mayo", "Sandwich", 12));
        MenuItemDao.insert(new MenuItem(
                "Coke", 1.99, "Refreshing soft drink", "Drinks", 50));
    }

    @Override
    public void start(Stage stage) throws Exception {
        DatabaseManager.createTables();
        seedData();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ap/restaurant/restaurant/menu.fxml"));
            Parent root = fxmlLoader.load();
            MenuController controller = fxmlLoader.getController();
            controller.setCurrentUser(UserDao.findUserByName("admin"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
