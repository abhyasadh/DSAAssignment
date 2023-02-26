package Nine.database;

import javax.swing.*;
import java.sql.*;

public class DBConnection {
    public Connection connection;
    Statement statement;
    ResultSet resultSet;
    int val;

    public DBConnection(){
        try {

            String username = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task_management",username,null);

            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Via the use of sql query
    // insert, update and delete
    public int manipulate(String query){
        try {
            val = statement.executeUpdate(query);
            connection.close();
        }catch (SQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null, "These details already exist!");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return val;
    }

    public ResultSet retrieve(String query){
        try {
            resultSet = statement.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}