package model.financials;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import model.Stocks;
import model.news.NewsElement;
import util.JsonReader;

public class Financial implements Stocks {

  private String url = "";
  private String urlEnd = "/financials";
  private ArrayList<FinancialElement> elements = new ArrayList<>();

  public Financial(String ticker) {
    url = URL_1 + ticker + urlEnd;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) {
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      JSONArray array = json.getJSONArray("financials");
      for (int i = 0; i < array.length(); i++) {
        elements.add(new FinancialElement(array.getJSONObject(i)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (FinancialElement e : elements) {
      result += e.toString();
    }
    return result;
  }



}
