package model.news;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import model.Stocks;
import util.JsonReader;

public class News implements Stocks {

  private String url = "";
  private String urlEnd = "/news";
  private String ticker;
  private ArrayList<NewsElement> elements = new ArrayList<>();

  public News(String ticker) {
    url = URL_1 + ticker + urlEnd;
    this.ticker = ticker;
    loadStocks(ticker);
  }

  @Override
  public void loadStocks(String ticker) {
    try {
      JSONArray array = JsonReader.readJsonArrayFromUrl(url);
      for (int i = 0; i < array.length(); i++) {
        elements.add(new NewsElement(array.getJSONObject(i), ticker));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String result = "";
    for (NewsElement e : elements) {
      result += e.toString();
    }
    return result;
  }

}
