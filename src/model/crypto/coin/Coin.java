package model.crypto.coin;

import org.json.JSONObject;

public class Coin {

  private JSONObject data;
  private String symbol;
  private String name;
  private String fullName;
  private String algorithm;
  private String proofType;
  private String totalCoinSupply;
  private int sortOrder;

  public Coin(JSONObject coin) {
    symbol = coin.getString("Name");
    name = coin.getString("CoinName");
    fullName = coin.getString("FullName");
    algorithm = coin.getString("Algorithm");
    proofType = coin.getString("ProofType");
    totalCoinSupply = coin.getString("TotalCoinSupply");
    sortOrder = Integer.parseInt(coin.getString("SortOrder"));
  }

  public String toString() {
    return symbol + " - " + name;
  }

  public String toStringFull() {
    return fullName + "\n" +
            "Algorithm: " + algorithm + " Proof Type: " + proofType + "\n" +
            "Total Coin Supply: " + totalCoinSupply + "\n";
  }

  public int getSortOrder() {
    return sortOrder;
  }

}
