package model.stockdata;

import org.json.JSONObject;

public class StockData {

  private String open;
  private String high;
  private String low;
  private String close;
  private String volume;
  private String adjustClose;
  private String ticker;
  private String date;
  private boolean adjusted;

  public StockData(JSONObject json, String ticker, String date, boolean adjusted) {
    this.adjusted = adjusted;
    open = json.getString("1. open");
    high = json.getString("2. high");
    low = json.getString("3. low");
    close = json.getString("4. close");
    if (adjusted) {
      adjustClose = json.getString("5. adjusted close");
      adjustClose = adjustClose.substring(0, adjustClose.length() - 2);
    } else {
      volume = json.getString("5. volume");
    }
    this.ticker = ticker;
    this.date = date;
    open = open.substring(0, open.length() - 2);
    high = high.substring(0, high.length() - 2);
    low = low.substring(0, low.length() - 2);
    close = close.substring(0, close.length() - 2);
  }

  public String getX() {
    return date;
  }

  public double getY() {
    if (adjusted) {
      return Double.parseDouble(adjustClose);
    } else {
      return Double.parseDouble(close);
    }
  }

  public String toString() {
    return ticker + "\n" +
            "Open: " + open + " Close: " + close + "\n" +
            "High: " + high + " Low: " + low + "\n";
  }

}
