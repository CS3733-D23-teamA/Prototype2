package edu.wpi.teamA.controllers.Navigation;

import edu.wpi.teamA.database.DAOImps.FlowerDAOImpl;
import edu.wpi.teamA.database.ORMclasses.FlowerEntity;
import edu.wpi.teamA.navigation.Navigation;
import edu.wpi.teamA.navigation.Screen;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServiceRequestController extends PageController {
  private FlowerDAOImpl fdao = new FlowerDAOImpl();
  @FXML private TableView<FlowerEntity> flowerTable;
  @FXML private TableColumn<FlowerEntity, String> roomCol;
  @FXML private TableColumn<FlowerEntity, String> dateCol;
  @FXML private TableColumn<FlowerEntity, String> timeCol;
  @FXML private TableColumn<FlowerEntity, String> flowerCol;
  @FXML private TableColumn<FlowerEntity, String> commentCol;
  @FXML private TableColumn<FlowerEntity, String> statusCol;

  @FXML
  public void initialize() {
    displayFlowerRequests(fdao.getAllFlowers());
  }

  public void displayFlowerRequests(List<FlowerEntity> flowerEntityArrayList) {
    roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    flowerCol.setCellValueFactory(new PropertyValueFactory<>("flowerType"));
    commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
    statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

    flowerTable.setItems(FXCollections.observableArrayList(flowerEntityArrayList));
    flowerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }
}
