package model.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import key.Props;

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

  public String getUsersStr() {
    String query = "SELECT * FROM user;";
    try {
      ResultSet rs = st.executeQuery(query);
      if (rs.next()) {
        return rs.getString(1) + " " + rs.getString(2);
      } else {
        return "";
      }
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
      return "";
    }
  }

  public ArrayList<Trade> getTransactionsByUser(String username) {
    ArrayList<Trade> transactions = new ArrayList<>();
    String query = "SELECT trade.tradeid, ticker, date_executed, price, number " +
            "FROM trade, user, transaction " +
            "WHERE user.username = \'" + username + "\' AND transaction.username = \'" + username + "\' AND transaction.tradeid = trade.tradeid;";
    try {
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        int tradeID = rs.getInt("tradeID");
        String ticker = rs.getString("ticker");
        double price = rs.getDouble("price");
        Date dateExecuted = rs.getDate("date_executed");
        int number = rs.getInt("number");
        transactions.add(new Trade(tradeID, ticker, price, dateExecuted, number));
      }
      return transactions;
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
      return transactions;
    }
  }

  public void addTransaction(String username, String ticker, Date dateExecuted, double price, int number) {
    String query1 = "SELECT tradeID, ticker, price, date_executed, number FROM trade;";
    String query2 = "SELECT username, tradeID FROM transaction;";
    try {
      ResultSet rs = st.executeQuery(query1);
      rs.moveToCurrentRow();
      while (rs.next()) {
        int tradeID = rs.getInt("tradeID");
        rs.moveToInsertRow();
        rs.updateInt("tradeID", tradeID + 1);
        rs.updateString("ticker", ticker);
        rs.updateDouble("price", price);
        rs.updateDate("date_executed", dateExecuted);
        rs.updateInt("number", number);
        rs = st.executeQuery(query2);
        rs.moveToInsertRow();
        rs.updateString("username", username);
        rs.updateInt("tradeID", tradeID + 1);
      }
    } catch (SQLException e) {
      Logger logger = Logger.getLogger(DB.class.getName());
      logger.log(Level.SEVERE, e.getMessage(), e);
    }
  }

}
