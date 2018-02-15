package model.crypto.price;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import util.JsonReader;

public class CoinPrice {

  private String url1 = "https://min-api.cryptocompare.com/data/price?fsym=";
  private String url2 = "&tsyms=";
  private String from;
  private ArrayList<String> to;
  private ArrayList<Double> toPrice;
  private JSONObject json;

  public CoinPrice(String from, String... to) {
    this.from = from.toUpperCase();
    this.to = new ArrayList<>();
    this.toPrice = new ArrayList<>();
    for (String s : to) {
      this.to.add(s.toUpperCase());
    }
    String url = url1 + from + url2;
    for (String s : this.to) {
      url += s + ",";
    }
    url = url.substring(0, url.length() - 1);
    try {
      json = JsonReader.readJsonFromUrl(url);
      for (String s : this.to) {
        toPrice.add(json.getDouble(s));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < to.size(); i++) {
      result += to.get(i) + ": " + toPrice.get(i) + "\n";
    }
    return result + "\n";
  }

}
