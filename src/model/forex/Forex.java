package model.forex;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import key.Props;
import util.JsonReader;

public class Forex {

  private Map<String, Double> pairsToRates;
  private String[] fromPair = {"EUR", "USD", "GBP", "AUD", "USD", "USD", "EUR", "EUR"};
  private String[] toPair = {"USD", "JPY", "USD", "USD", "CHF", "CAD", "JPY", "GBP"};
  private String url1 = "https://free.currencyconverterapi.com/api/v5/convert?q=";
  private String from;
  private String url2 = "_";
  private String to;


  public Forex() {
    pairsToRates = new HashMap<>();
    for (int i = 0; i < fromPair.length; i++) {
      String f = fromPair[i];
      String t = toPair[i];
      String url = url1 + f + url2 + t;
      try {
        JSONObject json = JsonReader.readJsonFromUrl(url);
        this.addToMap(json, f, t);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void addToMap(JSONObject object, String from, String to) {
    JSONObject results = object.getJSONObject("results");
    JSONObject pairing = results.getJSONObject(from + "_" + to);
    String fromCode = pairing.getString("fr");
    String toCode = pairing.getString("to");
    double rate = pairing.getDouble("val");
    String pair = fromCode + "/" + toCode;
    pairsToRates.put(pair, rate);
  }

  public Map<String, Double> getPairsToRates() {
    return pairsToRates;
  }
}
