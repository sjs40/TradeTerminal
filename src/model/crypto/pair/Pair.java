package model.crypto.pair;

import org.json.JSONObject;

public class Pair {

  private String exchange;
  private String from;
  private String to;
  private double volumeFrom;
  private double volumeTo;

  public Pair(JSONObject json) {
    exchange = json.getString("exchange");
    from = json.getString("fromSymbol");
    to = json.getString("toSymbol");
    volumeFrom = json.getDouble("volume24h");
    volumeTo = json.getDouble("volume24hTo");
  }

  public String toString() {
    return from + " -> " + to;
  }

  public String toStringFull() {
    return from + " -> " + to + "\n" +
            "Vol From: " + volumeFrom + " Vol To: " + volumeTo + "\n";
  }

}
