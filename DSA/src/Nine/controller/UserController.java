package Nine.controller;

import Nine.database.DBConnection;
import Nine.model.User;
import Nine.view.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    DBConnection db = new DBConnection();
    public int userRegister(User user){
        String query;
        query = "insert into user(first_name,last_name,phone,password)" +
                " values('" +
                user.getFName() + "','" +
                user.getLName() + "','" +
                user.getPhone() + "','" +
                user.getPassword() +"');";
        return db.manipulate(query);
    }

    public User loginUser(String phone, String password) {
        String query;
        query = "select id from user where phone = '" + phone +
                "' and password = '" + password + "';";
        ResultSet rs = db.retrieve(query);
        User user = null;

        try {
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id"));
                Login.USER_ID = user.getUserId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
