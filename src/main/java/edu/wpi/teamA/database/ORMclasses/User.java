package edu.wpi.teamA.database.ORMclasses;

import lombok.Getter;
import lombok.Setter;

public class User {

  @Getter @Setter private int adminYes;

  @Getter @Setter private String userName;

  @Getter @Setter private String password;

  @Getter @Setter private String firstName;

  @Getter @Setter private String lastName;

  public User(int adminYes, String userName, String password, String firstName, String lastName) {
    this.adminYes = adminYes;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
