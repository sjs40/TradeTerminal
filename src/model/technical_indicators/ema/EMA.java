package model.technical_indicators.ema;

import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import key.Key;
import model.stockdata.DataFunction;
import util.JsonReader;

public class EMA {

  private String url1 = "https://www.alphavantage.co/query?function=EMA&symbol=";
  private String ticker;
  private String url2 = "&interval=";
  private String interval;
  private String url3 = "&time_period=";
  private String timePeriod;
  private String url4 = "&series_type=close&apikey=" + Key.getKey();

  private ArrayList<EMAElement> elements;

  public EMA(String ticker, DataFunction interval, String timePeriod, RegularTimePeriod earliestPeriod) {
    this.ticker = ticker.toUpperCase();
    this.interval = interval.getPeriod();
    this.timePeriod = timePeriod;
    elements = new ArrayList<>();
    String url = url1 + this.ticker + url2 + this.interval + url3 + this.timePeriod + url4;
    try {
      JSONObject json = JsonReader.readJsonFromUrl(url);
      JSONObject series = json.getJSONObject("Technical Analysis: EMA");
      Set<String> keys = series.keySet();
      for (String key : keys) {
        EMAElement element = new EMAElement(key, series.getJSONObject(key));
        int compare = element.getDate().compareTo(earliestPeriod.getStart());
        if (compare >= 0) {
          elements.add(element);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public TimeSeries getTimeSeries() {
    TimeSeries series = new TimeSeries("EMA", Minute.class);
    for (EMAElement element : elements) {
      series.add(element.getX(), element.getY());
    }
    return series;
  }

}
