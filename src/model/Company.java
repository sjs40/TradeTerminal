package model;

import org.json.JSONObject;

import java.io.IOException;

import util.JsonReader;

public class Company implements Stocks {

  private String url = "";
  private String urlEnd = "/company";

  private String symbol;
  private String name;
  private String exchange;
  private String industry;
  private String website;
  private String desc;
  private String ceo;
  private String type;
  private String sector;

  public Company(String ticker) {
    url = URL_1 + ticker + urlEnd;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) {
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      symbol = json.getString("symbol");
      name = json.getString("companyName");
      exchange = json.getString("exchange");
      industry = json.getString("industry");
      website = json.getString("website");
      desc = json.getString("description");
      ceo = json.getString("CEO");
      type = json.getString("issueType");
      sector = json.getString("sector");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    return name + " (" + symbol + ")\n" +
            sector + ", " + industry + "\n" +
            "CEO: " + ceo + "\n" +
            "Website: " + website + "\n" +
            "Description: " + desc + "\n";
  }

}
