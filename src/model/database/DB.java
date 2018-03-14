package model.database;

import sun.rmi.runtime.Log;

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

import javafx.geometry.Pos;
import key.Props;
import model.database.notes.Note;

public class DB {

  private String url = "jdbc:mysql://localhost:3306/TradeTerminal?useSSL=false";
  private String user = Props.getUser();
  private String password = Props.getPassword();
  private Connection con;
  private Statement st;

  public DB() {
    try {
      con = DriverManager.getConnection(url, user, password);
      st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void setUpDatabase() {
    String query = "DROP TABLE IF EXISTS portfolio;\n" +
            "\n" +
            "CREATE TABLE portfolio (\n" +
            "  positionid INT PRIMARY KEY,\n" +
            "  ticker VARCHAR(7) NOT NULL,\n" +
            "  date DATE NOT NULL,\n" +
            "  price DECIMAL(7, 2) NOT NULL,\n" +
            "  current_price DECIMAL(7, 2) NOT NULL,\n" +
            "  amount INT NOT NULL\n" +
            ");";
    try {
      st.executeQuery(query);
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public ArrayList<Position> getAllPositions() {
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
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
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public ArrayList<Position> getPositionsOfTicker(String ticker) {
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
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
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public ArrayList<Position> getPositionOfDate(Date date) {
    String query = "SELECT * FROM portfolio;";
    ArrayList<Position> positions = new ArrayList<>();
    try {
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
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return positions;
  }

  public void addPosition(int positionID, String ticker, Date date, double price, int amount) {
    try {
      String insert = "INSERT INTO portfolio (positionid, ticker, date, price, amount)" +
              " values (?, ?, ?, ?, ?)";
      PreparedStatement pst = con.prepareStatement(insert);
      pst.setInt(1, positionID);
      pst.setString(2, ticker);
      pst.setDate(3, date);
      pst.setDouble(4, price);
      pst.setInt(5, amount);
      pst.execute();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void deletePosition(int positionID) {
    try {
      String delete = "DELETE FROM portfolio WHERE positionid=?";
      PreparedStatement pst = con.prepareStatement(delete);
      pst.setInt(1, positionID);
      pst.execute();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public ArrayList<Note> getAllNoteDetails() {
    String query = "SELECT * FROM note;";
    ArrayList<Note> noteDetails = new ArrayList<>();
    try {
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
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return noteDetails;
  }

  public ArrayList<Note> getAllNoteTexts() {
    String query = "SELECT * FROM noteText;";
    ArrayList<Note> noteTexts = new ArrayList<>();
    try {
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        int noteID = rs.getInt("noteID");
        String text = rs.getString("text");
        Note note = new Note(noteID);
        note.setText(text);
        noteTexts.add(note);
      }
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return noteTexts;
  }

  public Note getNoteTextByID(int noteID) {
    try {
      String query = "SELECT * FROM noteText WHERE noteID=?;";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1, noteID);
      ResultSet rs = pst.executeQuery();
      while (rs.next()) {
        Note note = new Note(noteID);
        note.setText(rs.getString("text"));
        return note;
      }
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
    return new Note();
  }

  public void addNote(String title, String text) {
    String queryDetails = "INSERT INTO note (title, dateCreated, dateUpdated) VALUES (?, ?, ?);";
    String queryGetID = "SELECT noteID FROM note;";
    String queryText = "INSERT INTO noteText (noteID, text) VALUES (?, ?);";
    try {
      PreparedStatement pstDetails = con.prepareStatement(queryDetails);
      Date currentDate = Date.valueOf(LocalDate.now());
      pstDetails.setString(1, title);
      pstDetails.setDate(2, currentDate);
      pstDetails.setDate(3, currentDate);
      pstDetails.execute();

      ResultSet rs = st.executeQuery(queryGetID);
      rs.last();
      int noteID = rs.getInt("noteID");

      PreparedStatement pstText = con.prepareStatement(queryText);
      pstText.setInt(1, noteID);
      pstText.setString(2, text);
      pstText.execute();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void updateNote(int noteID, String title, String text) {
    String queryDate = "UPDATE note set dateUpdated = ? WHERE noteID = ?;";
    String queryTitle = "UPDATE note set title = ? WHERE noteID = ?;";
    String queryText = "UPDATE noteText set text = ? WHERE noteID = ?;";
    try {
      PreparedStatement pstDetails = con.prepareStatement(queryDate);
      Date currentDate = Date.valueOf(LocalDate.now());
      pstDetails.setDate(1, currentDate);
      pstDetails.setInt(2, noteID);
      pstDetails.executeUpdate();

      PreparedStatement pstTitle = con.prepareStatement(queryTitle);
      pstTitle.setString(1, title);
      pstTitle.setInt(2, noteID);
      pstTitle.executeUpdate();

      PreparedStatement pstText = con.prepareStatement(queryText);
      pstText.setString(1, text);
      pstText.setInt(2, noteID);
      pstText.executeUpdate();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

  public void deleteNote(int noteID) {
    try {
      String deleteText = "DELETE FROM noteText WHERE nodeID=?;";
      String deleteDetails = "DELETE FROM note WHERE nodeID=?;";
      PreparedStatement pstText = con.prepareStatement(deleteText);
      pstText.setInt(1, noteID);
      pstText.execute();

      PreparedStatement pstDetails = con.prepareStatement(deleteDetails);
      pstDetails.setInt(1, noteID);
      pstDetails.execute();
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

}
