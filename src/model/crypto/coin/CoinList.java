package model.crypto.coin;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import util.JsonReader;

public class CoinList {

  private String url = "https://www.cryptocompare.com/api/data/coinlist/";
  private ArrayList<Coin> coins;

  public CoinList() {
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      JSONObject data = json.getJSONObject("Data");
      Set<String> keys = data.keySet();
      coins = new ArrayList<>();
      for (String s : keys) {
        coins.add(new Coin(data.getJSONObject(s)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (Coin coin : coins) {
      result += coin.toString() + "\n";
    }
    return result + "\n";
  }

  public String toStringFull() {
    String result = "";
    for (Coin coin : coins) {
      result += coin.toStringFull() + "\n";
    }
    return result + "\n";
  }

  public void keepTop20() {
    ArrayList<Coin> top20 = new ArrayList<>();
    for (Coin c : coins) {
      if (c.getSortOrder() <= 20) {
        top20.add(c);
      }
    }
    coins = top20;
  }

}
