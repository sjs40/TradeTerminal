package model;

import org.json.JSONObject;

import java.io.IOException;

import util.JsonReader;

public class Quote implements Stocks {

  private String url = "";
  private String urlEnd = "/quote";

  private String symbol;
  private String name;
  private String exchange;
  private String sector;
  private double open;
  private int openTime;
  private double close;
  private int closeTime;
  private double high;
  private double low;
  private double latestPrice;
  private int latestTime;
  private double change;
  private double changePct;
  private int marketCap;
  private double peRatio;
  private double high52;
  private double low52;
  private double ytdChange;

  public Quote(String ticker) throws IllegalArgumentException {
    url = URL_1 + ticker + urlEnd;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) throws IllegalArgumentException {
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      symbol = json.getString("symbol");
      name = json.getString("companyName");
      exchange = json.getString("primaryExchange");
      sector = json.getString("sector");
      open = json.getDouble("open");
      openTime = json.getInt("openTime");
      close = json.getDouble("close");
      closeTime = json.getInt("closeTime");
      high = json.getDouble("high");
      low = json.getDouble("low");
      latestPrice = json.getDouble("latestPrice");
      latestTime = json.getInt("latestUpdate");
      change = json.getDouble("change");
      changePct = json.getDouble("changePercent");
      marketCap = json.getInt("marketCap");
      try {
        peRatio = json.getDouble("peRatio");
      } catch (Exception e) {
        peRatio = -1;
      }
      high52 = json.getDouble("week52High");
      low52 = json.getDouble("week52Low");
      ytdChange = json.getDouble("ytdChange");
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Not a valid ticker.");
    }
  }

  public String getSymbol() {
    return symbol;
  }

  public String getSector() {
    return sector;
  }

  public double getOpen() {
    return open;
  }

  public double getClose() {
    return close;
  }

  public double getHigh() {
    return high;
  }

  public double getLow() {
    return low;
  }

  public double getChange() {
    return change;
  }

  public double getChangePct() {
    return changePct;
  }

  public double getHigh52() {
    return high52;
  }

  public double getLow52() {
    return low52;
  }

  public int getMarketCap() {
    return marketCap;
  }

  public String getName() {
    return name;
  }

  public double getCurrentPrice() {
    return this.latestPrice;
  }

  public String toString() {
    String changePctStr = String.format("%.2f", changePct);
    return name + " (" + symbol + ")\n" +
            "Open: $" + open + " Close: $" + close + "\n" +
            "High: $" + high + " Low: $" + low + "\n" +
            "Current: $" + latestPrice + " Change: $" + change + "(%" + changePctStr + ")\n";
  }

}
