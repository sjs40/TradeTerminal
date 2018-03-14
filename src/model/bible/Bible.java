package model.bible;

import org.json.JSONObject;

import java.io.IOException;

import util.JsonReader;

public class Bible {

  private String url1 = "https://ajith-holy-bible.p.mashape.com/GetChapter?Book=";
  private String book;
  private String url2 = "&chapter=";
  private String chapter;

  private JSONObject json;
  private String output;

  public Bible(String book, String chapter) throws IllegalArgumentException {
    this.book = book;
    this.chapter = chapter;
    String url = url1 + this.book + url2 + this.chapter;
    try {
      this.json = JsonReader.readJsonFromUrlAuth(url, "X-Mashape-Key", "nkkHJiSQ0jmshcimlIeRaIUyK0cSp1enLzUjsnQJRYXLkWMYe9");
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Not a valid book and/or chapter.");
    }
  }

  public void setOutput() {
    String b = json.getString("Book");
    String c = json.getString("Chapter");
    String o = json.getString("Output");
    output = b + "\n" + c + "\n" + o;
  }

  public String getOutput() {
    return output;
  }
}
