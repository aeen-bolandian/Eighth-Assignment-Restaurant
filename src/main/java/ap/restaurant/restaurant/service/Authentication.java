package ap.restaurant.restaurant.service;

import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.model.User;

public class Authentication {
    public static void signUp(String username , String password , String email) {
        // add regex at the end
        User user = new User(username , password , email);
        UserDao.insert(user);
    }
    public static void logIn(String username, String password) {
        User user = UserDao.findUserByName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.setLoggedin(true);
                UserDao.update(user);
                System.out.println("logged in successfully");
            }
            else {
                System.out.println("wrong password");
            }
        }
        else {
            System.out.println("user not found");
        }
    }

    public static void logOut(User user) {
        user.setLoggedin(false);
    }
}
