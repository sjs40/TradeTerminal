package model.related;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Quote;
import model.database.H2Database;
import model.database.Position;
import util.JsonReader;
import util.Utils;

public class Related {

  private Map<String, Integer> relatedStocks;
  private ArrayList<String> stocksHeld;
  private String url1 = "https://api.iextrading.com/1.0/stock/";
  private String url2 = "/relevant";

  public Related() {
    relatedStocks = new HashMap<>();
    stocksHeld = new ArrayList<>();
    H2Database database = new H2Database();
    for (Position pos : database.getAllPositions()) {
      stocksHeld.add(pos.getTicker());
    }
  }

  public void fillRelatedStocks() {
    relatedStocks = new HashMap<>();
    for (String ticker : stocksHeld) {
      String url = url1 + ticker.toLowerCase() + url2;
      try {
        JSONObject json = JsonReader.readJsonFromUrl(url);
        JSONArray array = json.getJSONArray("symbols");
        for (int i = 0; i < array.length(); i++) {
          String related = array.getString(i);
          if (relatedStocks.containsKey(related)) {
            relatedStocks.put(related, relatedStocks.get(related) + 1);
          } else {
            relatedStocks.put(related, 1);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    relatedStocks = Utils.sortByValue(relatedStocks);
  }

  public String getStocksHeldString() {
    String result = "";
    for (String ticker : stocksHeld) {
      result += ticker + "\n";
    }
    return result;
  }

  public String getRelatedStocksString() {
    String result = "";
    for (Map.Entry<String, Integer> entry : relatedStocks.entrySet()) {
      result += entry.getKey() + ": " + entry.getValue() + "\n";
    }
    return result;
  }

  public ArrayList<String> getTop7() {
    ArrayList<String> top7 = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : relatedStocks.entrySet()) {
      top7.add(entry.getKey());
    }
    return top7;
  }

  public ArrayList<Quote> getTop7Quotes() {
    ArrayList<Quote> quotes = new ArrayList<>();
    for (String ticker : getTop7()) {
      try {
        System.out.println(ticker);
        quotes.add(new Quote(ticker.toLowerCase()));
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      }
    }
    return quotes;
  }

}
