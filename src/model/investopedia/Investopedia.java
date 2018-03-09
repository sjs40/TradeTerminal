package model.investopedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Investopedia {

  private String word;
  private String url1 = "https://www.investopedia.com/terms/";
  private String firstLetter;
  private String urlEnd = ".asp";
  private String url;
  private Document doc;

  public Investopedia(String word) {
    this.word = word.toLowerCase().replaceAll("[^a-z]+", "").trim();
    this.firstLetter = String.valueOf(this.word.charAt(0)).toLowerCase();
    this.url = url1 + firstLetter + "/" + this.word + urlEnd;
    try {
      this.doc = Jsoup.connect(this.url).get();
    } catch (IOException e) {
      throw new IllegalArgumentException("Unknown Term");
    }
  }

  public String getDefinition() {
    Element div = doc.select("div.content-box-term").first();
    Element paragraph = div.select("p").first();
    return paragraph.text();
  }

}
