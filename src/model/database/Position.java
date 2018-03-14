package model.database;

import java.sql.Date;
import java.time.LocalDate;

import model.Quote;

public class Position {

  private int positionID;
  private String ticker;
  private Date date;
  private double price;
  private int amount;

  public Position(int positionID, String ticker, Date date, double price, int amount) {
    this.positionID = positionID;
    this.ticker = ticker;
    this.date = date;
    this.price = price;
    this.amount = amount;
  }

  public int getPositionID() {
    return positionID;
  }

  public String getTicker() {
    return ticker;
  }

  public Date getDate() {
    return date;
  }

  public double getPrice() {
    return price;
  }

  public double getCurrentPrice() {
    Quote quote = new Quote(ticker);
    quote.loadStocks(ticker);
    return quote.getCurrentPrice();
  }

  public int getAmount() {
    return amount;
  }

  public LocalDate getDateFromSQLDate() {
    return date.toLocalDate();
  }
}
