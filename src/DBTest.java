import java.sql.Date;
import java.util.ArrayList;

import model.database.DB;
import model.database.Trade;

public class DBTest {
  public static void main(String[] args) {
    DB db = new DB();
    System.out.println(db.getUsersStr());
    ArrayList<Trade> transactions = db.getTransactionsByUser("samjos11");
    for (Trade trade : transactions) {
      System.out.println(trade.toString());
    }

    db.addTransaction("samjos11", "CSCO", new Date(2018, 2, 20), 43.42, 6);
  }
}
