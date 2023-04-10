package edu.wpi.teamA.controllers.Navigation;

import edu.wpi.teamA.database.DAOImps.NodeDAOImp;
import edu.wpi.teamA.database.ORMclasses.Node;
import edu.wpi.teamA.pathfinding.AStar;
import edu.wpi.teamA.pathfinding.GraphNode;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import net.kurobako.gesturefx.GesturePane;

public class PathfindingController extends PageController {

  @FXML private MFXFilterComboBox<Integer> startSelection;
  @FXML private MFXFilterComboBox<Integer> endSelection;
  @FXML private Text directions;
  @FXML private MFXButton submitButton;
  private ArrayList<Integer> nodeIDOptions = new ArrayList<Integer>();
  private NodeDAOImp nodeDAO = new NodeDAOImp();
  @FXML private GesturePane gp;

  @FXML private ArrayList<Node> nodeList;

  @FXML private Pane topPane;

  @FXML
  private StackPane sp =
      new StackPane(new ImageView("edu/wpi/teamA/images/map-page/00_thelowerlevel1.png"), topPane);

  @Override
  public void initialize() {
    gp.setContent(sp);

    nodeList = nodeDAO.loadNodesFromDatabase();

    for (Node node : nodeList) {
      nodeIDOptions.add(node.getNodeID());
    }

    startSelection.setItems(FXCollections.observableArrayList(nodeIDOptions));
    endSelection.setItems(FXCollections.observableArrayList(nodeIDOptions));
  }

  public void submit() {
    try {
      AStar a = new AStar(startSelection.getSelectedItem(), endSelection.getSelectedItem());

      directions.setText(a.toString());
      System.out.println("Nodes submitted");

      drawPath(a);

    } catch (NullPointerException e) {
      System.out.println("Null Value");
      //System.out.println(pr);
    }
  }

  public void drawPath(AStar a) {
    ArrayList<Integer> nodePathIDs = a.getPath();

    for (Integer i: nodePathIDs) {
      GraphNode gNode = a.getGraphNode(i);

    }



  }
}
