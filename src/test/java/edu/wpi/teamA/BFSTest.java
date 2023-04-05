package edu.wpi.teamA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.wpi.teamA.database.ORMclasses.Edge;
import edu.wpi.teamA.pathfinding.BFS;
import edu.wpi.teamA.pathfinding.Graph;
import edu.wpi.teamA.pathfinding.GraphNode;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BFSTest {
  Graph graph = new Graph();
  GraphNode nodeA = new GraphNode(11, 0, 0, "", "");
  GraphNode nodeB = new GraphNode(12, 0, 0, "", "");
  GraphNode nodeC = new GraphNode(13, 0, 0, "", "");
  GraphNode nodeD = new GraphNode(14, 0, 0, "", "");
  GraphNode nodeE = new GraphNode(15, 0, 0, "", "");

  @BeforeEach
  protected void setUp() throws Exception {
    System.out.println("Setting it up!");
    GraphNode nodeA = new GraphNode(11, 0, 0, "", "");
    GraphNode nodeB = new GraphNode(12, 0, 0, "", "");
    GraphNode nodeC = new GraphNode(13, 0, 0, "", "");
    GraphNode nodeD = new GraphNode(14, 0, 0, "", "");
    GraphNode nodeE = new GraphNode(15, 0, 0, "", "");
    GraphNode nodeF = new GraphNode(16, 0, 0, "", "");

    graph.addNodeToGraph(nodeA);
    graph.addNodeToGraph(nodeB);
    graph.addNodeToGraph(nodeC);
    graph.addNodeToGraph(nodeD);
    graph.addNodeToGraph(nodeE);
    Edge edgeAB = new Edge(nodeA.getNodeID(), nodeB.getNodeID());
    Edge edgeBC = new Edge(nodeB.getNodeID(), nodeC.getNodeID());
    Edge edgeCD = new Edge(nodeC.getNodeID(), nodeD.getNodeID());
    Edge edgeAE = new Edge(nodeA.getNodeID(), nodeE.getNodeID());
    Edge edgeDF = new Edge(nodeA.getNodeID(), nodeE.getNodeID());
    // Edge edgeAC = new Edge(nodeA.getNodeID(), nodeC.getNodeID());

    nodeA.addEdge(edgeAB);
    nodeB.addEdge(edgeAB);
    nodeB.addEdge(edgeBC);
    nodeC.addEdge(edgeBC);
    nodeA.addEdge(edgeAE);
    nodeE.addEdge(edgeAE);
    nodeC.addEdge(edgeCD);
    nodeD.addEdge(edgeCD);
    nodeF.addEdge(edgeDF);
    nodeD.addEdge(edgeDF);
    // nodeA.addEdge(edgeAC);
    // nodeC.addEdge(edgeAC);
  }

  @Test
  public void testSetPathBFS() {
    BFS bfs1 = new BFS(graph, 11, 13);
    ArrayList<Integer> expected_path = new ArrayList<Integer>();
    expected_path.add(11);
    expected_path.add(12);
    expected_path.add(13);
    ArrayList<Integer> actual_path = bfs1.setPath();
    assertEquals(expected_path, actual_path);

    BFS bfs2 = new BFS(graph, 13, 11);
    expected_path = new ArrayList<Integer>();
    expected_path.add(13);
    expected_path.add(12);
    expected_path.add(11);
    actual_path = bfs2.setPath();
    assertEquals(expected_path, actual_path);

    BFS bfs3 = new BFS(graph, 11, 15);
    expected_path = new ArrayList<Integer>();
    expected_path.add(11);
    expected_path.add(15);
    actual_path = bfs3.setPath();
    assertEquals(expected_path, actual_path);

    BFS bfs4 = new BFS(graph, 11, 16);
    expected_path = new ArrayList<Integer>();
    expected_path.add(11);
    expected_path.add(12);
    expected_path.add(13);
    expected_path.add(14);
    expected_path.add(16);
    actual_path = bfs4.setPath();
    assertEquals(expected_path, actual_path);
  }
}
