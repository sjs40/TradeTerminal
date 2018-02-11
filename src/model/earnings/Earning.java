package model.earnings;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import model.Stocks;
import model.financials.FinancialElement;
import util.JsonReader;

public class Earning implements Stocks {

  private String url = "";
  private String urlEnd = "/earnings";
  private ArrayList<EarningElement> elements = new ArrayList<>();

  public Earning(String ticker) {
    url = URL_1 + ticker + urlEnd;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) {
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      JSONArray array = json.getJSONArray("earnings");
      for (int i = 0; i < array.length(); i++) {
        elements.add(new EarningElement(array.getJSONObject(i)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (EarningElement e : elements) {
      result += e.toString();
    }
    return result;
  }
}
