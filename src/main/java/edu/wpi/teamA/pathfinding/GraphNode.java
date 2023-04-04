package edu.wpi.teamA.pathfinding;
import edu.wpi.teamA.database.Edge;
import edu.wpi.teamA.database.Node;
import java.util.ArrayList;
public class GraphNode {
    int nodeID;
    int xcoord;
    int ycoord;
    String floor;
    String building;
    String longName;
    String shortName;
    String nodeType;
    ArrayList<Edge> edgeList; //change to Edge
    boolean visited;
    GraphNode prev;
    int gCost;
    int hCost;


    public GraphNode(int nodeID, int xcoord, int ycoord, String floor, String building, String longName, String shortName, String nodeType, ArrayList<Edge> edgeList, boolean visited, GraphNode prev) {
        this.nodeID = nodeID;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.floor = floor;
        this.building = building;
        this.longName = longName;
        this.shortName = shortName;
        this.nodeType = nodeType;
        this.edgeList = edgeList;
        this.visited = visited;
        this.prev = prev;

    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setEdgeList(ArrayList<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public GraphNode getPrev() {
        return prev;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return gCost+hCost;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void addEdge(Edge edge) {
        edgeList.add(edge);
    }

    public int edgeCount() {
        return edgeList.size();
    }

    public Edge getEdge(int index) {
        return edgeList.get(index);
    }


    //getters and setters for all attributes
    //get fCost: gCost+hCost
}
