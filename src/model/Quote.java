package model;

import com.oracle.javafx.jmx.json.JSONReader;

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

  public Quote(String ticker) {
    url = URL_1 + ticker + urlEnd;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) {
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
      peRatio = json.getDouble("peRatio");
      high52 = json.getDouble("week52High");
      low52 = json.getDouble("week52Low");
      ytdChange = json.getDouble("ytdChange");
    } catch (IOException e) {
      e.printStackTrace();
    }
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
