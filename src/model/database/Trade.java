package model.database;

import java.sql.Date;

public class Trade {

  private int tradeID;
  private String ticker;
  private double price;
  private Date dateExecuted;
  private int number;
  private double position;

  public Trade(int tradeID, String ticker, double price, Date dateExecuted, int number) {
    this.tradeID = tradeID;
    this.ticker = ticker;
    this.price = price;
    this.dateExecuted = dateExecuted;
    this.number = number;
    this.position = number * price;
  }

  public String toString() {
    return ticker + " @ " + price + " on " + dateExecuted + " for " + number + " share(s). Total: $" + position;
  }

}
