package ap.restaurant.restaurant.controller;

import ap.restaurant.restaurant.dao.MenuItemDao;
import ap.restaurant.restaurant.dao.OrderDao;
import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.model.Order;
import ap.restaurant.restaurant.model.OrderDetails;
import ap.restaurant.restaurant.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderInListController {
    @FXML
    private Label priceLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Button orderAgainButton;
    @FXML
    private ListView<String> orderDetailsListView;
    @FXML
    private Label statusLabel;

    private Order orderRef;

    public Order getOrderRef() { return orderRef; }

    public void setOrderRef(Order orderRef) {
        this.orderRef = orderRef;
        updateUI();
    }

    public void updateUI() {
        orderDetailsListView.getItems().clear();
//        System.out.println(orderRef.getId());
//        System.out.println(orderRef.getTotalPrice());
//        System.out.println(orderRef.getCreatedAt());
        List<OrderDetails> orderDetails = OrderDao.getOrderDetails(orderRef.getId());
//        if (orderDetails.isEmpty()) {
//            System.out.println("No order details found");
//        }
        for (OrderDetails orderDetail : orderDetails) {
            String name = Objects.requireNonNull(MenuItemDao.getById(orderDetail.getMenuItemId())).getName();
            orderDetailsListView.getItems().add(orderDetail.getQuantity() + "X " + name);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dateLabel.setText(sdf.format(orderRef.getCreatedAt()));
            priceLabel.setText(orderRef.getTotalPrice() + "$");
            statusLabel.setText(orderRef.getStatus().name());
        }
    }


    @FXML
    public void orderAgainButtonClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            User user = UserDao.findUserById(orderRef.getUserId());
            if (user != null) {
                List<OrderDetails> orderDetails = new ArrayList<>();
                for (OrderDetails orderDetail : orderRef.getOrderDetails()) {

                    if (Objects.requireNonNull(MenuItemDao.getById(orderDetail.getMenuItemId())).getQuantity() > orderDetail.getQuantity()) {
                        orderDetails.add(new OrderDetails(Objects.requireNonNull(MenuItemDao.getById(orderDetail.getMenuItemId())), orderDetail.getQuantity()));
                    }
                    else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Error");
                        alert1.setHeaderText(null);
                        alert1.setContentText("not enough material for this order");
                        alert1.showAndWait();
                        return;
                    }
//                    System.out.println("quantity : " + orderDetail.getQuantity());
                }
                user.order(orderDetails);
            }
        }
    }
}
