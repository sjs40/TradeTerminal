package model.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.notes.Note;

public class H2Database {

  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_CONNECTION = "jdbc:h2:~/TradeTerminal";
  private static final String DB_USER = "";
  private static final String DB_PASSWORD = "";

  private Connection conn;

  public Connection getConn() {
    try {
      Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      if (conn != null) {
        return conn;
      }
      conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
      return conn;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public void createTables() {
    conn = getConn();
    String note = "CREATE TABLE note (" +
            "noteID INT NOT NULL AUTO_INCREMENT," +
            "title VARCHAR(50) NOT NULL," +
            "dateCreated DATE NOT NULL," +
            "dateUpdated DATE NOT NULL," +
            "PRIMARY KEY (noteID)" +
            ");";

    String noteText = "CREATE TABLE noteText (" +
            "noteID INT NOT NULL," +
            "text LONGTEXT NOT NULL," +
            "FOREIGN KEY (noteID) REFERENCES note (noteID)" +
            "ON DELETE CASCADE ON UPDATE CASCADE" +
            ");";

    String portfolio = "CREATE TABLE portfolio (" +
            "positionID INT PRIMARY KEY," +
            "ticker VARCHAR(7) NOT NULL," +
            "date DATE NOT NULL," +
            "price DECIMAL(7,2) NOT NULL," +
            "amount INT NOT NULL" +
            ");";

    try {
      conn.setAutoCommit(false);

      PreparedStatement notePrep = conn.prepareStatement(note);
      notePrep.executeUpdate();
      notePrep.close();

      PreparedStatement noteTextPrep = conn.prepareStatement(noteText);
      noteTextPrep.executeUpdate();
      noteTextPrep.close();

      PreparedStatement portfolioPrep = conn.prepareStatement(portfolio);
      portfolioPrep.executeUpdate();
      portfolioPrep.close();

      conn.commit();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public ArrayList<Position> getAllPositions() {
    conn = getConn();
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        int positonID = rs.getInt("positionid");
        String ticker = rs.getString("ticker");
        Date date = rs.getDate("date");
        double price = rs.getDouble("price");
        int amount = rs.getInt("amount");
        Position position = new Position(positonID, ticker, date, price, amount);
        positions.add(position);
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public ArrayList<Position> getPositionsOfTicker(String ticker) {
    conn = getConn();
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        String dbTicker = rs.getString("ticker");
        if (dbTicker.toUpperCase().equals(ticker.toUpperCase())) {
          int positionID = rs.getInt("positionID");
          Date date = rs.getDate("date");
          double price = rs.getDouble("price");
          int amount = rs.getInt("amount");
          Position position = new Position(positionID, dbTicker, date, price, amount);
          positions.add(position);
        }
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public ArrayList<Position> getPositionOfDate(Date date) {
    conn = getConn();
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        Date dbDate = rs.getDate("date");
        if (dbDate.toString().equals(date.toString())) {
          int positionID = rs.getInt("positionid");
          String ticker = rs.getString("ticker");
          double price = rs.getDouble("price");
          int amount = rs.getInt("amount");
          Position position = new Position(positionID, ticker, dbDate, price, amount);
          positions.add(position);
        }
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public void addPosition(int positionID, String ticker, Date date, double price, int amount) {
    conn = getConn();
    try {
      String insert = "INSERT INTO portfolio (positionid, ticker, date, price, amount)" +
              " values (?, ?, ?, ?, ?)";
      PreparedStatement pst = conn.prepareStatement(insert);
      pst.setInt(1, positionID);
      pst.setString(2, ticker);
      pst.setDate(3, date);
      pst.setDouble(4, price);
      pst.setInt(5, amount);
      pst.execute();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void deletePosition(int positionID) {
    conn = getConn();
    try {
      String delete = "DELETE FROM portfolio WHERE positionid=?";
      PreparedStatement pst = conn.prepareStatement(delete);
      pst.setInt(1, positionID);
      pst.execute();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public ArrayList<Note> getAllNoteDetails() {
    conn = getConn();
    String query = "SELECT * FROM note;";
    ArrayList<Note> noteDetails = new ArrayList<>();
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        int noteID = rs.getInt("noteID");
        String title = rs.getString("title");
        LocalDate dateCreated = rs.getDate("dateCreated").toLocalDate();
        LocalDate dateUpdated = rs.getDate("dateUpdated").toLocalDate();
        Note note = new Note(noteID);
        note.setTitle(title);
        note.setDateCreated(dateCreated);
        note.setDateUpdated(dateUpdated);
        noteDetails.add(note);
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return noteDetails;
  }

  public ArrayList<Note> getAllNoteTexts() {
    conn = getConn();
    String query = "SELECT * FROM noteText;";
    ArrayList<Note> noteTexts = new ArrayList<>();
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        int noteID = rs.getInt("noteID");
        String text = rs.getString("text");
        Note note = new Note(noteID);
        note.setText(text);
        noteTexts.add(note);
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return noteTexts;
  }

  public Note getNoteTextByID(int noteID) {
    conn = getConn();
    try {
      String query = "SELECT * FROM noteText WHERE noteID=?;";
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setInt(1, noteID);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        Note note = new Note(noteID);
        note.setText(rs.getString("text"));
        return note;
      }
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return new Note();
  }

  public void addNote(String title, String text) {
    conn = getConn();
    String queryDetails = "INSERT INTO note (title, dateCreated, dateUpdated) VALUES (?, ?, ?);";
    String queryGetID = "SELECT noteID FROM note;";
    String queryText = "INSERT INTO noteText (noteID, text) VALUES (?, ?);";
    try {
      PreparedStatement pstDetails = conn.prepareStatement(queryDetails);
      Date currentDate = Date.valueOf(LocalDate.now());
      pstDetails.setString(1, title);
      pstDetails.setDate(2, currentDate);
      pstDetails.setDate(3, currentDate);
      pstDetails.execute();

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(queryGetID);
      rs.last();
      int noteID = rs.getInt("noteID");

      PreparedStatement pstText = conn.prepareStatement(queryText);
      pstText.setInt(1, noteID);
      pstText.setString(2, text);
      pstText.execute();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void updateNote(int noteID, String title, String text) {
    conn = getConn();
    String queryDate = "UPDATE note set dateUpdated = ? WHERE noteID = ?;";
    String queryTitle = "UPDATE note set title = ? WHERE noteID = ?;";
    String queryText = "UPDATE noteText set text = ? WHERE noteID = ?;";
    try {
      PreparedStatement pstDetails = conn.prepareStatement(queryDate);
      Date currentDate = Date.valueOf(LocalDate.now());
      pstDetails.setDate(1, currentDate);
      pstDetails.setInt(2, noteID);
      pstDetails.executeUpdate();

      PreparedStatement pstTitle = conn.prepareStatement(queryTitle);
      pstTitle.setString(1, title);
      pstTitle.setInt(2, noteID);
      pstTitle.executeUpdate();

      PreparedStatement pstText = conn.prepareStatement(queryText);
      pstText.setString(1, text);
      pstText.setInt(2, noteID);
      pstText.executeUpdate();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void deleteNote(int noteID) {
    conn = getConn();
    try {
      String deleteText = "DELETE FROM noteText WHERE nodeID=?;";
      String deleteDetails = "DELETE FROM note WHERE nodeID=?;";
      PreparedStatement pstText = conn.prepareStatement(deleteText);
      pstText.setInt(1, noteID);
      pstText.execute();

      PreparedStatement pstDetails = conn.prepareStatement(deleteDetails);
      pstDetails.setInt(1, noteID);
      pstDetails.execute();
      conn.close();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(H2Database.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }
}
