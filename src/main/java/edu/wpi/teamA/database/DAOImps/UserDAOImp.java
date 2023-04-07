package edu.wpi.teamA.database.DAOImps;

import edu.wpi.teamA.database.Connection.DBConnectionProvider;
import edu.wpi.teamA.database.ORMclasses.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAOImp {
  ArrayList<User> UserArray;

  static DBConnectionProvider UserLoginProvider = new DBConnectionProvider();

  public UserDAOImp() {
    this.UserArray = new ArrayList<User>();
  }

  // Create database table for User
  public void createUserTable() {
    try {
      Statement stmtUser = UserLoginProvider.createConnection().createStatement();
      String sqlCreateUser =
          "CREATE TABLE IF NOT EXISTS \"Prototype2_schema\".\"Users\" ("
              + "userName   VARCHAR(255) PRIMARY KEY,"
              + "password   VARCHAR(255),"
              + "firstName  VARCHAR(255),"
              + "lastName   VARCHAR(255))";
      stmtUser.execute(sqlCreateUser);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  // Check if the user exists. If exists, pull the password from the database and check if it fits
  // If not exist, call addUser
  public boolean checkUser(String userName, String password) {
    try {
      PreparedStatement ps =
          UserLoginProvider.createConnection()
              .prepareStatement("SELECT * FROM \"Prototype2_schema\".\"Users\" WHERE userName = ?");
      ps.setString(1, userName);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        String storedPassword = rs.getString("password");
        if (password.equals(storedPassword)) {
          return true;
        } else {
          return false;
        }
      } else {
        System.out.println("User not found, add new user.");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  // Add the new user into the database
  // Also store the user into the array
  public void addUser(String userName, String password, String firstName, String lastName) {
    try {

      PreparedStatement ps =
          UserLoginProvider.createConnection()
              .prepareStatement(
                  "INSERT INTO \"Prototype2_schema\".\"Users\" (userName, password, firstName, lastName) VALUES (?, ?, ?, ?)");
      ps.setString(1, userName);
      ps.setString(2, password);
      ps.setString(3, firstName);
      ps.setString(4, lastName);
      ps.executeUpdate();

      UserArray.add(new User(userName, password, firstName, lastName));
      System.out.println("New user added successfully.");

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
