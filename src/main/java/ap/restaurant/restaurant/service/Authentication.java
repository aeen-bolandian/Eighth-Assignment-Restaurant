package ap.restaurant.restaurant.service;

import ap.restaurant.restaurant.dao.UserDao;
import ap.restaurant.restaurant.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class Authentication {
    public static void signUp(String username , String password , String email) {
        User user = new User(username , hashPassword(password) , email);
        UserDao.insert(user);
    }
    public static void logIn(String username, String password) {
        User user = UserDao.findUserByName(username);
        if (user != null) {
            if (checkPassword(password, user.getPassword())) {
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
        UserDao.update(user);
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // strength 12
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
