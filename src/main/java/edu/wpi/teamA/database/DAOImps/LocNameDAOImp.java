package edu.wpi.teamA.database.DAOImps;

import edu.wpi.teamA.database.Connection.DBConnectionProvider;
import edu.wpi.teamA.database.Interfaces.ILocNameDAO;
import edu.wpi.teamA.database.ORMclasses.LocationName;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LocNameDAOImp implements IDataBase, ILocNameDAO {

  ArrayList<LocationName> LocNameArray = new ArrayList<LocationName>();

  static DBConnectionProvider LocNameProvider = new DBConnectionProvider();

  public LocNameDAOImp(Connection nodeConnection) {
    this.LocNameArray = LocNameArray;
  }

  public LocNameDAOImp() {
    this.LocNameArray = new ArrayList<LocationName>();
  }

  public static void createSchema() {
    try {
      Statement stmtSchema = LocNameProvider.createConnection().createStatement();
      String sqlCreateSchema = "CREATE SCHEMA IF NOT EXISTS \"Prototype2_schema\"";
      stmtSchema.execute(sqlCreateSchema);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static Connection createConnection() {
    String url = "jdbc:postgresql://database.cs.wpi.edu:5432/teamadb";
    String user = "teama";
    String password = "teama10";

    try {
      return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ArrayList<LocationName> loadLocNamesFromCSV(String filePath) {
    ArrayList<LocationName> locationNames = new ArrayList<>();

    try {
      BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
      csvReader.readLine(); // Skip the header line
      String row;

      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");

        String longName = data[0];
        String shortName = data[1];
        String nodeType = data[2];
        LocationName locationName = new LocationName(longName, shortName, nodeType);
        locationNames.add(locationName);
      }

      csvReader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return locationNames;
  }

  public static ArrayList<LocationName> Import(String filePath) {
    LocNameDAOImp.createSchema();
    ArrayList<LocationName> LocNameArray = loadLocNamesFromCSV(filePath);

    try {
      BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
      csvReader.readLine();
      String row;

      String sqlCreateEdge =
          "Create Table if not exists \"Prototype2_schema\".\"LocationName\""
              + "(longName     Varchar(600),"
              + "shortName     Varchar(600),"
              + "nodeType      Varchar(600))";
      Statement stmtLocName = LocNameProvider.createConnection().createStatement();
      stmtLocName.execute(sqlCreateEdge);

      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");

        PreparedStatement ps =
            LocNameProvider.createConnection()
                .prepareStatement(
                    "INSERT INTO \"Prototype2_schema\".\"LocationName\" VALUES (?, ?, ?)");
        ps.setString(1, data[0]);
        ps.setString(2, data[1]);
        ps.setString(3, data[2]);
        ps.executeUpdate();
      }
      csvReader.close();
    } catch (SQLException | IOException e) {

      throw new RuntimeException(e);
    }
    return LocNameArray;
  }

  public static void Export(String filePath) {
    try {
      Statement st = LocNameProvider.createConnection().createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM \"Prototype2_schema\".\"LocationName\"");

      FileWriter csvWriter = new FileWriter("LocationName.csv");
      csvWriter.append("longName,shortName,nodeType\n");

      while (rs.next()) {
        csvWriter.append(rs.getString("longName")).append(",");
        csvWriter.append(rs.getString("shortName")).append(",");
        csvWriter.append(rs.getString("nodeType")).append("\n");
      }

      csvWriter.flush();
      csvWriter.close();

      System.out.println("Location Name table exported to LocationName.csv");

    } catch (SQLException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<LocationName> loadLocNamefromDatabase() {
    ArrayList<LocationName> locationNames = new ArrayList<>();

    try {
      Statement st = LocNameProvider.createConnection().createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM \"Prototype2_schema\".\"LocationName\"");

      while (rs.next()) {
        String longName = rs.getString("longName");
        String shortName = rs.getString("shortName");
        String nodeType = rs.getString("nodeType");

        LocationName locationName = new LocationName(longName, shortName, nodeType);
        locationNames.add(locationName);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return locationNames;
  }

  public void Add() {
    try {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter longName, shortName, and nodeType:");
      String longName = input.nextLine();
      String shortName = input.nextLine();
      String nodeType = input.nextLine();

      PreparedStatement ps =
              LocNameProvider.createConnection()
                      .prepareStatement("INSERT INTO \"Prototype2_schema\".\"LocationName\" VALUES (?, ?, ?)");
      ps.setString(1, longName);
      ps.setString(2, shortName);
      ps.setString(3, nodeType);
      ps.executeUpdate();

      LocNameArray.add(new LocationName(longName, shortName, nodeType));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void Delete() {
    try {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the longName of the LocationName to delete:");
      String longName = input.nextLine();

      PreparedStatement ps =
              LocNameProvider.createConnection()
                      .prepareStatement("DELETE FROM \"Prototype2_schema\".\"LocationName\" WHERE longName = ?");
      ps.setString(1, longName);
      ps.executeUpdate();

      LocNameArray.removeIf(LocationName -> LocationName.getLongName().equals(longName));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void Update() {
    try {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter old longName, new longName, new shortName, and new nodeType:");
      String oldLongName = input.nextLine();
      String newLongName = input.nextLine();
      String newShortName = input.nextLine();
      String newNodeType = input.nextLine();

      PreparedStatement ps =
              LocNameProvider.createConnection().prepareStatement(
                      "UPDATE \"Prototype2_schema\".\"LocationName\" SET longName = ?, shortName = ?, nodeType = ? WHERE longName = ?");
      ps.setString(1, newLongName);
      ps.setString(2, newShortName);
      ps.setString(3, newNodeType);
      ps.setString(4, oldLongName);
      ps.executeUpdate();

      LocNameArray.forEach(
              locationName -> {
                if (locationName.getLongName().equals(oldLongName)) {
                  locationName.setLongName(newLongName);
                  locationName.setShortName(newShortName);
                  locationName.setNodeType(newNodeType);
                }
              });

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

