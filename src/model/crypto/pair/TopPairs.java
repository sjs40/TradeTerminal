package model.crypto.pair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import util.JsonReader;

public class TopPairs {

  private String url1 = "https://min-api.cryptocompare.com/data/top/pairs?fsym=";

  private JSONArray array;

  public TopPairs(String from) {
    String url = url1 + from.toUpperCase() + "&limit=20";
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      array = json.getJSONArray("Data");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < array.length(); i++) {
      Pair pair = new Pair(array.getJSONObject(i));
      result += pair.toString() + "\n";
    }
    return result + "\n";
  }

  public String toStringFull() {
    String result = "";
    for (int i = 0; i < array.length(); i++) {
      Pair pair = new Pair(array.getJSONObject(i));
      result += pair.toStringFull() + "\n";
    }
    return result + "\n";
  }

}
