package edu.wpi.teamA.controllers;

import edu.wpi.teamA.App;
import edu.wpi.teamA.navigation.Navigation;
import edu.wpi.teamA.navigation.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class HeaderController {

  @FXML private MenuButton menuButton;
  @FXML private MenuItem serviceRequestMenu;
  @FXML private MenuItem mapMenu;

  @FXML private MenuItem signageMenu;
  @FXML private MenuItem pathfindingMenu;
  @FXML private MenuItem exitMenu;
  @FXML private ImageView bwhLogo;
  @FXML private Circle profile;

  @FXML
  public void initialize() {
    // setPageTitle("New page title");
    serviceRequestMenu.setOnAction(event -> Navigation.navigate(Screen.SERVICE_REQUEST));
    mapMenu.setOnAction(event -> Navigation.navigate(Screen.MAP));
    signageMenu.setOnAction(event -> Navigation.navigate(Screen.SIGNAGE));
    pathfindingMenu.setOnAction(event -> Navigation.navigate(Screen.PATHFINDING));
    exitMenu.setOnAction(event -> App.getPrimaryStage().hide());
    bwhLogo.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
    // profile.setOnMouseClicked();
  }
}
