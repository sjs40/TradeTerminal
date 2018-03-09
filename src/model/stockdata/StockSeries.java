package model.stockdata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import key.Props;
import util.JsonReader;

public class StockSeries {

  private String url1 = "https://www.alphavantage.co/query?function=";
  private String function;
  private String url2 = "&symbol=";
  private String ticker;
  private String url3 = "&apikey=" + Props.getKey();

  private ArrayList<StockData> data;

  public StockSeries(DataFunction function, String ticker) {
    this.function = function.getFunction();
    this.ticker = ticker.toUpperCase();
    data = new ArrayList<>();
    String url = url1 + this.function + url2 + this.ticker + url3;
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      String jsonKey = "";
      boolean adjusted = false;
      switch (function) {
        case WEEKLY:
          jsonKey = "Weekly Time Series";
          break;
        case DAILY:
          jsonKey = "Daily Time Series";
          break;
        case MONTHLY:
          jsonKey = "Monthly Time Series";
          break;
        case INTRADAY:
          jsonKey = "Time Series (1min)";
          break;
        case WEEKLY_ADJUST:
          jsonKey = "Weekly Adjusted Time Series";
          adjusted = true;
          break;
        case DAILY_ADJUST:
          jsonKey = "Time Series (Daily)";
          adjusted = true;
          break;
      }
      JSONObject series = json.getJSONObject(jsonKey);
      Set<String> keys = series.keySet();
      for (String key : keys) {
        data.add(new StockData(series.getJSONObject(key), this.ticker, key, adjusted));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (StockData d : data) {
      result += d.toString();
    }
    return result + "\n";
  }

  public ArrayList<StockData> getData() {
    return data;
  }
}
