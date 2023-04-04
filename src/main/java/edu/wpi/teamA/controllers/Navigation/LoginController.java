package edu.wpi.teamA.controllers.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
  @FXML private Button loginButton;

  @FXML private Button clearButton;
  @FXML private Label loginMessageLabel;
  @FXML private TextField usernameTextField;
  @FXML private PasswordField passwordTextField;

  public void loginButtonOnAction(ActionEvent e) {
    if (usernameTextField.getText().isBlank() == true) {
      loginMessageLabel.setText("Please enter username");
    } else if (passwordTextField.getText().isBlank() == true) {
      loginMessageLabel.setText("Please enter password");
    } else {
      loginMessageLabel.setText("You tried to log in");
    }
  }

  public void clearButtonOnAction(ActionEvent e) {
    Stage stage = (Stage) clearButton.getScene().getWindow();
    stage.close();
  }
}
